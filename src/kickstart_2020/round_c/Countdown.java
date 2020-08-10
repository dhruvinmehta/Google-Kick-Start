package kickstart_2020.round_c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Countdown {
    public static void main(String[] args) {
        Countdown countdown = new Countdown();

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int k = in.nextInt();

            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = in.nextInt();
            }
            System.out.println("Case #" + i + ": " + countdown.totalCountdown(a, k));
        }
    }

    private int totalCountdown(int[] a, int k) {
        int result = 0;
        int current = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1] - 1) {
                current += 1;
            } else {
                current = 0;
            }

            if (a[i] == 1 && current >= k - 1) {
                result++;
            }
        }
        return result;
    }
}
