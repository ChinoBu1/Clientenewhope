import com.google.gson.Gson;

public class JSON {
    Gson gson = new Gson();

    public JSON() {
    }

    public String ToJson(Polynomial x, byte[] seed) {
        String json = gson.toJson(new msg1(x, seed));
        return json;

    }

    public String ToJson(Polynomial x, int[][] hint) {
        String json = gson.toJson(new msg2(x, hint));
        return json;

    }

    public msg1 msg1FromJson(String json) {
        msg1 msg = gson.fromJson(json, msg1.class);
        return msg;

    }

    public msg2 msg2FromJson(String json) {
        msg2 msg = gson.fromJson(json, msg2.class);
        return msg;

    }
}
