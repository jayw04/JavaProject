package project1;

import java.util.concurrent.Callable;

public class CircuitBreaker {

    private enum State {
        CLOSED,
        OPEN,
        HALF_OPEN
    }

    private State state = State.CLOSED;
    private int failureThreshold = 5;
    private int successThreshold = 3;
    private long timeout = 1000; // milliseconds

    public <T> T execute(Callable<T> callable) throws Exception {
        if (state == State.OPEN) {
            throw new CircuitBreakerOpenException();
        }

        try {
            return callable.call();
        } catch (Exception e) {
            if (state == State.CLOSED) {
                state = State.HALF_OPEN;
                return callable.call();
            } else if (state == State.HALF_OPEN) {
                state = State.OPEN;
                throw new CircuitBreakerOpenException();
            } else {
                throw e;
            }
        } finally {
            if (state == State.HALF_OPEN && !isTimeout()) {
                state = State.CLOSED;
            }
        }
    }

    private boolean isTimeout() {
        return System.currentTimeMillis() - stateChangedTime > timeout;
    }

    private static class CircuitBreakerOpenException extends Exception {
        public CircuitBreakerOpenException() {
            super("Circuit breaker is open");
        }
    }
}