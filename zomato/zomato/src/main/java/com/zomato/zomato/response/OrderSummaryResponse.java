package com.zomato.zomato.response;

public class OrderSummaryResponse {
    long totalAmount;

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "OrderSummaryResponse{" +
                "total_amount=" + totalAmount +
                '}';
    }
}
