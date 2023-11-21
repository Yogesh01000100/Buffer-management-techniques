package IP_LAB;
import java.util.LinkedList;
import java.util.Queue;

class DropTailBuffer {
    private int maxSize;
    private Queue<Packet> buffer;

    public DropTailBuffer(int maxSize) {
        this.maxSize = maxSize;
        this.buffer = new LinkedList<>();
    }

    public boolean enqueuePacket(Packet packet) {
        if (buffer.size() < maxSize) {
            buffer.add(packet);
            System.out.println("Enqueued packet: " + packet);
            return true;
        } else {
            System.out.println("Dropped packet: " + packet);
            return false;
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

public class DropTailBufferExample {
    public static void main(String[] args) {
        DropTailBuffer buffer = new DropTailBuffer(5); // Create a buffer with a max size of 5 packets

        // Enqueue some packets
        for (int i = 1; i <= 7; i++) {
            Packet packet = new Packet("Packet " + i);
            if (!buffer.enqueuePacket(packet)) {
                System.out.println("Buffer is full, dropping incoming packets.");
            }
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
