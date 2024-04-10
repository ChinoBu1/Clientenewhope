import com.google.gson.Gson;

public class JSON {
    Gson gson = new Gson();

    public JSON() {
    }

    public String ToJson(Polynomial x, byte[] seed) {
        String json = gson.toJson(new jsonRequest(x, seed));
        return json;

    }

    public String ToJson(Polynomial x, int[][] hint) {
        String json = gson.toJson(new jsonResponse(x, hint));
        return json;

    }

    public jsonRequest msg1FromJson(String json) {
        jsonRequest msg = gson.fromJson(json, jsonRequest.class);
        return msg;

    }

    public jsonResponse msg2FromJson(String json) {
        jsonResponse msg = gson.fromJson(json, jsonResponse.class);
        return msg;

    }
}
