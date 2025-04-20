import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int T, N;
    private static int[] targets;
    private static boolean[] visited;
    private static boolean[] done;
    private static int answer;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            inputSetting();
            solution();
        }
        System.out.println(sb);
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            if (!visited[i]) dfs(i);
        }
        sb.append(N - answer).append("\n");
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        int next = targets[cur];
        if (!visited[next]) dfs(next);
        else if (!done[next]) {
            //이는 현재 탐색중인 간선 dfs 경우에 속하는 경우
            for (int i = next; i != cur; i = targets[i]) {
                answer++;
            }
            answer++;
        }
        //현재 탐색중인 DFS의 간선들인지를 확인하기 위한 done 변수
        done[cur] = true;
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        targets = new int[N];
        visited = new boolean[N];
        done = new boolean[N];
        answer = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            targets[i] = Integer.parseInt(st.nextToken()) - 1;
        }
    }
}
