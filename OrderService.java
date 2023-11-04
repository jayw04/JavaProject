/**
 * 
 */
import jakka.actor.ActorSystem
package project1;

/**
 * 
 */
public class OrderService {
	// Create a separate actor system for each isolated service.
	ActorSystem orderServiceActorSystem = ActorSystem.create("order-service");
	ActorSystem paymentServiceActorSystem = ActorSystem.create("payment-service");

	// Create actors to communicate between the isolated services.
	ActorRef orderServiceActor = orderServiceActorSystem.actorOf(Props.create(OrderServiceActor.class));
	ActorRef paymentServiceActor = paymentServiceActorSystem.actorOf(Props.create(PaymentServiceActor.class));

	// Start the actor systems.
	orderServiceActorSystem.start();
	paymentServiceActorSystem.start();

	// Send a message from the order service actor to the payment service actor.
	orderServiceActor.tell(new OrderPlacedMessage(), orderServiceActor);

}

//Create a separate thread pool for each isolated service.
ExecutorService orderServiceThreadPool = Executors.newFixedThreadPool(10);
ExecutorService paymentServiceThreadPool = Executors.newFixedThreadPool(10);

//Create a separate queue for each isolated service.
BlockingQueue<OrderMessage> orderServiceQueue = new LinkedBlockingQueue<>();
BlockingQueue<PaymentMessage> paymentServiceQueue = new LinkedBlockingQueue<>();

//Create a thread to consume messages from the order service queue.
orderServiceThreadPool.execute(() -> {
 while (true) {
     OrderMessage orderMessage = orderServiceQueue.take();
     // Process the order message.
 }
});

//Create a thread to consume messages from the payment service queue.
paymentServiceThreadPool.execute(() -> {
 while (true) {
     PaymentMessage paymentMessage = paymentServiceQueue.take();
     // Process the payment message.
 }
});

//Send a message to the order service queue.
orderServiceQueue.put(new OrderPlacedMessage());

//Send a message to the payment service queue.
paymentServiceQueue.put(new PaymentMessage());
