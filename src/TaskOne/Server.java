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

    public class ClientHandler extends Thread {


        final DataInputStream dis;
        final DataOutputStream dos;
        final Socket s;
        public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) throws IOException {
            this.s = s;
            this.dis = dis;
            this.dos = dos;
        }
        @Override
        public void run()
        {
            while (true)
            {
                String received;
                try {
                    dos.writeUTF("\nWelcome..... You Are Connected Successfully..\n" +
                            "What do you want?.....\nType Exit to terminate connection.");
                    received = dis.readUTF();
                    if(received.equalsIgnoreCase("Exit")){
                        System.out.println("Client " + this.s + " sends exit…");
                        System.out.println("Closing this connection.");
                        this.s.close();
                        this.dis.close();
                        this.dos.close();
                        System.out.println("Connection closed");
                        break;
                    } else if(received.equalsIgnoreCase("grade")) {
                        dos.writeUTF("Please Enter Your Name...");
                        String input = dis.readUTF();
                        double x = (int) (Math.random() * ((100 - 0) + 1)) + 0;
                        dos.writeUTF("\n-------------------\nYour Name Is : "
                                + input + "\nYour Grade IS " + x + "/100\n-------------------\n");
                        break;
                    } else{
                        dos.writeUTF("You Entered Invalid Input");
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }//end of try catch

            }//end of while

        }//end of run


    }//end of class



    public static void main(String[] args) throws IOException {

        Server s = new Server(6060);

    }//end of main


}//end of Class
