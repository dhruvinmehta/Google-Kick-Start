package kickstart_2020.round_a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Plates {
    public static void main(String[] args) {
        Plates plates = new Plates();

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();

        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            int p = in.nextInt();

            int[][] stack = new int[n][k];

            for (int j = 0; j < n; j++) {
                for (int l = 0; l < k; l++) {
                    stack[j][l] = in.nextInt();
                }
            }
            System.out.println("Case #" + i + ": " + plates.maximumBeauty(stack, n, k, p));
        }
    }

    public int maximumBeauty(int[][] stack, int n, int k, int p) {
        int[][] sum = new int[n][k + 1];
        int[][] dp = new int[n + 1][p + 1];

        for (int i = 0; i < n; i++) {
            sum[i][1] = stack[i][0];
            for (int j = 2; j <= k; j++) {
                sum[i][j] = sum[i][j - 1] + stack[i][j - 1];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= p; j++) {
                for (int x = 0; x <= Math.min(j, k); x++) {
                    dp[i][j] = Math.max(dp[i][j], sum[i - 1][x] + dp[i - 1][j - x]);
                }
            }
        }
        return dp[n][p];
    }
}
