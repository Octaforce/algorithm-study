import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        System.out.println(dfs(input));
    }

    private static int dfs(String cur) {
        int result = 0;
        for (int i = 0; i < cur.length(); i++) {
            if (cur.charAt(i) == '(') {
                int closeIdx = getCloseIdx(cur);
                result--;
                System.out.println((i + 1) + " " + closeIdx);
                String next = cur.substring(i + 1, closeIdx);
                System.out.println(next);
                result += (cur.charAt(i - 1) - '0') * dfs(next);
                break;
            } else {
                result++;
            }
        }
        return result;
    }

    private static int getCloseIdx(String cur) {
        for (int i = cur.length() - 1; i >= 0; i--) {
            if (cur.charAt(i) == ')') return i;
        }
        return -1;
    }

}
