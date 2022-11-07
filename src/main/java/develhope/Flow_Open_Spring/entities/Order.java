package develhope.Flow_Open_Spring.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.PERSIST) //because when this variable don't exist, the order can't exist
    private User user;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Product> product;


    private LocalDate date;


    private double price;



    public Order() {
    }

    public Order(long id, User user, List<Product> product, LocalDate date, double price) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.date = date;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
