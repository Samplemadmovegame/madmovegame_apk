package com.example.madmovegame.payment.model;

public class Payment {

    private String paymentName;

    public Payment() {
    }

    public Payment(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }
}
