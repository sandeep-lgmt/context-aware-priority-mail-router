package com.router.service;

import java.util.List;

import com.router.db.RuleRepository;
import com.router.model.EmailRule;

public class RoutingEngine {

    public static String decideRoute(String sender, String subject) throws Exception {

        List<EmailRule> rules = RuleRepository.getAllRules();

        for (EmailRule rule : rules) {

            boolean senderMatch =
                    sender.toLowerCase().matches(
                            rule.getSenderPattern()
                                    .replace("%", ".*")
                                    .toLowerCase()
                    );

            boolean subjectMatch =
                    subject.toLowerCase().matches(
                            rule.getSubjectPattern()
                                    .replace("%", ".*")
                                    .toLowerCase()
                    );

            if (senderMatch && subjectMatch) {
                return rule.getRouteTo();
            }
        }

        return "INBOX"; // default
    }
}
