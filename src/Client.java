import org.bouncycastle.util.Arrays;

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
        byte[] rmessage = client.sendGet();
        byte[] seed = new byte[32];
        Polynomial pa = new Polynomial();
        switch (mode) {
            case 0:
                byte[] paByte = new byte[rmessage.length - 32];
                System.arraycopy(rmessage, rmessage.length - 32, seed, 0, 32);
                System.arraycopy(rmessage, 0, paByte, 0, rmessage.length - 32);
                pa = nh.fromByteArray(paByte);
                break;
            case 1:
                msg1 msg1 = json.msg1FromJson(new String(rmessage));
                pa = msg1.getPoly();
                seed = msg1.getSeed();
                break;

            case 2:
                proto.msg1 protomsg1 = proto.msg1.parseFrom(rmessage);
                Long[] temp = protomsg1.getCoefsList().toArray(new Long[0]);
                long[] coef = new long[temp.length];
                for (int i = 0; i < temp.length; i++) {
                    coef[i] = (long) temp[i];
                }
                pa = new Polynomial(coef);
                seed = protomsg1.getSeed().toByteArray();
                break;

            default:
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

        int[] SK = nh.Rec(Kb, hint);
        byte[] K = nh.toByte(SK);

        byte[] Ka = client.sendPost(pb, hint);

        System.out.println(Arrays.areEqual(K, Ka));
    }
}
