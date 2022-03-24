package server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.DataOutputStream;
import java.io.IOException;

public class Parser {

    private static Command jParser(JsonElement question){
        String type = question.getAsJsonObject().get("type").getAsString();
        JsonElement value = null;
        if(question.getAsJsonObject().has("value"))
            value = question.getAsJsonObject().get("value");
        String[] keys = null;
        if(question.getAsJsonObject().has("key"))
            if(question.getAsJsonObject().get("key").isJsonArray()){
                JsonArray array = question.getAsJsonObject().get("key").getAsJsonArray();
                int size = array.size();
                keys = new String[size];
                for (int i = 0; i < size; i++) {
                    keys[i] = array.get(i).getAsString();
                }
            } else {
                keys = new String[1];
                keys[0] = question.getAsJsonObject().get("key").getAsString();

            }
        return new Command(type, keys, value);
    }

    public static void sendAnswer(Database database, JsonElement question, DataOutputStream output) throws IOException {
        Command command = jParser(question);
        Answer serverAnswer = new Answer();
        Gson gson = new Gson();
        try{
            switch (command.getType()){
                case "set":{
                    String answer = database.set(command.getKeys(), command.getValue());
                    serverAnswer.setResponse(answer);
                    output.writeUTF(serverAnswer.getAnswer());
                    break;
                }
                case "get":{
                    String answer = database.get(command.getKeys());
                    if(answer.equals("ERROR")) {
                        serverAnswer.setResponse(answer);
                        serverAnswer.setReason("No such key");
                    } else  {
                        serverAnswer.setResponse("OK");
                        serverAnswer.setValue(answer);
                    }
                    output.writeUTF(serverAnswer.getAnswer());
                    break;
                }
                case "delete":{
                    String answer = database.delete(command.getKeys());
                    if(answer.equals("ERROR")) {
                        serverAnswer.setResponse(answer);
                        serverAnswer.setReason("No such key");
                    } else  {
                        serverAnswer.setResponse("OK");
                    }
                    output.writeUTF(serverAnswer.getAnswer());
                    break;
                }
                case "exit": {
                    serverAnswer.setResponse("OK");
                    output.writeUTF(serverAnswer.getAnswer());
                    break;
                }
            }
        } catch (NumberFormatException e){
            serverAnswer.setResponse("ERROR");
            serverAnswer.setReason("No such key");
            output.writeUTF(gson.toJson(serverAnswer));
        }

    }
}
