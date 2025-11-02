package com.sweetshop.model;

import jakarta.persistence.*;

@Entity
public class Sweet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private double price;
    private int quantity;

    public Sweet() {}
    public Sweet(String name, String category, double price, int quantity){
        this.name=name; this.category=category; this.price=price; this.quantity=quantity;
    }
    public Long getId(){return id;}
    public String getName(){return name;}
    public void setName(String n){this.name=n;}
    public String getCategory(){return category;}
    public void setCategory(String c){this.category=c;}
    public double getPrice(){return price;}
    public void setPrice(double p){this.price=p;}
    public int getQuantity(){return quantity;}
    public void setQuantity(int q){this.quantity=q;}
}
