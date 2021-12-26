package com.example.foodorderapp.Models;

public class OrdersModel {

    int orderImage,quantity;
    String soldItem, price, orderNumber,customerName;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrdersModel() {
        this.orderImage = 0;
        this.soldItem = "";
        this.price = "";
        this.orderNumber = "";
        this.quantity = 0;
        this.customerName="";
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public OrdersModel(int orderImage, String soldItem, String price, String orderNumber, int quantity, String customerName) {
        this.orderImage = orderImage;
        this.soldItem = soldItem;
        this.price = price;
        this.orderNumber = orderNumber;
        this.quantity = quantity;
        this.customerName=customerName;
    }



    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getSoldItem() {
        return soldItem;
    }

    public void setSoldItem(String soldItem) {
        this.soldItem = soldItem;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


}
