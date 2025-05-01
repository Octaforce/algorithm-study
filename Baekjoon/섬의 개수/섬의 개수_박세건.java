import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;

    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public static void main(String[] args) throws IOException {
        while (true) {
            inputSetting();
            if (N == 0 && M == 0) {
                System.out.println(answerString);
                return;
            }
            solution();
        }
    }

    private static void solution() throws IOException {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j);
                    result++;
                }
            }
        }
        answerString.append(result).append("\n");
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < dir.length; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];
            if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;
            if (visited[dx][dy]) continue;
            if (map[dx][dy] == 0) continue;
            dfs(dx, dy);
        }
    }


    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
