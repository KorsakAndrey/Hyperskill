package server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    //Server
    //TO DO: - Remake parser

    public static void main(String[] args) throws Exception {
        String address = "127.0.0.1";
        int port = 23456;
        DataInputStream input;
        DataOutputStream output;
        Server server = new Server();
        Gson gson = new Gson();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        boolean serverIsOn = true;

        File file = new File("C:\\Users\\Andrey\\IdeaProjects\\JSON Database" +
                "\\JSON Database\\task\\src\\server\\data\\db.json");
        if(!file.isFile()){
            if(!new File("C:\\Users\\Andrey\\IdeaProjects\\JSON Database\\JSON Database" +
                    "\\task\\src\\server\\data").mkdirs()) {
                System.out.println("Error create directory");
            }
            if(!new File("C:\\Users\\Andrey\\IdeaProjects\\JSON Database\\JSON Database" +
                    "\\task\\src\\server\\data\\db.json").createNewFile()) {
                System.out.println("Error create file");
            }
        }
        Database database = new Database(file, lock);

        server.start(address, port);
        while (serverIsOn){
            input = server.getInput();
            output = server.getOutput();
            JsonElement question = JsonParser.parseString(input.readUTF()).getAsJsonObject();
            serverIsOn = !Objects.equals(question.getAsJsonObject().get("type").getAsString(), "exit");
            new Thread(new Follow(database, question, output)).start();
            if(serverIsOn) {
                server.restart();
            } else {
                server.stop();
            }
        }


    }
}
