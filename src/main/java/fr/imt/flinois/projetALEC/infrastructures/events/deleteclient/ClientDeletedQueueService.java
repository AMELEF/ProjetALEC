package fr.imt.flinois.projetALEC.infrastructures.events.deleteclient;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class ClientDeletedQueueService {
    private final Queue<ClientDeletedEvent> queue = new ConcurrentLinkedQueue<>(); // File FIFO
    public void addToQueue(ClientDeletedEvent item) {
        queue.add(item);
    }
    public ClientDeletedEvent getFromQueue() {
        return queue.poll();
    }
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
