import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static long N;
    private static int[] dp;


    public static void main(String[] args) throws IOException {
        N = Long.parseLong(br.readLine());
        dp = new int[(int) N + 2];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
        }
        System.out.println(dp[(int) N]);
        for (int i = 1; i <= N; i++) {
            System.out.println(i + " " + dp[i]);
        }
    }
}
