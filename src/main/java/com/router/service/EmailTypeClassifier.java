package com.router.service;

public class EmailTypeClassifier {

    public static String classify(String subject) {

        if (subject == null) {
            return "GENERAL";
        }

        subject = subject.toLowerCase();

        if (subject.contains("otp") || subject.contains("verification")) {
            return "OTP";
        }

        if (subject.contains("temporary") || subject.contains("expires")) {
            return "TEMP";
        }

        if (subject.contains("security")) {
            return "SECURITY";
        }

        if (subject.contains("sale") || subject.contains("offer")) {
            return "PROMOTION";
        }

        return "GENERAL";
    }
}
