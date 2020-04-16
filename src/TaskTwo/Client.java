package TaskTwo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket clientSocket = null;
    private DataOutputStream out;
    private DataInputStream in;

    public Client(String ip, int port) throws IOException {
        clientSocket =new Socket(ip,port);
        out = new DataOutputStream(clientSocket.getOutputStream());
        in = new DataInputStream(clientSocket.getInputStream());

    }//end of constructor


    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }//end of stopConnection

    public static void main(String[] args) throws IOException {
        Scanner input=new Scanner(System.in);
        String ip = "127.0.0.1";
        int port = 7070;
        Client client = new Client(ip, port);
        System.out.println(client.in.readUTF());
        while (true){
            System.out.println(client.in.readUTF());
            String tosend = input.nextLine();
            if(tosend.equalsIgnoreCase("Exit")){
                System.out.println("Closing this connection : " + client.clientSocket);
                client.stopConnection();
                System.out.println("Connection closed");
                break;
            } else {
                client.out.writeUTF(tosend);
                System.out.println(client.in.readUTF());
            }
        }//end of while

    }//end of main
}//end of Class
