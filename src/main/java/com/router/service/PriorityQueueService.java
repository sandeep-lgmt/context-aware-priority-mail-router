package com.router.service;

import java.util.PriorityQueue;

import com.router.model.EmailItem;

public class PriorityQueueService {

    // Max-heap based on EmailItem.compareTo()
    private static final PriorityQueue<EmailItem> queue =
            new PriorityQueue<>();

    // Add email to priority queue
    public void addEmail(EmailItem email) {
        queue.offer(email);
    }

    // Get most important email
    public EmailItem getNextImportantEmail() {
        return queue.poll();
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
