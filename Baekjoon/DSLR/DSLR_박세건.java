import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static int A, B;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            inputSetting();
            solution();
            System.out.println(answerString.toString());
            answerString.setLength(0);
        }
    }

    private static void solution() {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] visited = new int[11111];
        char[] backTracking = new char[11111];
        for (int i = 0; i < 11111; i++) {
            visited[i] = -1;
        }
        queue.add(A);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
//            System.out.println(cur + " " + B);
            if (cur == B) {
                while (cur != A) {
//                    System.out.println("마무리 : " + cur);
                    answerString.append(backTracking[cur]);
                    cur = visited[cur];
                }
                answerString.reverse();
                return;
            }
            int afterD = (2 * cur) % 10000;
            int afterS = (cur == 0) ? 9999 : cur - 1;
            int afterL = moveLeft(cur);
            int afterR = moveRight(cur);

            if (visited[afterD] == -1) {
                visited[afterD] = cur;
                backTracking[afterD] = 'D';
                queue.add(afterD);
            }
            if (visited[afterS] == -1) {
                visited[afterS] = cur;
                backTracking[afterS] = 'S';
                queue.add(afterS);
            }
            if (visited[afterL] == -1) {
                visited[afterL] = cur;
                backTracking[afterL] = 'L';
                queue.add(afterL);
            }
            if (visited[afterR] == -1) {
                visited[afterR] = cur;
                backTracking[afterR] = 'R';
                queue.add(afterR);
            }

        }
    }

    private static int moveRight(int cur) {
        int tmp = cur % 10;
        cur /= 10;
        return cur + tmp * 1000;
    }

    private static int moveLeft(int cur) {
        int tmp = cur / 1000;
        cur %= 1000;
        return cur * 10 + tmp;
    }


    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
    }
}
