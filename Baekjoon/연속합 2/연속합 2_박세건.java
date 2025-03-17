import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] arr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        inputSetting();
        System.out.println(getAnswer());
    }

    private static int getAnswer() {
        int answer = Integer.MIN_VALUE;
        dp[0][0] = -987654321;
        for (int i = 1; i <= N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
            answer = Math.max(answer, Math.max(dp[i][0], dp[i][1]));
        }
//        for (int i = 1; i <= N; i++) {
//            System.out.print(dp[i][0] + " ");
//        }
//        System.out.println();
//        for (int i = 1; i <= N; i++) {
//            System.out.print(dp[i][1] + " ");
//        }
//        System.out.println();
        return answer;
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
