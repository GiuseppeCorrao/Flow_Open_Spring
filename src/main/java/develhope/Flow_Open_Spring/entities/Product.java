package develhope.Flow_Open_Spring.entities;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double priceDelivery;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Brand brand;

    public Product() {
    }

    public Product(long id, String color, double price, String description, String name, int quantity, double priceDelivery, Brand brand) {
        this.id = id;
        this.color = color;
        this.price = price;
        this.description = description;
        this.name = name;
        this.quantity = quantity;
        this.priceDelivery = priceDelivery;
        this.brand = brand;
    }

    public Product(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceDelivery() {
        return priceDelivery;
    }

    public void setPriceDelivery(double priceDelivery) {
        this.priceDelivery = priceDelivery;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
