package kickstart_2020.round_b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class RobotPathDecoding {
    private final Map<Character, Point> direction;
    private final String moves;
    private static final long TEN_POWER_NINE = 1000000000L;

    public RobotPathDecoding(String moves) {
        this.moves = moves;
        this.direction = new HashMap<>();
        this.direction.put('N', new Point(0, -1));
        this.direction.put('E', new Point(1, 0));
        this.direction.put('W', new Point(-1, 0));
        this.direction.put('S', new Point(0, 1));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            RobotPathDecoding robotPathDecoding = new RobotPathDecoding(in.next());
            System.out.println("Case #" + i + ": " + robotPathDecoding.findPosition());
        }
    }

    private String findPosition() {
        Map<Integer, Integer> bracketMapping = buildBracketMapping();
        Point point = findPosition(bracketMapping, 0, moves.length());
        return (point.x + 1) + " " + (point.y + 1);
    }

    private Point findPosition(Map<Integer, Integer> bracketMapping, int start, int end) {
        long dx = 0, dy = 0;
        for (int i = start; i < end; i++) {
            if (isDirection(moves.charAt(i))) {
                dx = (TEN_POWER_NINE + dx + direction.get(moves.charAt(i)).x) % TEN_POWER_NINE;
                dy = (TEN_POWER_NINE + dy + direction.get(moves.charAt(i)).y) % TEN_POWER_NINE;
            } else if (Character.isDigit(moves.charAt(i))) {
                Point point = findPosition(bracketMapping, i + 2, bracketMapping.get(i + 1));
                long D = Long.parseLong(String.valueOf(moves.charAt(i)));
                dx = (TEN_POWER_NINE + dx + D * point.x) % TEN_POWER_NINE;
                dy = (TEN_POWER_NINE + dy + D * point.y) % TEN_POWER_NINE;
                i = bracketMapping.get(i + 1);
            }
        }
        return new Point(dx, dy);
    }

    private boolean isDirection(char c) {
        return c == 'N' || c == 'E' || c == 'W' || c == 'S';
    }

    private Map<Integer, Integer> buildBracketMapping() {
        Map<Integer, Integer> bracketMapping = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == '(') {
                stack.push(i);
            } else if (moves.charAt(i) == ')') {
                bracketMapping.put(stack.pop(), i);
            }
        }
        return bracketMapping;
    }

    private static class Point {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
