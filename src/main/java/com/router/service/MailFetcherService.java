package com.router.service;

import java.util.Properties;

import com.router.model.EmailItem;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.InternetAddress;

public class MailFetcherService {

    public void fetchInbox() {

        // ⚠️ DO NOT add spaces
        String gmail = "sk8825690@gmail.com";
        String appPassword = "extd gbxu suww fwtu";

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");

        try {
            Session session = Session.getInstance(props);
            Store store = session.getStore();
            store.connect("imap.gmail.com", gmail, appPassword);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();

            EmailStoreService storeService = new EmailStoreService();
            PriorityQueueService pqService = new PriorityQueueService();

            for (int i = messages.length - 1;
                 i >= Math.max(0, messages.length - 10);
                 i--) {

                Message msg = messages[i];

                //  sender
                String sender =
                        ((InternetAddress) msg.getFrom()[0])
                                .getAddress();

                //  SPAM CHECK (Sliding Window)
                if (SpamDetectorService.isSpam(sender)) {
                    System.out.println("🚫 SPAM BLOCKED → " + sender);
                    continue;
                }

                //  Relationship Graph
                RelationshipGraph.recordInteraction(sender);
                double relationshipWeight =
                        RelationshipGraph.getRelationshipWeight(sender);

                //  Recency (simple for now)
                double recencyScore = 1.0;

                double priority =
                        (relationshipWeight * 0.7) +
                        (recencyScore * 0.3);

                String subject = msg.getSubject();

                String route =
                        RoutingEngine.decideRoute(sender, subject);

                System.out.println("From: " + sender);
                System.out.println("Subject: " + subject);
                System.out.println("Priority: " + priority);
                System.out.println("Route: " + route);
                System.out.println("--------------------------------");

                //  EmailItem has ONLY (sender, subject)
                EmailItem emailItem =
                        new EmailItem(sender, subject);

                // Priority Queue
                pqService.addEmail(emailItem);

                // Store in DB
                storeService.save(emailItem);
            }

            inbox.close();
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
