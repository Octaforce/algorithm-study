import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;


public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static List<Integer> upBlock = new ArrayList<>();
    private static List<Integer> downBlock = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString.toString());
    }

    private static void solution() {
        upBlock.sort(Comparator.comparingInt(o -> -o));
        downBlock.sort(Comparator.comparingInt(o -> -o));
        int[] breakCnt = new int[M + 2];
        int[] tmp = new int[M + 2];
        for (int i = 0; i < upBlock.size(); i++) {
            tmp[upBlock.get(i)]++;
        }
        for (int i = M; i > 0; i--) {
            tmp[i] += tmp[i + 1];
            breakCnt[i] += tmp[i];
        }

        tmp = new int[M + 2];
        for (int i = 0; i < downBlock.size(); i++) {
            tmp[downBlock.get(i)]++;
        }
        int minVal = Integer.MAX_VALUE;
        for (int i = M; i > 0; i--) {
            tmp[i] += tmp[i + 1];
            breakCnt[M - i + 1] += tmp[i];
            minVal = Math.min(minVal, breakCnt[M - i + 1]);
        }
//        System.out.println(Arrays.toString(breakCnt));
        int minCnt = 0;
        for (int i = 1; i < breakCnt.length - 1; i++) {
            if (breakCnt[i] == minVal) minCnt++;
        }
        answerString.append(minVal).append(" ").append(minCnt);
    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(br.readLine());
            if (i % 2 == 0) downBlock.add(cur);
            else upBlock.add(cur);
        }
    }
}
