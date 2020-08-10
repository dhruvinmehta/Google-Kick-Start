package kickstart_2020.round_d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RecordBreaker {
    public static void main(String[] args) {
        RecordBreaker recordBreaker = new RecordBreaker();
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int[] v = new int[n];
            for (int j = 0; j < n; j++) {
                v[j] = in.nextInt();
            }
            System.out.println("Case #" + i + ": " + recordBreaker.recordBreakingDays(v));
        }
    }

    private int recordBreakingDays(int[] v) {
        int result = 0;
        int max = -1;
        for (int i = 0; i < v.length - 1; i++) {
            if (v[i] > max && v[i] > v[i + 1])
                result++;
            max = Math.max(v[i], max);
        }
        if (v[v.length - 1] > max)
            result++;

        return result;
    }
}
