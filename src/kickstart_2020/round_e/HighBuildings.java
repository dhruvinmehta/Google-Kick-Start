package kickstart_2020.round_e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HighBuildings {
    public static void main(String[] args) {
        HighBuildings highBuildings = new HighBuildings();

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            System.out.println("Case #" + i + ": " + highBuildings.find(n, a, b, c));
        }
    }

    private String find(int n, int a, int b, int c) {
        int requiredBuildings = a + b - c;
        if (requiredBuildings > n || (requiredBuildings < 2 && n != requiredBuildings)) {
            return "IMPOSSIBLE";
        }

        int buildingsOnLeft = a - c;
        int buildingsOnRight = b - c;
        int tallestIndex = tallestBuildingIndex(buildingsOnLeft, buildingsOnRight);
        List<Integer> buildings = new ArrayList<>();

        for (int i = 0; i < c; i++) {
            buildings.add(n);
        }

        for (int i = 0; i < buildingsOnLeft; i++) {
            buildings.add(0, n - i - 1);
        }

        for (int i = 0; i < buildingsOnRight; i++) {
            buildings.add(n - i - 1);
        }

        for (int i = 0; i < (n - requiredBuildings); i++) {
            buildings.add(tallestIndex + i, 1);
        }
        return buildings.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    private int tallestBuildingIndex(int buildingsOnLeft, int buildingsOnRight) {
        if (buildingsOnRight != 0) {
            return buildingsOnLeft + 1;
        } else if (buildingsOnLeft != 0) {
            return buildingsOnLeft;
        } else {
            return buildingsOnLeft + 1;
        }
    }
}
