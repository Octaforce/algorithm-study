import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static List<int[]> connInfo = new ArrayList<>();
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        inputSetting();
        System.out.println(findAnswerByMst());
    }

    private static int findAnswerByMst() {
        for (int i = 0; i < N + 1; i++) {
            parents[i] = i;
        }
        Collections.sort(connInfo, Comparator.comparingInt(o -> o[2]));
        int answer = 0;
        int cnt = 0;
        for (int i = 0; i < connInfo.size(); i++) {
            if (cnt == N - 2) break;
            int[] cur = connInfo.get(i);
            int from = cur[0];
            int to = cur[1];
            int val = cur[2];
            if (isNotSameParent(from, to)) {
                union(from, to);
                answer += val;
                cnt++;
            }
        }
        return answer;
    }

    private static void union(int from, int to) {
        int fromParent = find(from);
        int toParent = find(to);
        parents[toParent] = fromParent;
    }

    private static boolean isNotSameParent(int from, int to) {
        int fromParent = find(from);
        int toParent = find(to);
        return fromParent != toParent;
    }

    private static int find(int node) {
        if (parents[node] == node) return node;
        return parents[node] = find(parents[node]);
    }


    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            connInfo.add(new int[]{from, to, val});
        }
    }
}
