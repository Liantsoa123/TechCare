package org.example.techcare.model;

import org.example.techcare.model.component.TypeComponent;
import org.example.techcare.dao.TypeComponentDAO;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TypeComponent> repairs = new TypeComponentDAO().getAllTypeComponents();
        for (TypeComponent repair : repairs) {
            System.out.println(repair.getName());
        }

    }
}
