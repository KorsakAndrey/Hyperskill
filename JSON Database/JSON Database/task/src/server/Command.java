package server;

import com.google.gson.JsonElement;

public class Command {
    String type;
    String[] keys;
    JsonElement value;

    public String getType() {
        return type;
    }

    public String[] getKeys() {
        return keys;
    }

    public JsonElement getValue() {
        return value;
    }

    public Command(String type) {
        this.type = type;
        this.keys = null;
        this.value = null;
    }

    public Command(String type, String[] keys){
        this.type = type;
        this.keys = keys;
        this.value = null;
    }

    public Command(String type, String[] keys, JsonElement value){
        this.type = type;
        this.keys = keys;
        this.value = value;
    }
}
