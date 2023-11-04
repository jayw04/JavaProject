package project1;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private static final int MAX_SIZE = 3;
    private final Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Thread producerThread = new Thread(() -> {
            while (true) {
                pc.produce();
            }
        });
        Thread consumerThread = new Thread(() -> {
            while (true) {
                pc.consume();
            }
        });
        producerThread.start();
        consumerThread.start();
    }

    private synchronized void produce() {
        while (queue.size() == MAX_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int item = (int) (Math.random() * 15);
        queue.add(item);
        System.out.println("Produced item: " + item);
        notifyAll();
    }

    private synchronized void consume() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int item = queue.remove();
        System.out.println("Consumed item: " + item);
        notifyAll();
    }
}
