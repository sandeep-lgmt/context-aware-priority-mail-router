package com.router.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.router.db.DBConnection;

public class EmailCleanupService {

    public void cleanupExpiredOtpEmails() {

        String sql = """
            DELETE FROM emails
            WHERE email_type = 'OTP_TEMP'
            AND created_at < NOW() - INTERVAL 10 MINUTE
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int deleted = ps.executeUpdate();

            if (deleted > 0) {
                System.out.println(
                    "TTL Cleanup → Deleted " + deleted + " OTP emails"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
