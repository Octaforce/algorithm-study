import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) {
//                return -(o1[0] - o2[0]); -> 왜 답이 아닐까ㅏ
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int start = arr[0][0];
        int end = arr[0][1];
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (end > arr[i][0]) continue;
            else {
                cnt++;
                start = arr[i][0];
                end = arr[i][1];
            }
        }
        answerString.append(cnt);
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
