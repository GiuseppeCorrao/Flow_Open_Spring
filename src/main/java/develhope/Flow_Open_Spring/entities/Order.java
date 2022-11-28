package develhope.Flow_Open_Spring.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;


    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private List<Product> product;


    private LocalDate date;


    private double Complessiveprice;

    private String address;



    public Order() {
    }

    public Order(Long id, User user, List<Product> product, LocalDate date, double Complessiveprice,String address) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.date = date;
        this.Complessiveprice = Complessiveprice;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public double getComplessiveprice() {
        return Complessiveprice;
    }

    public void setComplessiveprice(double complessiveprice) {
        this.Complessiveprice = complessiveprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
