package project1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SampleProducerConsumer {

    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        // Create a producer thread
        Thread producer = new Thread(() -> {
            while (true) {
                // Produce some data
                String data = "Hello, world!";

                // Put the data in the queue
                try {
                    queue.put(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start the producer thread
        producer.start();

        // Create a consumer thread
        Thread consumer = new Thread(() -> {
            while (true) {
                // Take some data from the queue
                String data = null;
                try {
                    data = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Consume the data
                System.out.println("Consumed data: " + data);
            }
        });

        // Start the consumer thread
        consumer.start();

        // Wait for the producer and consumer threads to finish
        //producer.join();
        //consumer.join();
    }
}