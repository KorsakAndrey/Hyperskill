package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class Client {
    private DataInputStream input;
    private DataOutputStream output;
    private Socket socket;

    public void connect(String address, int port) {
        try {
            socket = new Socket(InetAddress.getByName(address), port);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        }catch (Exception e){
            System.out.println("Connection error!");
            System.exit(1);
        }
        System.out.println("Client started!");
    }

    public void disconnect() throws IOException {
        socket.close();
    }

    public DataInputStream getInput() {
        return input;
    }

    public DataOutputStream getOutput() {
        return output;
    }
}
