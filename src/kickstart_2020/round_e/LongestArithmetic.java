package kickstart_2020.round_e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LongestArithmetic {
    public static void main(String[] args) {
        LongestArithmetic longestArithmetic = new LongestArithmetic();

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            long[] a = new long[n];

            for (int j = 0; j < n; j++) {
                a[j] = in.nextLong();
            }

            System.out.println("Case #" + i + ": " + longestArithmetic.longestContiguousSubarray(a));
        }
    }

    private int longestContiguousSubarray(long[] a) {
        int currentLength = 2;
        int maxLength = 2;
        long diff = a[1] - a[0];

        for (int i = 2; i < a.length; i++) {
            if (a[i] - a[i - 1] == diff) {
                currentLength++;
            } else {
                diff = a[i] - a[i - 1];
                currentLength = 2;
            }
            maxLength = Math.max(currentLength, maxLength);
        }
        return maxLength;
    }
}
