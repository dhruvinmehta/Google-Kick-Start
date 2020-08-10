package kickstart_2020.round_d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AlienPiano {
    public static void main(String[] args) {
        AlienPiano alienPiano = new AlienPiano();
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int k = in.nextInt();
            int[] a = new int[k];
            for (int j = 0; j < k; j++) {
                a[j] = in.nextInt();
            }
            System.out.println("Case #" + i + ": " + alienPiano.findViolations(a));
        }
    }

    public int findViolations(int[] a) {
        int result = Integer.MAX_VALUE;
        int[][] violationMatrix = new int[a.length][4];

        for (int alienNote = 0; alienNote < 4; alienNote++)
            violationMatrix[0][alienNote] = 0;

        for (int humanNote = 1; humanNote < a.length; humanNote++) {
            for (int alienNote = 0; alienNote < 4; alienNote++) {
                int violationCount = Integer.MAX_VALUE;
                for (int previousAlienNote = 0; previousAlienNote < 4; previousAlienNote++) {
                    int penalty = violatesRule(a[humanNote - 1], a[humanNote], alienNote, previousAlienNote) ? 1 : 0;
                    violationCount = Math.min(violationCount, violationMatrix[humanNote - 1][previousAlienNote] + penalty);
                }
                violationMatrix[humanNote][alienNote] = violationCount;
            }
        }

        for (int alienNote = 0; alienNote < 4; alienNote++)
            result = Math.min(result, violationMatrix[a.length - 1][alienNote]);

        return result;
    }

    private boolean violatesRule(int previousNote, int currentNote, int currentAlienNote, int previousAlienNote) {
        return (previousNote > currentNote && previousAlienNote <= currentAlienNote)
                || (previousNote < currentNote && previousAlienNote >= currentAlienNote)
                || (previousNote == currentNote && previousAlienNote != currentAlienNote);
    }
}
