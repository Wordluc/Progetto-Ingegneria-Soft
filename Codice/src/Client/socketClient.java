package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class socketClient {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;


    public String name;
    public socketClient(Socket socket, String name){
        try{
            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.name = name;
        }catch (IOException e){
            closeClient();
        }
    }
    public void closeClient(){
        try{
            socket.close();
            reader.close();
            writer.close();
            System.exit(0);
        }catch (IOException E){
            System.out.println(E.getStackTrace());
        }
    }
    public void sendMessage(String msg){
        try {
            writer.write(msg);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //--- Invio messaggi al server
    public void sendMessage(){
        Scanner scanner = new Scanner(System.in);
        while (socket.isConnected()){
            String msg = scanner.nextLine();
            switch (msg){
                case "!quit":{
                    write(msg);
                    closeClient();
                    break;
                }
                default:
                    write(msg);
            }
        }
    }
    public void write(String msg){
        try {
            writer.write(msg);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    //--- Ascolto messaggi dal server
    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
               while (socket.isConnected()) {
                   try {
                       String msg = reader.readLine();
                       switch (msg){
                           case "!close":{
                               closeClient();
                           }
                           default:
                               System.out.println(msg);
                       }

                   } catch (IOException E) {
                       closeClient();
                   }
               }
            }
        }).start();
    }

    private String[] pureMsg(String msg){
        String [] message;
        if(msg.startsWith("!")){
            message = msg.split(" ");

        }


        return null;
    }
    public static void main(String[] args) throws IOException {

        Scanner c = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = c.nextLine();
        Socket socket = new Socket("localhost",2222);
        socketClient client = new socketClient(socket, nome);
        client.listenForMessage();
        client.sendMessage();
    }
}
