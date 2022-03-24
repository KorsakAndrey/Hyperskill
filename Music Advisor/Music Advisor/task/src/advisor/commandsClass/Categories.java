package advisor.commandsClass;

import advisor.Api;
import advisor.GetArray;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;

public class Categories implements GetArray {
    @Override
    public ArrayList<String> getArray() {
        ArrayList<String> answer = new ArrayList<>();
        String request ="/v1/browse/categories";
        String response = Api.clientApi(request);
        if (!response.equals("error")) {
            JsonArray categories = JsonParser.parseString(response).getAsJsonObject()
                    .get("categories").getAsJsonObject().get("items").getAsJsonArray();
            for (JsonElement category : categories) {
                answer.add(category.getAsJsonObject().get("name").getAsString());
            }
        }
        return answer;
    }

    public static HashMap<String,String> getMap() {
        HashMap<String,String> playlistCategories = new HashMap<>();
        String request ="/v1/browse/categories";
        String response = Api.clientApi(request);
        if (!response.equals("error")) {
            JsonArray categories = JsonParser.parseString(response).getAsJsonObject()
                    .get("categories").getAsJsonObject().get("items").getAsJsonArray();
            for (JsonElement category : categories) {
                String name = category.getAsJsonObject().get("name").getAsString();
                String id = category.getAsJsonObject().get("id").getAsString();
                playlistCategories.put(name, id);
            }
        }
        return playlistCategories;
    }
}
