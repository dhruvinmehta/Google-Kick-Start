package kickstart_2020.round_b;

import java.util.Scanner;

public class BusRoutes {
    public static void main(String[] args) {
        BusRoutes busRoutes = new BusRoutes();

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            long d = in.nextLong();

            long[] x = new long[n];
            for (int j = 0; j < n; j++) {
                x[j] = in.nextLong();
            }
            System.out.println("Case #" + i + ": " + busRoutes.findFirstDayOfTravel(x, d));
        }
    }

    private long findFirstDayOfTravel(long[] x, long d) {
        for (int i = x.length - 1; i >= 0; i--) {
            d -= d % x[i];
        }
        return d;
    }
}
