package com.router.graph;

import java.util.HashMap;
import java.util.Map;

public class RelationshipGraph {

    // sender -> interaction weight
    private static final Map<String, Integer> interactionMap = new HashMap<>();

    // Increase weight when sender appears
    public static void recordInteraction(String sender) {
        interactionMap.put(sender,
                interactionMap.getOrDefault(sender, 0) + 1);
    }

    // Get relationship weight
    public static int getWeight(String sender) {
        return interactionMap.getOrDefault(sender, 0);
    }

    // Debug helper
    public static void printGraph() {
        System.out.println("---- Relationship Graph ----");
        interactionMap.forEach((k, v) ->
                System.out.println(k + " → " + v));
    }
}
