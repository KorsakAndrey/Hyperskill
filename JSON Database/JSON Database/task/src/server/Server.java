package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private DataInputStream input;
    private DataOutputStream output;


    public void start(String address, int port) throws IOException {
            server = new ServerSocket(port, 50, InetAddress.getByName(address));
            System.out.println("Server started!");
            Socket socket = server.accept();
            input = new DataInputStream(socket.getInputStream());
            output  = new DataOutputStream(socket.getOutputStream());

    }

    public void restart() throws IOException {
            Socket socket = server.accept();
            input = new DataInputStream(socket.getInputStream());
            output  = new DataOutputStream(socket.getOutputStream());
    }

    public DataInputStream getInput() {
        return input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public void stop() throws IOException {
        server.close();
    }
}
