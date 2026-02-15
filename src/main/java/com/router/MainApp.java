package com.router;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.router.scheduler.EmailCleanupScheduler;
import com.router.service.InnerCircleService;
import com.router.service.MailFetcherService;

public class MainApp {

    public static void main(String[] args) {
        EmailCleanupScheduler.start();

        //  Fetch emails from Gmail
        MailFetcherService fetcher =
                new MailFetcherService();

        fetcher.fetchInbox();

        System.out.println("Email Router started successfully");
        System.out.println("Starting Mail Router...");
        new MailFetcherService().fetchInbox();
        System.out.println("Inner Circle:");
InnerCircleService.getInnerCircle(3)
        .forEach(System.out::println);


        // ✅ Clean MySQL background thread
        try {
            AbandonedConnectionCleanupThread.checkedShutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
