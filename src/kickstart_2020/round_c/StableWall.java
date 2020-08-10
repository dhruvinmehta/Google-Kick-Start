package kickstart_2020.round_c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class StableWall {
    public static void main(String[] args) {
        StableWall stableWall = new StableWall();

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int r = in.nextInt();
            int c = in.nextInt();

            char[][] wall = new char[r][c];

            for (int j = 0; j < r; j++) {
                wall[j] = in.next().toCharArray();
            }
            System.out.println("Case #" + i + ": " + stableWall.checkStability(wall));
        }
    }

    private String checkStability(char[][] wall) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (char[] tiles : wall) {
            for (char tile : tiles) {
                graph.put(tile, new LinkedHashSet<>());
            }
        }

        for (int i = 0; i < wall.length - 1; i++) {
            for (int j = 0; j < wall[0].length; j++) {
                if (wall[i][j] != wall[i + 1][j]) {
                    if (graph.get(wall[i + 1][j]).contains(wall[i][j])) {
                        return "-1";
                    } else {
                        graph.get(wall[i][j]).add(wall[i + 1][j]);
                    }
                }
            }
        }
        return topologicalSort(graph);
    }

    private String topologicalSort(Map<Character, Set<Character>> graph) {
        StringBuilder result = new StringBuilder();
        Stack<Character> topologicalOrder = new Stack<>();
        Map<Character, Boolean> visited = new HashMap<>();
        for (Character vertex : graph.keySet()) {
            visited.put(vertex, false);
        }
        for (Map.Entry<Character, Set<Character>> polyomino : graph.entrySet()) {
            Character c = polyomino.getKey();
            if (!visited.get(c)) {
                topologicalSort(visited, graph, c, topologicalOrder);
            }
        }

        for (Character c : topologicalOrder) {
            result.append(c);
        }
        return result.toString();
    }

    private void topologicalSort(Map<Character, Boolean> visited, Map<Character, Set<Character>> graph, Character c, Stack<Character> topologicalOrder) {
        visited.put(c, true);
        for (Character adj : graph.get(c)) {
            if (!visited.get(adj)) {
                topologicalSort(visited, graph, adj, topologicalOrder);
            }
        }
        topologicalOrder.push(c);
    }
}