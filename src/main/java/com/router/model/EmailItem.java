package com.router.model;

import com.router.graph.RelationshipGraph;

public class EmailItem implements Comparable<EmailItem> {

    private final String sender;
    private final String subject;
    private final long receivedTime;
    private final String emailType;

    public EmailItem(String sender, String subject) {
        this.sender = sender;
        this.subject = subject;
        this.receivedTime = System.currentTimeMillis();
        this.emailType = classifyEmail(subject);
    }

    private String classifyEmail(String subject) {
        String s = subject.toLowerCase();

        if (s.contains("otp") || s.contains("one time password")) {
            return "OTP_TEMP";
        }
        if (s.contains("verify") || s.contains("verification")) {
            return "VERIFICATION";
        }
        if (s.contains("bank") || s.contains("payment")) {
            return "BANK";
        }
        if (s.contains("offer") || s.contains("sale")) {
            return "PROMOTION";
        }
        return "GENERAL";
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public long getReceivedTime() {
        return receivedTime;
    }

    public String getEmailType() {
        return emailType;
    }

    // Priority calculation
    public double getPriorityScore() {

        int relationshipWeight =
                RelationshipGraph.getWeight(sender);

        long ageMillis =
                System.currentTimeMillis() - receivedTime;

        double recencyScore =
                Math.max(0, 100_000 - ageMillis) / 1000.0;

        return (relationshipWeight * 0.7)
             + (recencyScore * 0.3);
    }

    @Override
    public int compareTo(EmailItem other) {
        return Double.compare(
                other.getPriorityScore(),
                this.getPriorityScore()
        );
    }

    @Override
    public String toString() {
        return sender + " | " + subject +
               " | type=" + emailType +
               " | priority=" + getPriorityScore();
    }
}
