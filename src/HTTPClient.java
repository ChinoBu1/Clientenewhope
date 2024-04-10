import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Base64;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import com.google.protobuf.ByteString;

import proto.Msg;
import proto.protoRequest;
import proto.protoRequestOrBuilder;
import proto.protoResponse;

import java.io.FileOutputStream;
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
    Logger logger = Logger.getLogger("client");
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

    public HTTPClient(int n, int q, String host, int port) throws URISyntaxException {
        FileHandler fh;
        try {
            fh = new FileHandler("client1.log");
            fh.setFormatter(new myformatter());
            logger.addHandler(fh);
        } catch (SecurityException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.setUseParentHandlers(false);
        this.n = n;
        this.q = q;
        nh = new NewHope(n, q);
        uri = new URI("http", null, host, port, "/", "n=" + n + "&q=" + q, null);
    }

    public byte[] sendSetup(Polynomial pb, byte[] seed) throws IOException, InterruptedException {

        Builder r = HttpRequest.newBuilder(uri);
        byte[] message = new byte[0];
        r.header("Accept", "application/octet-stream");
        r.header("Content-type", "application/octet-stream");
        System.out.println(pb);
        byte[] pbByte = nh.toByteArray(pb);
        message = new byte[pbByte.length + seed.length];
        System.arraycopy(pbByte, 0, message, 0, pbByte.length);
        System.arraycopy(seed, 0, message, pbByte.length, seed.length);
        r.method("POST", BodyPublishers.ofByteArray(message));
        logger.info(Base64.getEncoder().encodeToString(message) + "\n");
        HttpRequest request = r.build();
        HttpResponse<byte[]> response = client.send(request, BodyHandlers.ofByteArray());
        byte[] resp = response.body();
        return resp;
    }

    public protoResponse sendSetupProto(Polynomial pb, byte[] seed) throws IOException, InterruptedException {
        Builder r = HttpRequest.newBuilder(uri);
        r.header("Accept", "application/x-protobuf");
        r.header("Content-type", "application/x-protobuf");
        protoRequest.Builder b = protoRequest.newBuilder();
        for (long c : pb.GetCoef()) {
            b.addCoefs(c);
        }
        b.setSeed(ByteString.copyFrom(seed));
        protoRequest msg = b.build();
        logger.info(Base64.getEncoder().encodeToString(msg.toByteArray()) + "\n");
        r.method("POST", BodyPublishers.ofByteArray(msg.toByteArray()));
        HttpRequest request = r.build();
        HttpResponse<byte[]> response = client.send(request, BodyHandlers.ofByteArray());
        byte[] temp = response.body();
        protoResponse resp = protoResponse.parseFrom(temp);
        FileOutputStream output = new FileOutputStream("protoresp.txt");
        resp.writeTo(output);
        return resp;
    }

    public jsonResponse sendSetupJson(Polynomial pb, byte[] seed) throws IOException, InterruptedException {
        Builder r = HttpRequest.newBuilder(uri);
        logger.setUseParentHandlers(false);
        r.header("Accept", "application/json");
        r.header("Content-type", "application/json");
        byte[] message = json.ToJson(pb, seed).getBytes();
        logger.info(json.ToJson(pb, seed) + "\n" + Base64.getEncoder().encodeToString(seed));
        r.method("POST", BodyPublishers.ofByteArray(message));
        HttpRequest request = r.build();
        HttpResponse<byte[]> response = client.send(request, BodyHandlers.ofByteArray());
        byte[] temp = response.body();
        jsonResponse resp = json.msg2FromJson(new String(temp));
        return resp;
    }

    public void open() {
        client = HttpClient.newBuilder()
                .version(Version.HTTP_1_1)
                .followRedirects(Redirect.NORMAL)
                .build();
    }

    public void close() {
        client.shutdown();
        ;
    }

}
