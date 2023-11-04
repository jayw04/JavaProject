package project1;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

public class ProducerConsumerExample {

    private static final String QUEUE_NAME = "my-queue";

    public static void main(String[] args) throws Exception {

        // Create an AmazonSQS client.
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        // Start the producer thread.
        new Thread(() -> {
            while (true) {
                // Generate a message.
                String message = "Message " + new Random().nextInt();

                // Send the message to the queue.
                sqs.sendMessage(QUEUE_NAME, message);

                // Wait a short time before sending the next message.
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // Start the consumer thread.
        new Thread(() -> {
            while (true) {
                // Receive a message from the queue.
                ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest();
                receiveMessageRequest.setQueueUrl(QUEUE_NAME);
                receiveMessageRequest.setMaxNumberOfMessages(1);

                Message message = sqs.receiveMessage(receiveMessageRequest).getMessages().get(0);

                // Process the message.
                System.out.println("Received message: " + message.getBody());

                // Delete the message from the queue.
                sqs.deleteMessage(QUEUE_NAME, message.getReceiptHandle());
            }
        }).start();

        // Wait for the producer and consumer threads to finish.
        Thread.join();
    }
}


















1

2
Sure. Here is a simple example of a producer-consumer pattern implementation in Java using Amazon Web Services (AWS):

Java
