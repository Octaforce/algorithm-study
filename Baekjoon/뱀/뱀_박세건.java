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

    private static int N, K, L;
    private static int[][] map; //사과 : 1, 몸 : 2, 공간 : 0
    private static Queue<int[]> queue = new ArrayDeque<>(); //다음 이동에 대한 정보 [이동 시간(초), 이동 방향(0 : 왼쪽, 1: 오른쪽)

    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int[][][] nextBody;

    public static void main(String[] args) throws IOException {
        inputSetting();
        System.out.println(getAnswer());
    }

    private static int getAnswer() {
        map[0][0] = 2;
        return moveDfs(0, 0, 0, 0, 1, 0); // 매개변수 : (머리좌표 x, 머리좌표 y, 꼬리좌표 x, 꼬리좌표 y, 현재 방향, 현재시간)
    }

    private static int moveDfs(int hx, int hy, int tx, int ty, int curDir, int time) {
//        System.out.println(hx + " " + hy + " " + tx + " " + ty + " " + curDir + " " + time);
        //방향 변경의 시간과 같다면
        if (queue.size() > 0 && queue.peek()[0] == time) {
            int nextDir = queue.poll()[1];
            if (nextDir == 0) curDir = (curDir - 1 + 4) % 4;
            else curDir = (curDir + 1) % 4;
        }
        int nhx = hx + dir[curDir][0];
        int nhy = hy + dir[curDir][1];
        if (nhx < 0 || nhx >= N || nhy < 0 || nhy >= N) {
            return time + 1;
        }
        if (map[nhx][nhy] == 2) {
            return time + 1;
        }
        nextBody[hx][hy][0] = nhx;
        nextBody[hx][hy][1] = nhy;
        int ntx = tx;
        int nty = ty;
        if (map[nhx][nhy] != 1) {
            map[tx][ty] = 0;
            ntx = nextBody[tx][ty][0];
            nty = nextBody[tx][ty][1];
        }
        map[nhx][nhy] = 2;
        return moveDfs(nhx, nhy, ntx, nty, curDir, time + 1);
    }

    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        nextBody = new int[N][N][2];
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int ax = Integer.parseInt(st.nextToken()) - 1;
            int ay = Integer.parseInt(st.nextToken()) - 1;
            map[ax][ay] = 1;
        }
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken());
            int nextMove = (st.nextToken().equals("D")) ? 1 : 0;
            queue.add(new int[]{second, nextMove});
        }
    }
}
