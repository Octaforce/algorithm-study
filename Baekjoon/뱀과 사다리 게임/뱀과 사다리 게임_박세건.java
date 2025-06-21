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

    private static int N, M;
    private static int[] moveInfo = new int[111];
    private static int[] dp = new int[111];

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString.toString());
    }

    private static void solution() {
        int start = 1;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start, 0});
        dp[start] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curPoint = cur[0];
            int curCnt = cur[1];

//            System.out.println(Arrays.toString(cur));

            if (curPoint >= 100) {
                answerString.append(curCnt);
                return;
            }

            //바로 사다리나 뱀을 타고 이동 가능한 경우
            if (moveInfo[curPoint] != 0 && dp[moveInfo[curPoint]] > curCnt) {
                queue.add(new int[]{moveInfo[curPoint], curCnt});
                dp[moveInfo[curPoint]] = curCnt;
            }
            //주사위를 던져서 사다리나 뱀을 타고 이동 가능한 경우
            for (int dice = 1; dice <= 6; dice++) {
                int nextPoint = curPoint + dice;
                if (moveInfo[nextPoint] != 0) {
                    if (dp[moveInfo[nextPoint]] > curCnt + 1) {
                        queue.add(new int[]{moveInfo[nextPoint], curCnt + 1});
                        dp[moveInfo[nextPoint]] = curCnt + 1;
                    }
                } else {
                    if (dp[nextPoint] > curCnt + 1) {
                        queue.add(new int[]{nextPoint, curCnt + 1});
                        dp[nextPoint] = curCnt + 1;
                    }
                }
            }
//            // 최대 6칸 앞으로 이동
//            if (dp[curPoint + 6] > curCnt + 1) {
//                queue.add(new int[]{curPoint + 6, curCnt + 1});
//                dp[curPoint + 6] = curCnt + 1;
//            }
        }
    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 111; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt((st.nextToken()));
            int to = Integer.parseInt((st.nextToken()));
            moveInfo[from] = to;
        }
    }
}
