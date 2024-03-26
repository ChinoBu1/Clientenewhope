import org.bouncycastle.util.Arrays;

import proto.msg2;
import proto.msgproto;

public class Client {
    static final int PORT = 8888;

    public static void main(String[] args) throws Exception {
        HTTPClient client;
        JSON json = new JSON();
        NewHope nh;
        int n = Integer.parseInt(args[1]);
        int q = Integer.parseInt(args[2]);
        int mode = Integer.parseInt(args[0]);
        if (args.length == 3) {
            client = new HTTPClient(mode, n, q,
                    "localhost", PORT);
        } else {
            client = new HTTPClient(mode, n, q, args[3], Integer.parseInt(args[4]));
        }

        nh = new NewHope(n, q);
        byte[] seed = nh.generateSeed();

        Polynomial eb = nh.generateBinoPol();
        Polynomial eb1 = nh.generateBinoPol();
        Polynomial sb = nh.generateBinoPol();
        Polynomial m = nh.parseSeed(seed);

        Polynomial pb = Polynomial.PolyModInt(
                Polynomial.PolyModF(
                        Polynomial.PolySum(
                                Polynomial.PolyMult(m, sb),
                                eb),
                        nh.getF()),
                nh.getQ());
        byte[] rmessage = client.sendSetup(pb, seed);
        Polynomial pa = new Polynomial();
        int[][] hint = new int[256][4];
        switch (mode) {
            case 0:
                byte[] paByte = new byte[rmessage.length - 256 * 4 * 4];
                byte[] hintByte = new byte[256 * 4 * 4];
                System.arraycopy(rmessage, 0, paByte, 0, paByte.length);
                System.arraycopy(rmessage, paByte.length, hintByte, 0,
                        hintByte.length);
                pa = nh.fromByteArray(paByte);

                for (int i = 0; i < hint.length; i++) {
                    for (int j = 0; j < 4; j++) {
                        hint[i][j] = (hintByte[4 * (4 * i + j)] & 0xFF)
                                |
                                ((hintByte[4 * (4 * i + j) + 1]
                                        & 0xFF) << 8)
                                | ((hintByte[4 * (4 * i + j)
                                        + 2]
                                        & 0xFF) << 16)
                                | ((hintByte[4 * (4 * i + j)
                                        + 3]
                                        & 0xFF) << 24);
                    }
                }
                break;
            case 1:
                msgjson2 msg2 = json.msg2FromJson(new String(rmessage));
                pa = msg2.getPoly();
                hint = msg2.getHint();
                break;

            case 2:
                proto.msg2 protomsg2 = proto.msg2.parseFrom(rmessage);
                Long[] temp2 = protomsg2.getCoefsList().toArray(new Long[0]);
                long[] coef = new long[temp2.length];
                for (int i = 0; i < temp2.length; i++) {
                    coef[i] = (long) temp2[i];
                }

                pa = new Polynomial(coef);
                Integer[] temp4 = protomsg2.getHintsList().toArray(new Integer[0]);
                int[] hintTemp = new int[temp4.length];
                for (int i = 0; i < temp4.length; i++) {
                    hintTemp[i] = (int) temp4[i];
                }
                for (int i = 0; i < hintTemp.length / 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        hint[i][j] = hintTemp[4 * i + j];
                    }
                }
                break;

            default:
                break;
        }

        Polynomial Kb = Polynomial.PolyModInt(
                Polynomial.PolyModF(
                        Polynomial.PolySum(
                                Polynomial.PolyMult(pa, sb),
                                eb1),
                        nh.getF()),
                nh.getQ());

        int[] SK = nh.Rec(Kb, hint);
        byte[] K = nh.toByte(SK);

        for (byte b : K) {
            System.out.print(b + " ");
        }
    }
}
