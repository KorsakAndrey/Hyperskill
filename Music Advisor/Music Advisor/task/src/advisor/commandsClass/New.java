package advisor.commandsClass;

import advisor.Api;
import advisor.GetArray;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;


public class New implements GetArray {
    @Override
    public ArrayList<String> getArray() {
        ArrayList<String> answer = new ArrayList<>();
        String request = "/v1/browse/new-releases";
        String response = Api.clientApi(request);
        if (!response.equals("error")) {
            JsonArray albums = JsonParser.parseString(response).getAsJsonObject()
                    .get("albums").getAsJsonObject().get("items").getAsJsonArray();
            for(JsonElement album : albums) {
                StringBuilder temp = new StringBuilder();
                temp.append(album.getAsJsonObject().get("name").getAsString()).append("\n");
                JsonArray artists = album.getAsJsonObject().get("artists").getAsJsonArray();
                JsonArray artistsList = new JsonArray();
                for (JsonElement artist : artists) {
                    artistsList.add(artist.getAsJsonObject().get("name").getAsString());
                }
                temp.append(artistsList.toString().replaceAll("\"", "")
                        .replaceAll(",", ", ")).append("\n");
                temp.append(album.getAsJsonObject().get("external_urls").getAsJsonObject()
                        .get("spotify").getAsString()).append("\n");
                answer.add(temp.toString());
            }
        }
        return answer;
    }
}
