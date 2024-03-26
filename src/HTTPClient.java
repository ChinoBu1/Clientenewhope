import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;

import proto.msg2;

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

    public byte[] sendGet() throws IOException, InterruptedException {
        Builder r = HttpRequest.newBuilder(uri);
        switch (mode) {
            case 0:
                r.header("Accept", "application/octet-stream");
                break;
            case 1:
                r.header("Accept", "application/json");
                break;
            case 2:
                r.header("Accept", "application/x-protobuf");
                break;

            default:
                break;
        }
        HttpRequest request = r.build();
        HttpResponse<byte[]> response = client.send(request, BodyHandlers.ofByteArray());
        byte[] resp = response.body();
        return resp;
    }

    public byte[] sendPost(Polynomial x, int[][] hint) throws IOException, InterruptedException {
        byte[] message = new byte[0];
        Builder r = HttpRequest.newBuilder(uri);
        switch (mode) {
            case 0:
                r.header("Content-type", "application/octet-stream");
                byte[] hintByte = new byte[hint.length * 4 * 4];
                for (int i = 0; i < hint.length; i++) {
                    for (int j = 0; j < hint[i].length; j++) {
                        hintByte[4 * (4 * i + j)] = (byte) (hint[i][j]);
                        hintByte[4 * (4 * i + j) + 1] = (byte) (hint[i][j] >> 8);
                        hintByte[4 * (4 * i + j) + 2] = (byte) (hint[i][j] >> 16);
                        hintByte[4 * (4 * i + j) + 3] = (byte) (hint[i][j] >> 24);
                    }
                }

                byte[] pbByte = nh.toByteArray(x);
                message = new byte[pbByte.length + hintByte.length];

                System.arraycopy(pbByte, 0, message, 0, pbByte.length);
                System.arraycopy(hintByte, 0, message, pbByte.length, hintByte.length);
                break;
            case 1:
                r.header("Content-type", "application/json");
                message = json.ToJson(x, hint).getBytes();
                break;

            case 2:
                r.header("Content-type", "application/x-protobuf");
                proto.msg2.Builder b = proto.msg2.newBuilder();
                for (long c : x.GetCoef()) {
                    b.addCoefs(c);
                }
                for (int[] i : hint) {
                    for (int j : i) {
                        b.addHints(j);

                    }
                }
                msg2 msg = b.build();
                message = msg.toByteArray();
                break;

            default:

                break;
        }
        byte[] hintByte = new byte[hint.length * 4 * 4];
        for (int i = 0; i < hint.length; i++) {
            for (int j = 0; j < hint[i].length; j++) {
                hintByte[4 * (4 * i + j)] = (byte) (hint[i][j]);
                hintByte[4 * (4 * i + j) + 1] = (byte) (hint[i][j] >> 8);
                hintByte[4 * (4 * i + j) + 2] = (byte) (hint[i][j] >> 16);
                hintByte[4 * (4 * i + j) + 3] = (byte) (hint[i][j] >> 24);
            }
        }

        byte[] pbByte = nh.toByteArray(x);
        byte[] msgraw = new byte[pbByte.length + hintByte.length];
        System.out.println(msgraw.length);
        System.out.println(json.ToJson(x, hint).getBytes().length);
        proto.msg2.Builder b = proto.msg2.newBuilder();
        for (long c : x.GetCoef()) {
            b.addCoefs(c);
        }
        for (int[] i : hint) {
            for (int j : i) {
                b.addHints(j);
            }
        }
        msg2 msg = b.build();
        System.out.println(msg.toByteArray().length);
        r.method("POST", BodyPublishers.ofByteArray(message));
        HttpRequest request = r.build();
        HttpResponse<byte[]> response = client.send(request, BodyHandlers.ofByteArray());
        byte[] resp = response.body();
        return resp;
    }
}
