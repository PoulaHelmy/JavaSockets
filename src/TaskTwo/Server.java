package TaskTwo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {






    public static void main(String[] args) throws IOException {
         ServerSocket serverSocket=new ServerSocket(7070);
         System.out.println(serverSocket);
         System.out.println("Server Running ...");



         Socket clientSocket= serverSocket.accept();;
         DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());;
         DataInputStream in = new DataInputStream(clientSocket.getInputStream());;
         System.out.println("New Client connected..." + clientSocket);
         out.writeUTF("Hi From Server.....Waiting for Your Asks......");

         String input="";

        while (true) {
            out.writeUTF("Enter Your Number....Or Exit To Terminate... ");
            input=in.readUTF();
            if (input.equalsIgnoreCase("Exit")) {
                System.out.println("Server Closed ..");
                in.close();
                out.close();
                clientSocket.close();
                serverSocket.close();
                System.out.println("Connection closed");
                break;
            }else{
                int number = Integer.parseInt(input);
                boolean flag = false;
                for(int i = 2; i <= number/2; ++i) {
                    // condition for nonprime number
                    if(number % i == 0) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    if(number%2==0)
                        out.writeUTF("  : " + number + " is a Prime number And Even.");
                    else
                        out.writeUTF("  : " + number + " is a Prime number And Odd.");
                } else {
                    if(number%2==0)
                        out.writeUTF("  : " + number + " is a Not Prime number And Even.");
                    else
                        out.writeUTF("  : " + number + " is a Not Prime number And Odd.");
                }












            }

        }//end of while


    }//end of main


}//end of Class
