import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static long[] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 3];
        dp[1] = dp[2] = dp[3] = 1;
        for (int i = 4; i < N + 1; i++)
            dp[i] = dp[i - 1] + dp[i - 3];
        System.out.println(dp[N]);
    }

}
