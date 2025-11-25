package com.solid.onlinelearning.services.chain;

/**
 * Builder to assemble the default approval chain.
 */
public class EnrollmentApprovalChainBuilder {

    public EnrollmentHandler buildDefault() {
        EnrollmentHandler emailValidation = new EmailValidationHandler();
        EnrollmentHandler fraud = emailValidation.setNext(new FraudFlagHandler());
        fraud.setNext(new PaymentLimitHandler(500.0));
        return emailValidation;
    }
}
