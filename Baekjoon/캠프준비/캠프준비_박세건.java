import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, L, R, X;
    private static int[] arr;
    private static boolean[] visited;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        inputSetting();
        dfs(0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        System.out.println(answer);
    }

    private static void dfs(int cnt, int startIdx, int min, int max, int sum) {
        if (sum > R) return;
        if (cnt >= 2 && max - min >= X && sum >= L) answer++;

        for (int i = startIdx; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(cnt + 1, i + 1, Math.min(min, arr[i]), Math.max(max, arr[i]), sum + arr[i]);
            visited[i] = false;
        }
    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
