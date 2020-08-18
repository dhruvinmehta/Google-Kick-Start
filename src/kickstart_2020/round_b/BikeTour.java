package kickstart_2020.round_b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BikeTour {
    public static void main(String[] args) {
        BikeTour bikeTour = new BikeTour();

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int[] h = new int[n];
            for (int j = 0; j < n; j++) {
                h[j] = in.nextInt();
            }
            System.out.println("Case #" + i + ": " + bikeTour.findPeaks(h));
        }
    }

    private int findPeaks(int[] h) {
        int peaks = 0;
        for (int i = 1; i < h.length - 1; i++) {
            if (h[i - 1] < h[i] && h[i] > h[i + 1]) {
                peaks++;
            }
        }
        return peaks;
    }
}
