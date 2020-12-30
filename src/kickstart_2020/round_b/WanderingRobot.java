package kickstart_2020.round_b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class WanderingRobot {
    private final static double[] FACTORIAL = new double[200001];

    private static void computeLogFactorial() {
        for (int i = 1; i < FACTORIAL.length; i++) {
            FACTORIAL[i] = FACTORIAL[i - 1] + Math.log(i);
        }
    }

    public static void main(String[] args) {
        computeLogFactorial();
        WanderingRobot wanderingRobot = new WanderingRobot();

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int w = in.nextInt() - 1;
            int h = in.nextInt() - 1;
            int l = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            int d = in.nextInt() - 1;
            System.out.println("Case #" + i + ": " + wanderingRobot.getProbability(w, h, l, u, r, d));
        }
    }

    private double getProbability(int w, int h, int l, int u, int r, int d) {
        double probability = 0;
        if ((l == 0 && r == w) || (u == 0 && d == h)) {
            return probability;
        }

        if (u != 0) {
            for (int x = l; x <= r && x < w; x++) {
                probability += calculateProbability(x, u - 1);
            }
        }

        if (l != 0) {
            for (int y = u; y <= d && y < h; y++) {
                probability += calculateProbability(l - 1, y);
            }
        }
        if (r == w) {
            for (int y = 0; y < u; y++) {
                probability += calculateProbability(r - 1, y);
            }
        }

        if (d == h) {
            for (int x = 0; x < l; x++) {
                probability += calculateProbability(x, h - 1);
            }
        }
        return 1.0 - probability / 2.0;
    }

    private double calculateProbability(int num1, int num2) {
        return Math.exp(FACTORIAL[num1 + num2] - FACTORIAL[num1] - FACTORIAL[num2] - (num1 + num2) * Math.log(2d));
    }
}