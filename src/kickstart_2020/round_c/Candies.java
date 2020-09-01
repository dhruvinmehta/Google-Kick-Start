package kickstart_2020.round_c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Candies {
    private static final int N = 2 * 100000 + 1;
    private final long[] prefixSumTree = new long[N * 2];
    private final long[] multiplePrefixSumTree = new long[N * 2];

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            Candies candies = new Candies();
            int n = in.nextInt();
            int q = in.nextInt();

            long[] a = new long[n];
            Operation[] operations = new Operation[q];
            for (int j = 0; j < n; j++) {
                a[j] = in.nextLong();
            }

            for (int j = 0; j < q; j++) {
                operations[j] = new Operation(in.next().charAt(0), in.nextInt(), in.nextInt());
            }
            System.out.println("Case #" + i + ": " + candies.totalSweetness(a, operations));
        }
    }

    private long totalSweetness(long[] a, Operation[] operations) {
        long totalSweetness = 0;
        buildTrees(a);
        for (Operation operation : operations) {
            if (operation.type == 'U') {
                modifyTrees(a.length, operation.operand1 - 1, operation.operand2);
            } else {
                totalSweetness += queryTrees(a.length, operation.operand1 - 1, operation.operand2);
            }
        }
        return totalSweetness;
    }

    private long queryTrees(int n, int l, int r) {
        long prefixSum = 0;
        long multiplePrefixSum = 0;
        for (int i = l + n, j = r + n; i < j; i /= 2, j /= 2) {
            if ((i & 1) == 1) {
                prefixSum += prefixSumTree[i];
                multiplePrefixSum += multiplePrefixSumTree[i];
                i += 1;
            }

            if ((j & 1) == 1) {
                j -= 1;
                prefixSum += prefixSumTree[j];
                multiplePrefixSum += multiplePrefixSumTree[j];
            }
        }
        return (multiplePrefixSum - l * prefixSum) * multiplier(l);
    }

    private void modifyTrees(int n, int position, int value) {
        prefixSumTree[n + position] = multiplier(position) * value;
        multiplePrefixSumTree[n + position] = multiplier(position) * value * (position + 1);

        for (int i = n + position; i > 1; i /= 2) {
            prefixSumTree[i / 2] = prefixSumTree[i] + prefixSumTree[i ^ 1];
            multiplePrefixSumTree[i / 2] = multiplePrefixSumTree[i] + multiplePrefixSumTree[i ^ 1];
        }
    }

    public void buildTrees(long[] a) {
        for (int i = 0; i < a.length; i++) {
            prefixSumTree[a.length + i] = multiplier(i) * a[i];
            multiplePrefixSumTree[a.length + i] = multiplier(i) * a[i] * (i + 1);
        }

        for (int i = a.length - 1; i > 0; i--) {
            prefixSumTree[i] = prefixSumTree[2 * i] + prefixSumTree[2 * i + 1];
            multiplePrefixSumTree[i] = multiplePrefixSumTree[2 * i] + multiplePrefixSumTree[2 * i + 1];
        }
    }

    private int multiplier(int i) {
        return (i & 1) == 1 ? -1 : 1;
    }

    private static class Operation {
        public char type;
        public int operand1;
        public int operand2;

        Operation(char type, int operand1, int operand2) {
            this.type = type;
            this.operand1 = operand1;
            this.operand2 = operand2;
        }
    }
}
