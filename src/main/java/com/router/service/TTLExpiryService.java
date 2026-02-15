package com.router.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.router.db.DBConnection;

public class TTLExpiryService {

    public static void expireOldEmails() {

        String sql = """
            UPDATE emails
            SET is_expired = true
            WHERE email_type IN ('OTP', 'TEMP')
              AND created_at < NOW() - INTERVAL 10 MINUTE
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int expired = ps.executeUpdate();
            System.out.println("TTL expired emails: " + expired);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
