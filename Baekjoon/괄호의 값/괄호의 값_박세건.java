import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Mix {
        boolean isChar;
        char shape;
        int num;

        public Mix(char shape) {
            this.isChar = true;
            this.shape = shape;
            this.num = -1;
        }

        public Mix(int num) {
            this.isChar = false;
            this.shape = 'x';
            this.num = num;
        }

        @Override
        public String toString() {
            if (isChar) return "" + shape;
            else return "" + num;
        }
    }

    private static Stack<Mix> stack = new Stack<>();
    private static boolean isValid = true;

    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        findAnswer(input);
        int answer = getAnswer();
        System.out.println((isValid) ? answer : 0);
    }

    private static void findAnswer(String input) {
        for (int i = 0; i < input.length(); i++) {
//            System.out.println(stack);
            char cur = input.charAt(i);
            if (cur == '(' || cur == '[') stack.add(new Mix(cur));
            else {
                int sum = 0;
                while (!stack.isEmpty() && !stack.peek().isChar) {
                    sum += stack.pop().num;
                }
                if (!stack.isEmpty() && Math.abs(stack.peek().shape - cur) <= 2) {
                    int val = (stack.pop().shape == '(') ? 2 : 3;
                    stack.push(new Mix(val * ((sum == 0) ? 1 : sum)));
                } else {
                    isValid = false;
                }
            }
        }
    }

    private static int getAnswer() {
        int result = 0;
        while (!stack.isEmpty()) {
            Mix cur = stack.pop();
            if (cur.isChar) isValid = false;
            else result += cur.num;
        }
        return result;
    }

}
