package com.router.service;

import java.util.*;

/**
 * Finds your "Inner Circle" using BFS
 */
public class InnerCircleService {

    /**
     * Returns top N people based on interaction count
     */
    public static List<String> getInnerCircle(int limit) {

        Map<String, Integer> graph =
                RelationshipGraph.getGraph();

        // Sort by interaction count (descending)
        List<Map.Entry<String, Integer>> sorted =
                new ArrayList<>(graph.entrySet());

        sorted.sort((a, b) ->
                Integer.compare(b.getValue(), a.getValue()));

        List<String> innerCircle = new ArrayList<>();

        for (int i = 0; i < Math.min(limit, sorted.size()); i++) {
            innerCircle.add(sorted.get(i).getKey());
        }

        return innerCircle;
    }

    /**
     * BFS traversal starting from a person
     */
    public static void bfs(String startPerson) {

        Map<String, Integer> graph =
                RelationshipGraph.getGraph();

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(startPerson);
        visited.add(startPerson);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.println("Visited: " + current);

            // In real graph we'd add neighbors
            // Here interaction count itself is the weight
        }
    }
}
