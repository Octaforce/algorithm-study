import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() throws IOException {
        int[] ab = new int[N * N];
        int[] cd = new int[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ab[i * N + j] = map[i][0] + map[j][1];
                cd[i * N + j] = map[i][2] + map[j][3];
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);

        int start = 0;
        int end = cd.length - 1;
        long result = 0;

        while (start < ab.length && end >= 0) {
            int sum = ab[start] + cd[end];

            if (sum == 0) {
                int abVal = ab[start];
                int cdVal = cd[end];
                long abCount = 0;
                long cdCount = 0;

                // ab에서 중복 개수 세기
                while (start < ab.length && ab[start] == abVal) {
                    abCount++;
                    start++;
                }

                // cd에서 중복 개수 세기
                while (end >= 0 && cd[end] == cdVal) {
                    cdCount++;
                    end--;
                }

                result += abCount * cdCount;

            } else if (sum < 0) {
                start++;
            } else { // sum > 0
                end--;
            }
        }
        answerString.append(result);
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
