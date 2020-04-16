package TaskOne;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket clientSocket;
    private ServerSocket serverSocket;
    private DataOutputStream out;
    private DataInputStream in;

    public Server(int serverPort) throws IOException {
        serverSocket = new ServerSocket(serverPort);
        while (true) {
            System.out.println("Waiting for client connection......");
             clientSocket = serverSocket.accept();
            System.out.println("New Client connected..."+clientSocket);

            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
            System.out.println("Assigning new thread for this client");
            // create a new thread object
            Thread t = new ClientHandler(clientSocket, in, out);
            // Invoking the start() method
            t.start();

            }//end of while


        }//end of big while..//end of constructor




    public static void main(String[] args) throws IOException {

        Server s = new Server(6060);

    }//end of main


}//end of Class
