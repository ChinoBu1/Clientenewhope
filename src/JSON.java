public class JSON {
    public JSON() {
    }

    public static String json(Polynomial x) {
        String json = String.format("{\"coef\": [%d", x.GetCoef()[0]);
        for (int i = 1; i <= x.GetGrado(); i++) {
            json += String.format(", %d", x.GetCoef()[i]);
        }
        json += String.format("], \"grade\": %d}", x.GetGrado());
        return json;
    }

    public static String json(byte[] x) {
        String json = String.format("[ %d", x[0]);
        for (int i = 1; i < x.length; i++) {
            json += String.format(", %d", x[i]);
        }
        json += String.format("]", x.length);
        return json;
    }

    public static String json(int[][] hint) {
        String json = String.format("[ [ %d, %d, %d, %d] ", hint[0][0], hint[0][1], hint[0][2], hint[0][3]);
        for (int i = 1; i < hint.length; i++) {
            json += String.format(", [ %d, %d, %d, %d] ", hint[i][0], hint[i][1], hint[i][2], hint[i][3]);
        }
        json += "]";
        return json;
    }

    public static Polynomial ParsePoly(String x) {
        int grade = Integer.parseInt(x.substring(x.indexOf(":", x.indexOf("]", 0)) + 1, x.length() - 1).strip());
        String[] temps = x.substring(x.indexOf("[", 0) + 1, x.indexOf("]", 0)).split(",");
        long[] coef = new long[grade + 1];
        for (int i = 0; i < coef.length; i++) {
            coef[i] = Long.parseLong(temps[i].strip());
        }
        return new Polynomial(coef);
    }

    public static byte[] ParseSeed(String x) {
        String[] temps = x.substring(x.indexOf("[", 0) + 1, x.indexOf("]", 0)).split(",");
        byte[] seed = new byte[temps.length];
        for (int i = 0; i < seed.length; i++) {
            seed[i] = Byte.parseByte(temps[i].strip());
        }
        return seed;
    }

    public static int[][] ParseHint(String x) {
        int[][] hint = new int[256][4];
        String[] temps = x.split(", [");
        for (String string : temps) {
            System.out.println(string);
        }

        return hint;
    }
}
