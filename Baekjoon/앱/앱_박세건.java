import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, K;
    private static int[] memory;
    private static int[] price;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() throws IOException {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < (N + 1) * 111; j++) {
                dp[i][j] = dp[i - 1][j];
                if (price[i] <= j)
                    dp[i][j] = Math.max(dp[i - 1][j], memory[i] + dp[i - 1][j - price[i]]);
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < (N + 1) * 111; j++) {
                if (dp[i][j] >= M) {
                    result = Math.min(result, j);
                }
            }
        }
        answerString.append(result);
//        for (int i = 0; i <= N; i++) {
//            for (int j = 0; j < 20; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
    }


    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memory = new int[N + 1];
        price = new int[N + 1];
        dp = new int[N + 1][(N + 1) * 111];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

    }
}
