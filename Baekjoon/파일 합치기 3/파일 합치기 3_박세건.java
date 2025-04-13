import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int T, N;
    private static PriorityQueue<Long> pq;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            inputSetting();
            solution();
        }
        System.out.println(sb.toString());

    }

    private static void solution() {
        long sum = 0;
        while (pq.size() > 1) {
            long first = pq.poll();
            long second = pq.poll();
            pq.add(first + second);
            sum += first + second;
        }
        sb.append(sum).append("\n");
    }

    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }

    }
}
