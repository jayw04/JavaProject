package project1;
/**
 * The Producer-Consumer pattern is for a multi-process synchronization problem. The two processes: the producer and the consumer, 
 * who share a common, fixed-size buffer used as a queue.
 * The solution below uses java.util.concurrent package for synchronizing threads. We use a BlockingQueue which is thread-safe and simplifies 
 * the producer and consumer implementation by taking care of synchronization problem.
 * The BlockingQueue is a thread-safe queue that we are using for storing the
 *  data that is shared between the producer and consumer. 
 *  The LinkedBlockingQueue is one implementation that optionally has a capacity limit.
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerSample{
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(5);

        Thread producerThread = new Thread(new Producer(queue), "ProducerThread");
        Thread consumerThread = new Thread(new Consumer(queue), "ConsumerThread");

        producerThread.start();
        consumerThread.start();
    }
}