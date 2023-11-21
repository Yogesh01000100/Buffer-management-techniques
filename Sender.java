package IP_LAB;
import java.io.*;
import java.net.*;

class Sender {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3000);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);

            for (int i = 1; i <= 10; i++) {
                out.println("Packet " + i);
                System.out.println("Sent: Packet " + i);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
