package com.router.db;

import com.router.model.EmailRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RuleRepository {

    public static List<EmailRule> getAllRules() throws Exception {

        List<EmailRule> rules = new ArrayList<>();

        String sql = "SELECT * FROM email_rules ORDER BY priority DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EmailRule rule = new EmailRule();
                rule.setId(rs.getInt("id"));
                rule.setSenderPattern(rs.getString("sender_pattern"));
                rule.setSubjectPattern(rs.getString("subject_pattern"));
                rule.setRouteTo(rs.getString("route_to"));
                rule.setPriority(rs.getInt("priority"));

                rules.add(rule);
            }
        }
        return rules;
    }
}
