package com.router.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.router.db.DBConnection;
import com.router.model.EmailItem;

public class EmailStoreService {

    public void save(EmailItem email) {

        String sql = """
            INSERT INTO emails (sender, subject, email_type)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email.getSender());
            ps.setString(2, email.getSubject());
            ps.setString(3, email.getEmailType());

            ps.executeUpdate();

            System.out.println(
                "Saved Email → " +
                email.getSender() +
                " | " +
                email.getSubject() +
                " | " +
                email.getEmailType()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
