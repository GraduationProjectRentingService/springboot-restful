package org.spring.springboot.domain;

public class TotalOrderInfo {
    public int getTotalOrderMoney() {
        return totalOrderMoney;
    }

    public void setTotalOrderMoney(int totalOrderMoney) {
        this.totalOrderMoney = totalOrderMoney;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    int totalOrderMoney;
    int orderCount;
}
