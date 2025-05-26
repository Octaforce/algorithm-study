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
    private static char[][] map;
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /*
     * 트리의 지름을 구하는 방식을 이용
     * 먼저 맵 전체를 탐색
     * L이 발견되는 지점 a 확인
     * a에서 bfs 사용해서 가장 멀리있는 지점 b 확인
     * b는 해당 L로 이루어진 구역에서 가장 멀리 있는(지름을 만드는 지점) 중 하나
     * b에서 bfs 사용해서 가장 멀리있는 지점 c 확인
     * b에서 c까지가 해당 L로 이루어진 구역에서의 정답
     * 해당 구역의 방문 처리 진행하고 다시 처음 반복문으로 가서 발견되지 않은 L 확인하고 로직 반복
     * => [반례 발생]
     * 트리에서의 지름 구하는 방식과는 다른문제
     * 해당 문제는 사이클이 발생할 수 있음, 때문에 트리의 지름 처럼 BFS를 두번 사용해서 구할 수 없음
     * 왜 안되는지 이야기 해보기
     * */

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    answer = Math.max(answer, getFarNode(i, j));
                }
            }
        }
        answerString.append(answer);
    }

    private static int getFarNode(int sx, int sy) {
        boolean[][] visited = new boolean[N][M];
        int result = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            int cnt = cur[2];
            result = Math.max(result, cnt);

            for (int i = 0; i < 4; i++) {
                int nx = cx + dir[i][0];
                int ny = cy + dir[i][1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 'W') continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny, cnt + 1});
            }
        }

        return result;
    }


    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }
    }
}
