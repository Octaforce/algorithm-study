import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static char[][] map;
    private static int sx = -1, sy = -1;
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        inputSetting();
        System.out.println(getAnswerByBfs());
    }

    private static int getAnswerByBfs() {
        int answer = Integer.MAX_VALUE;
        Queue<int[]> queue = new ArrayDeque<>();
        int[][][] visited = new int[N][M][4];
        visitedSetting(visited);
        queue.add(new int[]{sx, sy, 0, -1});
        visited[sx][sy][0] = visited[sx][sy][1] = visited[sx][sy][2] = visited[sx][sy][3] = 0;
        while (queue.size() > 0) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2]; //꺽은 횟수
            int moveDir = cur[3];
            //BaseCase
            if (map[x][y] == 'C') {
                answer = Math.min(answer, cnt);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                if (moveDir != -1 && (moveDir + 2) % 4 == i) continue;  //이전 방향으로 다시 돌아가는 경우 제외
                int dx = x + dir[i][0];
                int dy = y + dir[i][1];
                if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;
                if (map[dx][dy] == '*') continue;

                if (i == moveDir && visited[dx][dy][i] > cnt) {
                    visited[dx][dy][i] = cnt;
                    queue.add(new int[]{dx, dy, cnt, i});
                } else if (i != moveDir && visited[dx][dy][i] > cnt + 1) {
                    visited[dx][dy][i] = cnt + 1;
                    queue.add(new int[]{dx, dy, cnt + 1, i});
                }
            }
        }
        return answer - 1;
    }

    private static void visitedSetting(int[][][] visited) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j][0] = visited[i][j][1] = visited[i][j][2] = visited[i][j][3] = Integer.MAX_VALUE;
            }
        }
    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String inputString = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = inputString.charAt(j);
                if (sx == -1 && map[i][j] == 'C') {
                    sx = i;
                    sy = j;
                    map[i][j] = '.';
                }
            }
        }
    }
}
