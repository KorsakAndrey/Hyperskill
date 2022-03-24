package server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class Answer {
    String response = null;
    String value = null;
    String reason = null;


    public void setResponse(String response) {
        this.response = response;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public  String getAnswer() {
        JsonElement answer = new JsonObject();
        answer.getAsJsonObject().addProperty("response" , response);
        if(value != null) {
            JsonElement tempValue = JsonParser.parseString(value);
           answer.getAsJsonObject().add("value", tempValue);
        }
        if(reason != null) {
            answer.getAsJsonObject().addProperty("reason", reason);
        }
        return answer.toString();
    }
}
