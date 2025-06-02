import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static List<List<int[]>> connInfo = new ArrayList<>();
    private static int S, E;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] visited = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            visited[i] = Integer.MAX_VALUE;
        }
        pq.add(new int[]{S, 0});
        visited[S] = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curVal = cur[1];
//            System.out.println(Arrays.toString(cur));
            if (curNode == E) {
                answerString.append(curVal);
                return;
            }

            for (int i = 0; i < connInfo.get(curNode).size(); i++) {
                int nextNode = connInfo.get(curNode).get(i)[0];
                int nextVal = connInfo.get(curNode).get(i)[1] + curVal;
                if (visited[nextNode] > nextVal) {
                    visited[nextNode] = nextVal;
                    pq.add(new int[]{nextNode, nextVal});
                }
            }
        }

    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            connInfo.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            connInfo.get(from).add(new int[]{to, val});
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

    }
}
