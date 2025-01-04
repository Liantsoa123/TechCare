package org.example.techcare.model.customers;

public class Customers {
    private  int customers_id ;
    private String email ;
    private String name;

    public Customers() {
    }

    public Customers(int customers_id, String email, String name) {
        this.customers_id = customers_id;
        this.email = email;
        this.name = name;
    }

    public int getCustomers_id() {
        return customers_id;
    }

    public void setCustomers_id(int customers_id) {
        this.customers_id = customers_id;
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
