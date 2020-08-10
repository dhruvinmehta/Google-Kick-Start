package kickstart_2020.round_a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Allocation {
    public static void main(String[] args) {
        Allocation allocation = new Allocation();

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int b = in.nextInt();

            int[] a = new int[n];
            for (int j = 0; j < n; j++) {
                a[j] = in.nextInt();
            }
            System.out.println("Case #" + i + ": " + allocation.maxHouses(a, b));
        }
    }

    private int maxHouses(int[] a, int b) {
        int count = 0;
        Arrays.sort(a);
        for (int price : a) {
            b -= price;
            if (b < 0) {
                break;
            }
            count++;
        }
        return count;
    }
}
