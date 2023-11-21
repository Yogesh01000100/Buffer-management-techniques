package IP_LAB;
import java.io.*;
import java.net.*;
class Receiver {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

            int bufferSize = 3;
            int packetsReceived = 0;

            while (packetsReceived < 10) {
                String packet = in.readLine();
                System.out.println("Received: " + packet);
                packetsReceived++;

                if (packetsReceived % bufferSize == 0) {
                    System.out.println("Sending acknowledgment to slow down.");
                }
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
