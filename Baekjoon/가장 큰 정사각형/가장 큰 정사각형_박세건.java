import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static int[][] dp;
    private static int[][] map;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        inputSetting();
        findAnswer();
        System.out.println(answer * answer);
    }

    private static void findAnswer() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] == 1) {
                    dp[i][j] = getMaxSize(i, j);
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }
    }

    private static int getMaxSize(int x, int y) {
        for (int i = 1; i <= dp[x - 1][y - 1]; i++) {
            if (map[x - i][y] != 1) return i;
            if (map[x][y - i] != 1) return i;
        }
        return dp[x - 1][y - 1] + 1;
    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][M + 1];
        map = new int[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i + 1][j + 1] = tmp.charAt(j) - '0';
            }
        }
    }
}
