package kickstart_2020.round_a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Workout {
    public static void main(String[] args) {
        Workout workout = new Workout();

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int k = in.nextInt();

            long[] session = new long[n];
            for (int j = 0; j < n; j++) {
                session[j] = in.nextInt();
            }
            System.out.println("Case #" + i + ": " + workout.minimumDifficult(session, k));
        }
    }

    private long minimumDifficult(long[] session, int k) {
        long maxDifference = 0, result = Integer.MAX_VALUE;
        long[] difference = new long[session.length - 1];
        for (int i = 0; i < session.length - 1; i++) {
            difference[i] = session[i + 1] - session[i];
            maxDifference = Math.max(maxDifference, difference[i]);
        }

        long low = 0, high = maxDifference;
        while (low <= high) {
            long mid = (low + high) / 2;
            long totalAddedSession = 0;
            for (long current : difference) {
                totalAddedSession += Math.ceil((double) current / (double) mid) - 1;
            }

            if (totalAddedSession < 0) {
                high = mid - 1;
            } else if (totalAddedSession > k) {
                low = mid + 1;
            } else {
                result = Math.min(result, mid);
                high = mid - 1;
            }
        }
        return result;
    }
}
