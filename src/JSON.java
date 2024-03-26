import com.google.gson.Gson;

public class JSON {
    Gson gson = new Gson();

    public JSON() {
    }

    public String ToJson(Polynomial x, byte[] seed) {
        String json = gson.toJson(new msgjson1(x, seed));
        return json;

    }

    public String ToJson(Polynomial x, int[][] hint) {
        String json = gson.toJson(new msgjson2(x, hint));
        return json;

    }

    public msgjson1 msg1FromJson(String json) {
        msgjson1 msg = gson.fromJson(json, msgjson1.class);
        return msg;

    }

    public msgjson2 msg2FromJson(String json) {
        msgjson2 msg = gson.fromJson(json, msgjson2.class);
        return msg;

    }
}
