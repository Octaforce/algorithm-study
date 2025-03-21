import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M, K;
    private static boolean[][] visited;

    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        inputSetting();
        findArea();
        System.out.println(sb.toString());
    }

    private static void findArea() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    pq.add(findByDfs(i, j));
                }
            }
        }
        sb.append(pq.size()).append("\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }
    }

    private static int findByDfs(int x, int y) {
        int result = 1;
        for (int i = 0; i < 4; i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];
            if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;
            if (visited[dx][dy]) continue;
            visited[dx][dy] = true;
            result += findByDfs(dx, dy);
        }
        return result;
    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            checkVisited(x1, y1, x2, y2);
        }
    }

    private static void checkVisited(int x1, int y1, int x2, int y2) {
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                visited[i][j] = true;
            }
        }
    }
}
