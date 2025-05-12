import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int[] beforePoint;

    private static int totalCnt;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() throws IOException {
        Queue<int[]> queue = new ArrayDeque<>();
        //정답을 구하기 위한 정보를 저장하는 변수
        int maxTileNum = 1;
        queue.add(new int[]{0, 0});
        queue.add(new int[]{0, 1});
        visited[0][0] = visited[0][1] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
//            System.out.println(x + " " + y);
//            System.out.println(Arrays.toString(cur) + " " + getTileNum(x, y));
            int curTileNum = getTileNum(x, y);
            //정답을 구하기 위한 최대 타일 번호
            maxTileNum = Math.max(maxTileNum, curTileNum);
            for (int i = 0; i < 4; i++) {
                int dx = x + dir[i][0];
                int dy = y + dir[i][1];

                if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;
                if (visited[dx][dy]) continue;
                int nextTileNum = getTileNum(dx, dy);
                if (map[x][y] != map[dx][dy]) continue;

                visited[dx][dy] = true;
                queue.add(new int[]{dx, dy});

                if (dy - 1 >= 0 && map[dx][dy - 1] > 0 && getTileNum(dx, dy - 1) == getTileNum(dx, dy)) {
                    visited[dx][dy - 1] = true;
                    queue.add(new int[]{dx, dy - 1});
                } else if (dy < M && map[dx][dy + 1] > 0 && getTileNum(dx, dy + 1) == getTileNum(dx, dy)) {
                    visited[dx][dy + 1] = true;
                    queue.add(new int[]{dx, dy + 1});
                }

                beforePoint[nextTileNum] = curTileNum;

            }
        }
        int cnt = 1;
        Stack<Integer> stack = new Stack<>();
        while (maxTileNum != 1) {
//            System.out.println(maxTileNum);
            cnt++;
            stack.add(maxTileNum);
            maxTileNum = beforePoint[maxTileNum];
        }
        stack.add(1);
        answerString.append(cnt).append("\n");
        while (!stack.isEmpty()) {
            answerString.append(stack.pop()).append(" ");
        }
    }


    /**
     * map의 좌표로 몇번째 타일인지를 구하는 함수
     *
     * @param x
     * @param y
     * @return
     */
    private static int getTileNum(int x, int y) {
        if (x % 2 == 1) {
            return ((x - 1) / 2) * (2 * N - 1) + N + ((y - 1) / 2) + 1;
        } else {
            return (x / 2) * (2 * N - 1) + (y / 2) + 1;
        }
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = 2 * N;
        map = new int[N][M];
        visited = new boolean[N][M];
        totalCnt = (N + N - 1) * (N / 2) + (N % 2) * N;
        beforePoint = new int[totalCnt + 1];


        int tmp = 0;
        //빈칸 채우기
        for (int i = 1; i < N; i += 2) {
            map[i][0] = map[i][M - 1] = -1;
            visited[i][0] = visited[i][M - 1] = true;
        }
        for (int i = 0; i < totalCnt; i++) {
            st = new StringTokenizer(br.readLine());
            if (map[tmp / M][tmp % M] == -1) {
                map[tmp / M][tmp % M] = 0;
                tmp++;
            }
            map[tmp / M][tmp % M] = Integer.parseInt(st.nextToken());
            tmp++;
            map[tmp / M][tmp % M] = Integer.parseInt(st.nextToken());
            tmp++;
            if (tmp / M < N && map[tmp / M][tmp % M] == -1) {
                map[tmp / M][tmp % M] = 0;
                tmp++;
            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
    }
}
