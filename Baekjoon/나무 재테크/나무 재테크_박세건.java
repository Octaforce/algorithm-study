import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, K;
    private static int[][] foodMap;
    private static int[][] addFoodMap;
    private static PriorityQueue<Integer>[][] treeMap;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        for (int i = 0; i < K; i++) {
            Queue<int[]> deadTrees = startSpring();//죽은 나무를 반환
            startSummer(deadTrees);
            startAutumn();
            startWinter();
        }
        answerString.append(getTreeCnt());
    }

    private static int getTreeCnt() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt += treeMap[i][j].size();
            }
        }
        return cnt;
    }

    private static void startWinter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                foodMap[i][j] += addFoodMap[i][j];
            }
        }
    }

    private static void startAutumn() {
        int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int x : treeMap[i][j]) {
                    if (x % 5 == 0) {
                        for (int k = 0; k < dir.length; k++) {
                            int nx = i + dir[k][0];
                            int ny = j + dir[k][1];
                            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                            treeMap[nx][ny].add(1);
                        }
                    }
                }
            }
        }
    }

    private static void startSummer(Queue<int[]> deadTrees) {
        while (!deadTrees.isEmpty()) {
            int[] cur = deadTrees.poll();
            foodMap[cur[0]][cur[1]] += cur[2] / 2;
        }
    }

    private static Queue<int[]> startSpring() {
        Queue<int[]> deadTrees = new ArrayDeque<>();
        /*
         * 밑에 tmp 큐는 K만큼 생성되게 되는데 이마저 static 으로 처리해서 공유하는게 더 좋을까??
         * */
        Queue<Integer> tmp = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                PriorityQueue<Integer> pq = treeMap[i][j];
                while (!pq.isEmpty()) {
                    int curAge = pq.poll();
                    if (curAge <= foodMap[i][j]) {
                        foodMap[i][j] -= curAge;
                        tmp.add(curAge + 1);
                    } else {
//                        죽은 나무들
                        deadTrees.add(new int[]{i, j, curAge});
                    }
                }
                while (!tmp.isEmpty()) {
                    pq.add(tmp.poll());
                }
            }
        }
        return deadTrees;
    }


    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        foodMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                foodMap[i][j] = 5;
            }
        }
        addFoodMap = new int[N][N];
        treeMap = new PriorityQueue[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                treeMap[i][j] = new PriorityQueue<>();
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                addFoodMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            treeMap[x][y].add(age);
        }
    }
}
