package kickstart_2020.round_c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PerfectSubarray {
    public static void main(String[] args) {
        PerfectSubarray perfectSubarray = new PerfectSubarray();

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int[] a = new int[n];

            for (int j = 0; j < n; j++) {
                a[j] = in.nextInt();
            }

            System.out.println("Case #" + i + ": " + perfectSubarray.total(a));
        }
    }

    private long total(int[] a) {
        long total = 0, sum = 0, minSum = 0;
        Map<Long, Long> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0L, 1L);

        for (int element : a) {
            sum += element;
            minSum = Math.min(sum, minSum);
            for (long i = 0; (sum - i * i) >= minSum; i++) {
                if (prefixSumMap.containsKey(sum - i * i)) {
                    total += prefixSumMap.get(sum - i * i);
                }
            }
            prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0L) + 1L);
        }
        return total;
    }
}