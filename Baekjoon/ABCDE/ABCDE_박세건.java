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
    private static List<List<Integer>> connInfo = new ArrayList<>();
    private static boolean[] visited;
    private static int answer;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answer);
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            dfs(i, 0);
        }
    }

    private static void dfs(int cur, int cnt) {
        visited[cur] = true;
//        System.out.println(cur + " " + cnt);
//        System.out.println(Arrays.toString(visited));
        if (answer == 1) return;
        if (cnt == 4) {
            answer = 1;
            return;
        }
        for (int i = 0; i < connInfo.get(cur).size(); i++) {
            int next = connInfo.get(cur).get(i);
            if (visited[next]) continue;
            dfs(next, cnt + 1);
        }
        visited[cur] = false;
    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            connInfo.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            connInfo.get(from).add(to);
            connInfo.get(to).add(from);
        }
    }
}
