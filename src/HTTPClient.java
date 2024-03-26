import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.protobuf.ByteString;

import proto.msg2;
import proto.msg1;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;

public class HTTPClient {
    int n;
    int q;
    int mode;
    URI uri;
    NewHope nh;
    HttpClient client = HttpClient.newBuilder()
            .version(Version.HTTP_1_1)
            .followRedirects(Redirect.NORMAL)
            .build();

    JSON json = new JSON();

    public HTTPClient(int mode, int n, int q, String host, int port) throws URISyntaxException {
        this.mode = mode;
        this.n = n;
        this.q = q;
        nh = new NewHope(n, q);
        uri = new URI("http", null, host, port, "/", "n=" + n + "&q=" + q, null);
    }

    public byte[] sendSetup(Polynomial pb, byte[] seed) throws IOException, InterruptedException {
        Builder r = HttpRequest.newBuilder(uri);
        byte[] message = new byte[0];
        switch (mode) {
            case 0:
                r.header("Accept", "application/octet-stream");
                r.header("Content-type", "application/octet-stream");
                byte[] paByte = nh.toByteArray(pb);
                message = new byte[paByte.length + seed.length];
                System.arraycopy(paByte, 0, message, 0, paByte.length);
                System.arraycopy(seed, 0, message, paByte.length, seed.length);
                break;
            case 1:
                r.header("Accept", "application/json");
                r.header("Content-type", "application/json");
                message = json.ToJson(pb, seed).getBytes();
                break;
            case 2:
                r.header("Accept", "application/x-protobuf");
                r.header("Content-type", "application/x-protobuf");
                proto.msg1.Builder b = msg1.newBuilder();
                for (long c : pb.GetCoef()) {
                    b.addCoefs(c);
                }
                b.setSeed(ByteString.copyFrom(seed));
                msg1 msg = b.build();
                message = msg.toByteArray();
                break;

            default:
                break;
        }
        r.method("POST", BodyPublishers.ofByteArray(message));
        HttpRequest request = r.build();
        HttpResponse<byte[]> response = client.send(request, BodyHandlers.ofByteArray());
        byte[] resp = response.body();
        return resp;
    }

}
