package com.router.service;

import java.util.HashMap;
import java.util.Map;

public class RelationshipGraph {
    public static Map<String, Integer> getGraph() {
    return interactionMap;
}


    // senderEmail -> interaction count
    private static final Map<String, Integer> interactionMap = new HashMap<>();

    // Call this whenever an email is received
    public static void recordInteraction(String sender) {
        interactionMap.put(sender,
                interactionMap.getOrDefault(sender, 0) + 1);
    }

    // Weight between 1.0 and 5.0
    public static double getRelationshipWeight(String sender) {
        int count = interactionMap.getOrDefault(sender, 0);

        if (count >= 10) return 5.0;
        if (count >= 7)  return 4.0;
        if (count >= 4)  return 3.0;
        if (count >= 2)  return 2.0;

        return 1.0;
    }
}
