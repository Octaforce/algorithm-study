import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] arr;
    private static int[] target = new int[3];

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        int result = findByDFS(0, 0, 0, 0, 0);
        answerString.append(result);
    }

    private static int findByDFS(int start, int cnt, int r, int g, int b) {
//        System.out.println(start + " " + cnt + " " + r + " " + g + " " + b);
        int result = Integer.MAX_VALUE;
        if (cnt > 7) return result;

        if (cnt > 1) {
            result = Math.abs(target[0] - r / cnt) + Math.abs(target[1] - g / cnt) + Math.abs(target[2] - b / cnt);
        }


        for (int i = start; i < N; i++) {
            result = Math.min(result, findByDFS(i + 1, cnt + 1, r + arr[i][0], g + arr[i][1], b + arr[i][2]));
        }
        return result;
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        target[0] = Integer.parseInt(st.nextToken());
        target[1] = Integer.parseInt(st.nextToken());
        target[2] = Integer.parseInt(st.nextToken());
    }
}
