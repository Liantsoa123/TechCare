package org.example.techcare.dao;

import org.example.techcare.model.utils.ConnectionBdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public  boolean signIn(String email, String password) throws  Exception {
        String sql = "SELECT user_id FROM _user_ WHERE email = ? AND password = ?";
        try (Connection conn = new ConnectionBdd().getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}
