import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Client {
    private static Socket conection;
    private static DataInputStream input;
    private static DataOutputStream output;
    private static NewHope nh;
    static final int PORT = 8888;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        if (args.length == 1) {
            conection = new Socket(InetAddress.getLocalHost(), PORT);
        } else {
            conection = new Socket(InetAddress.getByName(args[1]), Integer.parseInt(args[2]));
        }
        int mode = Integer.parseInt(args[0]);
        nh = new NewHope();

        input = new DataInputStream(conection.getInputStream());
        output = new DataOutputStream(conection.getOutputStream());
        sendData(new byte[] { (byte) mode });
        byte[] rmessage = reciveData();
        byte[] seed;
        byte[] paByte;
        Polynomial pa;
        switch (mode) {
            case 0:
                seed = new byte[32];
                paByte = new byte[rmessage.length - 32];
                System.arraycopy(rmessage, rmessage.length - 32, seed, 0, 32);
                System.arraycopy(rmessage, 0, paByte, 0, rmessage.length - 32);
                pa = nh.fromByteArray(paByte);
                break;
            case 1:
                String json = new String(rmessage);
                String json_Polynom = json.substring(json.indexOf("{", 1), json.indexOf("}", 1) + 1);
                String json_seed = json.substring(json.indexOf("[", json_Polynom.length()),
                        json.indexOf("]", json_Polynom.length()) + 1);
                pa = JSON.ParsePoly(json_Polynom);
                seed = JSON.ParseSeed(json_seed);
                break;

            default:
                seed = new byte[32];
                paByte = new byte[rmessage.length - 32];
                System.arraycopy(rmessage, rmessage.length - 32, seed, 0, 32);
                System.arraycopy(rmessage, 0, paByte, 0, rmessage.length - 32);
                pa = nh.fromByteArray(paByte);
                break;
        }

        Polynomial sb = nh.generateBinoPol();
        Polynomial eb1 = nh.generateBinoPol();

        Polynomial Kb = Polynomial.PolyModInt(
                Polynomial.PolyModF(
                        Polynomial.PolySum(
                                Polynomial.PolyMult(pa, sb),
                                eb1),
                        nh.getF()),
                nh.getQ());

        Polynomial m = nh.parseSeed(seed);

        Polynomial eb = nh.generateBinoPol();
        Polynomial pb = Polynomial.PolyModInt(
                Polynomial.PolyModF(
                        Polynomial.PolySum(
                                Polynomial.PolyMult(m, sb),
                                eb),
                        nh.getF()),
                nh.getQ());
        int[][] hint = nh.hint(Kb);
        byte[] hintByte;
        byte[] pbByte;
        byte[] message;
        switch (mode) {
            case 0:
                hintByte = new byte[hint.length * 4 * 4];
                for (int i = 0; i < hint.length; i++) {
                    for (int j = 0; j < hint[i].length; j++) {
                        hintByte[4 * (4 * i + j)] = (byte) (hint[i][j]);
                        hintByte[4 * (4 * i + j) + 1] = (byte) (hint[i][j] >> 8);
                        hintByte[4 * (4 * i + j) + 2] = (byte) (hint[i][j] >> 16);
                        hintByte[4 * (4 * i + j) + 3] = (byte) (hint[i][j] >> 24);
                    }
                }
                pbByte = nh.toByteArray(pb);
                message = new byte[pbByte.length + hintByte.length];
                System.arraycopy(pbByte, 0, message, 0, pbByte.length);
                System.arraycopy(hintByte, 0, message, pbByte.length, hintByte.length);
                break;
            case 1:
                String json = "[" + JSON.json(pb) + ", " + JSON.json(hint) + "]";
                message = json.getBytes();
                break;

            default:
                hintByte = new byte[hint.length * 4 * 4];
                for (int i = 0; i < hint.length; i++) {
                    for (int j = 0; j < hint[i].length; j++) {
                        hintByte[4 * (4 * i + j)] = (byte) (hint[i][j]);
                        hintByte[4 * (4 * i + j) + 1] = (byte) (hint[i][j] >> 8);
                        hintByte[4 * (4 * i + j) + 2] = (byte) (hint[i][j] >> 16);
                        hintByte[4 * (4 * i + j) + 3] = (byte) (hint[i][j] >> 24);
                    }
                }
                pbByte = nh.toByteArray(pb);
                message = new byte[pbByte.length + hintByte.length];
                System.arraycopy(pbByte, 0, message, 0, pbByte.length);
                System.arraycopy(hintByte, 0, message, pbByte.length, hintByte.length);
                break;
        }

        sendData(message);
        int[] SK = nh.Rec(Kb, hint);
        byte[] K = nh.toByte(SK);
        MessageDigest ms = MessageDigest.getInstance("SHA3-256");
        byte[] Key = ms.digest(K);

        SecretKey sk = new SecretKeySpec(Key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sk);
        byte[] secret = reciveData();
        conection.close();
        byte[] decode = Base64.getDecoder().decode(secret);
        byte[] decrypt = new byte[0];
        try {
            decrypt = cipher.doFinal(decode);
            System.out.println(new String(decrypt));
        } catch (Exception e) {
            System.out.println("Fail");
        }
        if ("Test".equals(new String(decrypt))) {

            long finish = System.currentTimeMillis();
            System.out.println("Succes in " + (finish - start) + " ms");
        }

    }

    public static void sendData(byte[] bytes) throws IOException {
        output.writeInt(bytes.length);
        output.write(bytes);
        output.flush();
    }

    public static byte[] reciveData() throws IOException {
        byte[] message = new byte[input.readInt()];
        input.readFully(message);
        return message;
    }

}
