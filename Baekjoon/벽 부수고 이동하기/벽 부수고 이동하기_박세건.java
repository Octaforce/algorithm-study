import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] map;
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        boolean[][][] visited = new boolean[N][M][2]; //[i][j][1] 은 벽 부쉈을때
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 1, 0});   //x좌표, y좌표, 이동횟수, 벽 부순 여부(1이면 부심)
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            int cnt = cur[2];
            int breakVal = cur[3];

//            System.out.println(cx + " " + cy + " " + cnt + " " + breakVal);
            if (cx == N - 1 && cy == M - 1) {
                answerString.append(cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dir[i][0];
                int ny = cy + dir[i][1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visited[nx][ny][breakVal]) continue;

                if (map[nx][ny] == 1) {
                    if (breakVal == 1) continue;
                    visited[nx][ny][1] = true;
                    queue.add(new int[]{nx, ny, cnt + 1, 1});
                } else {
                    visited[nx][ny][breakVal] = true;
                    queue.add(new int[]{nx, ny, cnt + 1, breakVal});
                }
            }
        }
        answerString.append(-1);
    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j) - '0';
            }
        }
    }
}
