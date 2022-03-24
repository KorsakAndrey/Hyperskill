package server;

import com.google.gson.JsonElement;

import java.io.DataOutputStream;
import java.io.IOException;

public class Follow implements Runnable{
    DataOutputStream output;
    Database database;
    JsonElement question;

    public Follow(Database database, JsonElement question, DataOutputStream output) {
        this.output = output;
        this.database = database;
        this.question = question;
    }

    @Override
    public void run() {
        try {
            Parser.sendAnswer(database, question, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
