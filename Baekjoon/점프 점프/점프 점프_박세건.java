import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] arr;
    private static int answer;
    private static int start;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        inputSetting();
        visited[start - 1] = true;
        answer++;
        searchByDfs(start - 1);
        System.out.println(answer);
    }

    private static void searchByDfs(int cur) {
        if (isNextPossible(cur - arr[cur])) searchByDfs(cur - arr[cur]);
        if (isNextPossible(cur + arr[cur])) searchByDfs(cur + arr[cur]);
    }

    private static boolean isNextPossible(int next) {
        if (next < 0 || next >= N) return false;
        if (visited[next]) return false;
        visited[next] = true;
        answer++;
        return true;
    }

    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        start = Integer.parseInt(br.readLine());
    }
}
