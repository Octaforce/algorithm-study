import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int T, N;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            inputSetting();
            solution();
        }
        System.out.println(sb);
    }

    private static void solution() {
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o1[1];
            return o1[0] - o2[0];
        });

        int result = 0;
        int min = arr[0][1];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i][1] > min) {
                result++;
            } else min = arr[i][1];
        }
        sb.append(N - result).append("\n");
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}
