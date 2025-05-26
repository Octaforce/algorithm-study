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
    private static int N = 5;

    private static boolean[][] map;
    private static boolean[][] visited;

    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answer);
    }

    private static void solution() {
        boolean[][] visited = new boolean[N][N];
        selectByDfs(0, 0, 0, visited);
    }

    private static void selectByDfs(int start, int cnt, int yCnt, boolean[][] visited) {
        if (cnt == 7) {
            if (yCnt >= 4) {
                bfs(visited);
            } else return;
            return;
        }

        /*
         * 계속 0부터 시작하니 무한루프 발생
         * 탐색을 진행한 곳은 다시 탐색하지 않도록 처리(집합처럼)
         * */

        for (int k = start; k < N * N; k++) {
            int i = k / N;
            int j = k % N;
            if (!visited[i][j]) {
                visited[i][j] = true;
                selectByDfs(k + 1, cnt + 1, (map[i][j]) ? yCnt + 1 : yCnt, visited);
                visited[i][j] = false;
            }
        }
    }

    private static boolean bfs(boolean[][] visited) {
        boolean[][] visitedInBfs = new boolean[N][N];   //무한루프 막기 위한 visited
        int[] startPoint = findStart(visited);
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startPoint[0], startPoint[1]});
        visitedInBfs[startPoint[0]][startPoint[1]] = true;
        int cnt = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            if (cnt == 7) {
                answer++;
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                /*
                 * visited 처리되어있는 곳을 다시 되돌아가서 무한루프 발생
                 * */
                if (!visited[nx][ny]) continue;
                if (visitedInBfs[nx][ny]) continue;
                visitedInBfs[nx][ny] = true;
                queue.add(new int[]{nx, ny});
                cnt++;
            }
        }
        return false;
    }

    private static int[] findStart(boolean[][] visited) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) return new int[]{i, j};
            }
        }
        /*
         *  nullPoint 뜨면 여기
         * */
        return null;
    }


    private static void inputSetting() throws IOException {
        map = new boolean[5][5];
        visited = new boolean[5][5];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < tmp.length(); j++) {
                map[i][j] = (tmp.charAt(j) == 'S');
            }
        }

    }
}
