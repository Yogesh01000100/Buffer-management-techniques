package IP_LAB;
import java.util.LinkedList;
import java.util.Queue;

class DropFrontBuffer {
    private int maxSize;
    private Queue<Packet> buffer;

    public DropFrontBuffer(int maxSize) {
        this.maxSize = maxSize;
        this.buffer = new LinkedList<>();
    }

    public boolean enqueuePacket(Packet packet) {
        if (buffer.size() < maxSize) {
            buffer.add(packet);
            System.out.println("Enqueued packet: " + packet);
            return true; // Packet successfully enqueued
        } else {
            Packet oldestPacket = buffer.poll();
            System.out.println("Dropped the oldest packet: " + oldestPacket);
            buffer.add(packet);
            System.out.println("Enqueued packet: " + packet);
            return true; // Oldest packet dropped, and new packet enqueued
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

public class DropFrontBufferExample {
    public static void main(String[] args) {
        DropFrontBuffer buffer = new DropFrontBuffer(5); // Create a buffer with a max size of 5 packets

        // Enqueue some packets
        for (int i = 1; i <= 7; i++) {
            Packet packet = new Packet("Packet " + i);
            buffer.enqueuePacket(packet);
        }

        // Dequeue packets
        while (true) {
            Packet packet = buffer.dequeuePacket();
            if (packet == null) {
                System.out.println("Buffer is empty.");
                break;
            }
        }
    }
}
