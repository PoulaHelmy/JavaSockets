package TaskThree;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    private BufferedReader reader;
    private DatagramSocket clientSocket;
    private byte[] arr;
    private DatagramPacket dpack_send ;
    private DatagramPacket dpack_recv ;
    InetAddress IPAddress;
    Client() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        clientSocket = new DatagramSocket();
        IPAddress = InetAddress.getByName("127.0.01");
        arr = new byte[2048];
        dpack_send=null;
        dpack_recv=null;
    }
    public static void clearBytes(byte[] arr){
        for( int i = 0 ; i < arr.length ; i ++ )
            arr[i] = '\0' ;
    }
    public static void main(String[] args) throws IOException {

        try{

            Client client=new Client();
            Scanner scan = new Scanner(System.in);
            while(true) {
                clearBytes(client.arr);
                System.out.print("Enter Message : ");
                String input=scan.nextLine();

                client.arr = (input).getBytes();
                client.dpack_send=
                        new DatagramPacket(client.arr, client.arr.length, client.IPAddress, 7575);
                client.clientSocket.send(client.dpack_send);
                if(input.equalsIgnoreCase("exit")){
                    System.out.println("Terminate Connection.......");
                    client.clientSocket.close();
                    System.out.println("Connection Closed.......");
                }
                clearBytes(client.arr);

                client.dpack_recv =
                        new DatagramPacket(client.arr, client.arr.length);
                client.clientSocket.receive(client.dpack_recv );
                System.out.println("Server : " + new String(client.arr) );

            }//end of While
        }catch (IOException e) {
            System.out.println("Error : " + e );
        }//end of Try Catch



    }
}


