package client;

import com.beust.jcommander.JCommander;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.*;

public class Main {
    //Client
    public static void main(String[] args) throws IOException {
        String address = "127.0.0.1";
        int port = 23456;
        File file;
        DataInputStream input;
        DataOutputStream output;
        Client client = new Client();
        Args arguments = new Args();
        Collection<String> questions = new LinkedList<>();


        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);
        if (arguments.getFile() != null) {
            file = new File("C:\\Users\\Andrey\\IdeaProjects\\JSON Database\\JSON Database\\task\\src\\client\\data\\" , arguments.getFile());
            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()){
                questions.add(scanner.nextLine());
            }
            reader.close();
        } else {
            JsonElement question = new JsonObject();
            question.getAsJsonObject().addProperty("type", arguments.getType());
            if(arguments.getKey() != null)
                question.getAsJsonObject().addProperty("key", arguments.getKey());
            if(arguments.getValue() != null)
                question.getAsJsonObject().addProperty("value", arguments.getValue());
            questions.add(question.toString());
        }

        for (String question : questions) {
            client.connect(address, port);
            input = client.getInput();
            output = client.getOutput();

            System.out.println("Sent: " + question);
            output.writeUTF(question);
            System.out.print("Received: ");
            JsonElement answer = JsonParser.parseString(input.readUTF());
            System.out.println(answer.toString());

            client.disconnect();
        }
    }
}
