package IP_LAB;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Packet {
    private String data;

    public Packet(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}

class EarlyRandomDropBuffer {
    private int maxSize;
    private Queue<Packet> buffer;
    private Random random;

    public EarlyRandomDropBuffer(int maxSize) {
        this.maxSize = maxSize;
        this.buffer = new LinkedList<>();
        this.random = new Random();
    }

    public boolean enqueuePacket(Packet packet) {
        if (buffer.size() < maxSize) {
            buffer.add(packet);
            System.out.println("Enqueued packet: " + packet);
            return true; // Packet successfully enqueued
        } else {
            if (random.nextDouble() < 0.5) {
                Packet droppedPacket = buffer.poll();
                System.out.println("Randomly dropped packet early: " + droppedPacket);
                buffer.add(packet);
                System.out.println("Enqueued packet: " + packet);
                return true; // Random packet dropped early, and new packet enqueued
            } else {
                System.out.println("Buffer is full, and no early random drop occurred.");
                return false; // No packets were dropped early
            }
        }
    }

    public Packet dequeuePacket() {
        Packet packet = buffer.poll();
        if (packet != null) {
            System.out.println("Dequeued packet: " + packet);
        }
        return packet;
    }
}

public class EarlyRandomDropBufferF {
    public static void main(String[] args) {
        EarlyRandomDropBuffer buffer = new EarlyRandomDropBuffer(5); // Create a buffer with a max size of 5 packets

        Random random = new Random();

        // Enqueue and dequeue packets for demonstration
        for (int i = 1; i <= 7; i++) {
            Packet packet = new Packet("Packet " + i);
            buffer.enqueuePacket(packet);

            // Simulate early random packet dropping
            if (i >= 3 && random.nextDouble() < 0.3) {
                Packet dequeuedPacket = buffer.dequeuePacket();
                if (dequeuedPacket != null) {
                    System.out.println("Manually dequeued packet: " + dequeuedPacket);
                }
            }
        }

        // Dequeue any remaining packets
        while (true) {
            Packet packet = buffer.dequeuePacket();
            if (packet == null) {
                System.out.println("Buffer is empty.");
                break;
            }
        }
    }
}
