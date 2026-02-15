 package com.router.service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class SpamDetectorService {

    // sender → timestamps of received emails
    private static final Map<String, Deque<Long>> senderWindow =
            new HashMap<>();

    private static final int LIMIT = 10;        // emails
    private static final long WINDOW_MS = 60_000; // 1 minute

    public static synchronized boolean isSpam(String sender) {

        long now = System.currentTimeMillis();

        senderWindow.putIfAbsent(sender, new ArrayDeque<>());
        Deque<Long> timestamps = senderWindow.get(sender);

        // add current timestamp
        timestamps.addLast(now);

        // remove old timestamps outside window
        while (!timestamps.isEmpty()
                && now - timestamps.peekFirst() > WINDOW_MS) {
            timestamps.removeFirst();
        }

        // spam condition
        return timestamps.size() >= LIMIT;
    }
}
