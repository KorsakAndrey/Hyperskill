package advisor.commandsClass;

import advisor.Api;
import advisor.GetArray;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class Featured implements GetArray {
    @Override
    public ArrayList<String> getArray() {
        ArrayList<String> answer = new ArrayList<>();
        String request ="/v1/browse/featured-playlists";
        String response = Api.clientApi(request);
        if(!response.equals("error")) {
            JsonArray playlists = JsonParser.parseString(response).getAsJsonObject()
                    .get("playlists").getAsJsonObject().get("items").getAsJsonArray();
            for (JsonElement playlist : playlists) {
                StringBuilder temp = new StringBuilder();
                temp.append(playlist.getAsJsonObject().get("name").getAsString()).append("\n");
                temp.append(playlist.getAsJsonObject().get("external_urls").getAsJsonObject()
                        .get("spotify").getAsString()).append("\n");
                answer.add(temp.toString());
            }
        }
        return answer;
    }
}
