import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M, W;

    private static List<int[]> connInfo;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            inputSetting();
            solution();
        }
        System.out.println(answerString);
    }

    private static void solution() {
        /*
         * 모든 주어진 간선들이 하나의 그룹에 속하는 것이 아니기 때문에 어떤 그룹이 존재하는지 판단함
         * 후에, 해당 그룹에 특정 노드의 visited값을 0으로 변환해줌
         * */
        for (int i = 1; i < N + 1; i++) {
            if (visited[i] == Integer.MAX_VALUE) {
                findConnectedNode(i);
            }
        }
        //음수 사이클을 찾기위해 다시 반복
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < connInfo.size(); j++) {
                int from = connInfo.get(j)[0];
                int to = connInfo.get(j)[1];
                int val = connInfo.get(j)[2];
                /*
                 * 두번쨰 사이클 확인을 위한 탐색할때에는 밑에 조건인 INF일떄의 탐색을 확인하지 않아도 괜찮을 거라 생각.
                 * 한번 탐색을 완료하면 모든 노드가 INF 값이 아니라고 생각
                 * 하지만, 연결이
                 * */
                if (visited[from] != Integer.MAX_VALUE) {
                    if (visited[to] > visited[from] + val) {
                        //음수 사이클 존재!
                        answerString.append("YES").append("\n");
                        return;
                    }
                }
            }
        }
        answerString.append("NO").append("\n");
    }

    private static void findConnectedNode(int x) {
        visited[x] = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < connInfo.size(); j++) {
                int from = connInfo.get(j)[0];
                int to = connInfo.get(j)[1];
                int val = connInfo.get(j)[2];
                if (visited[from] != Integer.MAX_VALUE) {
                    if (visited[to] > visited[from] + val) {
                        visited[to] = visited[from] + val;
                    }
                }
            }
        }
    }


    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        visited = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            visited[i] = Integer.MAX_VALUE;
        }
        connInfo = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            connInfo.add(new int[]{from, to, val});
            connInfo.add(new int[]{to, from, val});
        }
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            connInfo.add(new int[]{from, to, -val});
        }
    }
}
