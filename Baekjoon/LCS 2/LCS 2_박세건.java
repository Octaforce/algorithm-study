import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String input1, input2;

    private static int[][] dp;
    private static int[][][] backTrackingDp;

    public static void main(String[] args) throws IOException {
        input1 = br.readLine();
        input2 = br.readLine();
        System.out.println(getAnswerLength());
        System.out.println(getAnswer());
    }

    private static int getAnswerLength() {
        dp = new int[input1.length() + 1][input2.length() + 1];
        backTrackingDp = new int[input1.length() + 1][input2.length() + 1][2];
        for (int i = 1; i <= input1.length(); i++) {
            for (int j = 1; j <= input2.length(); j++) {
                if (input2.charAt(j - 1) == input1.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    backTrackingDp[i][j][0] = i - 1;
                    backTrackingDp[i][j][1] = j - 1;
                } else {
                    if (dp[i][j - 1] > dp[i - 1][j]) {
                        dp[i][j] = dp[i][j - 1];
                        backTrackingDp[i][j][0] = i;
                        backTrackingDp[i][j][1] = j - 1;
                    } else {
                        dp[i][j] = dp[i - 1][j];
                        backTrackingDp[i][j][0] = i - 1;
                        backTrackingDp[i][j][1] = j;
                    }
                }
            }
        }
        return dp[input1.length()][input2.length()];
    }

    private static String getAnswer() {
        int x = input1.length();
        int y = input2.length();
        while (x != 0 && y != 0) {
            if (input1.charAt(x - 1) == input2.charAt(y - 1)) sb.append(input1.charAt(x - 1));
            int[] next = backTrackingDp[x][y];
            x = next[0];
            y = next[1];
        }
        sb.reverse();
        return sb.toString();
    }
}
