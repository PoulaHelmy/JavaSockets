package TaskThree;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    private DatagramSocket serverSocket;
    private byte[] arr;
    private DatagramPacket dpack_send ;
    private DatagramPacket dpack_recv ;
    Server(int port) throws SocketException {
        serverSocket = new DatagramSocket(port);
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
        Server server=new Server(7575);

        while(true)
        {
            clearBytes(server.arr);

            server.dpack_recv = new DatagramPacket(server.arr, server.arr.length);
            server.serverSocket.receive(server.dpack_recv);
            InetAddress SerderIp = server.dpack_recv.getAddress();
            int SnderPort = server.dpack_recv.getPort();
            String input = new String(server.arr);

            if(!input.equalsIgnoreCase("exit")){
                clearBytes(server.arr);
                String sendingSentence ="\nIP Address is : "+SerderIp+"\nPort Number is : "+SnderPort;
                server.arr = sendingSentence.getBytes();
                server.dpack_send= new
                        DatagramPacket( server.arr,  server.arr.length, SerderIp, SnderPort);
                server.serverSocket.send(server.dpack_send);
            }else{
                System.out.println("Terminate Connection.......");
                server.serverSocket.close();
                System.out.println("Connection Closed.......");
            }


    }//end of while
       }catch (IOException e) {
           System.out.println("Error : " + e );
       }//end of Try Catch



    }//end of Main
}//end of Class
