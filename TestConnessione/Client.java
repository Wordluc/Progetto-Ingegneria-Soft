import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public String name;
    public Client(Socket socket, String name){
        try{

            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.name = name;
            try{
                writer.write(name);
                writer.newLine();
                writer.flush();
            }catch (IOException E){
                closeClient();
            }
        }catch (IOException e){
            closeClient();
        }
    }
    public void closeClient(){
        try{
            socket.close();
            reader.close();
            writer.close();
        }catch (IOException E){
            System.out.println(E.getStackTrace());
        }
    }
    public void sendMessage(){

        Scanner scanner = new Scanner(System.in);
        while (socket.isConnected()){
            try {
                String msg = scanner.nextLine();
                writer.write(msg);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                closeClient();
            }
        }
    }
    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
               while (socket.isConnected()) {
                   try {
                       String msg = reader.readLine();
                       System.out.println(msg);
                   } catch (IOException E) {
                       closeClient();
                   }
               }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {

        Scanner c = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = c.nextLine();
        Socket socket = new Socket("localhost",1111);
        Client client = new Client(socket, nome);
        client.listenForMessage();
        client.sendMessage();
    }
}
