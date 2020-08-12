package kickstart_2020.round_a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bundling {
    public static void main(String[] args) {
        Bundling bundling = new Bundling();

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int k = in.nextInt();

            String[] strings = new String[n];
            for (int j = 0; j < n; j++) {
                strings[j] = in.next();
            }

            System.out.println("Case #" + i + ": " + bundling.maxSumOfScore(strings, k));
        }
    }

    private int maxSumOfScore(String[] strings, int k) {
        TrieNode root = buildTrie(strings);
        return maxSumOfScore(root, k);
    }

    private int maxSumOfScore(TrieNode root, int k) {
        if (root.isLeafNode()) {
            root.setUngrouped(root.count % k);
            return (root.count / k) * root.depth;
        }
        int tempScore = 0;
        int availableForGrouping = root.count;
        for (Map.Entry<Character, TrieNode> child : root.children.entrySet()) {
            tempScore += maxSumOfScore(child.getValue(), k);
            availableForGrouping += child.getValue().ungrouped;
        }
        root.setUngrouped(availableForGrouping % k);
        return tempScore + (availableForGrouping / k) * root.depth;
    }

    private TrieNode buildTrie(String[] strings) {
        TrieNode root = new TrieNode();
        for (String s : strings) {
            TrieNode current = root;
            for (int i = 0; i < s.length(); i++) {
                current = current.children.computeIfAbsent(s.charAt(i), c -> new TrieNode());
                current.setDepth(i + 1);
            }
            current.increaseCount();
        }
        return root;
    }

    private static class TrieNode {
        public Map<Character, TrieNode> children;
        public int depth;
        public int ungrouped;
        public int count;

        public TrieNode() {
            this.children = new HashMap<>();
        }

        public void setUngrouped(int ungrouped) {
            this.ungrouped = ungrouped;
        }

        public void increaseCount() {
            this.count++;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public boolean isLeafNode() {
            return children.isEmpty();
        }
    }
}
