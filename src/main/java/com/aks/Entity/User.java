package com.aks.Entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, unique = true)
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Email(message = "Please enter correct email-id.")
    @Column(name = "EMAIL",unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "LANGUAGE")
    private String language;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    private List<Order> orders=new ArrayList<Order>();

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "SUBS_ID", referencedColumnName = "SUBS_ID")
    private Subscription subscription;

    /**
     * Default Constructor
     */
    public User() {
    }

    /**
     *
     * @param name
     * @param email
     * @param password
     * @param phone
     * @param language
     * @param subscription
     */
    public User(String name, String email, String password, String phone, String language, Subscription subscription) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.language = language;
        this.subscription = subscription;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", language='" + language + '\'' +
                ", orders=" + orders +
                ", subscription=" + subscription +
                '}';
    }
}
