import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static List<int[]> connInfo = new ArrayList<>();
    private static long[] dp;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(sb);
    }

    private static void solution() {
        // DP를 이용해서 최솟값을 유지하면서 진행
        // N-1번 진행, 시작점부터 최대 가능한 끝점까지 도달하기 위해 필요한 횟수
        for (int i = 0; i < N + 1; i++) {
            dp[i] = Long.MAX_VALUE;
        }
        dp[1] = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < connInfo.size(); j++) {
                int from = connInfo.get(j)[0];
                int to = connInfo.get(j)[1];
                int val = connInfo.get(j)[2];
                if (dp[from] == Long.MAX_VALUE) continue;
                dp[to] = Math.min(dp[to], dp[from] + val);
            }
        }

        //한번 더 실행했을때에 값이 바뀐다면 순환이 존재하는 것
        for (int i = 0; i < connInfo.size(); i++) {
            int from = connInfo.get(i)[0];
            int to = connInfo.get(i)[1];
            int val = connInfo.get(i)[2];
            if (dp[from] == Long.MAX_VALUE) continue;
            if (dp[to] > dp[from] + val) {
                sb.append(-1);
                return;
            }
        }

        for (int i = 2; i < N + 1; i++) {
            sb.append((dp[i] == Long.MAX_VALUE) ? -1 : dp[i]).append("\n");
        }
        // 추가적으로 사이클이 발생했던 노드에서 DFS, BFS를 사용하면 영향받는 노드를 모두 구할 수 있음
    }


    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new long[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            connInfo.add(new int[]{from, to, val});
        }
    }
}
