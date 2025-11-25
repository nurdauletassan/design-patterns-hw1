package com.solid.onlinelearning.services.chain;

/**
 * Base class to simplify chaining logic.
 */
public abstract class BaseEnrollmentHandler implements EnrollmentHandler {
    private EnrollmentHandler next;

    @Override
    public EnrollmentHandler setNext(EnrollmentHandler next) {
        this.next = next;
        return next;
    }

    @Override
    public void handle(EnrollmentRequest request) {
        if (request.isRejected()) {
            return;
        }
        process(request);
        if (next != null) {
            next.handle(request);
        }
    }

    protected abstract void process(EnrollmentRequest request);
}
