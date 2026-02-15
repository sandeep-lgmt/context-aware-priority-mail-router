package com.router.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.router.service.EmailCleanupService;

public class EmailCleanupScheduler {

    private static final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public static void start() {

        EmailCleanupService cleanupService =
                new EmailCleanupService();

        scheduler.scheduleAtFixedRate(
            cleanupService::cleanupExpiredOtpEmails,
            0,
            1,
            TimeUnit.MINUTES
        );

        System.out.println("Email TTL Cleanup Scheduler started");
    }
}
