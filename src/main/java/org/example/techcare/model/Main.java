package org.example.techcare.model;

import org.example.techcare.dao.RetourDAO;
import org.example.techcare.model.component.TypeComponent;
import org.example.techcare.dao.TypeComponentDAO;
import org.example.techcare.model.retour.Retour;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Retour> repairs = new RetourDAO().getRetourByTypeComponentIdAndTypeLaptopId(2,3,true);
        for (Retour retour: repairs) {
            System.out.println(retour.getRetour_id()+"  "+retour.getRepair().getRepair_id());
        }
    }
}
