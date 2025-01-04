package org.example.techcare.model.customers;

public class Customers {
    private  int customer_id ;
    private String email ;
    private String name;

    public Customers() {
    }

    public Customers(int customer_id, String email, String name) {
        this.customer_id = customer_id;
        this.email = email;
        this.name = name;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
