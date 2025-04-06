import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] buildings;
    private static int[][] buildingCntByIdx;    //[보이는 빌딩 개수][가장 근접하면서 왼쪽빌딩 idx][각장 근접한 오른쪽 빌딩 idx]

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(sb.toString());

    }

    private static void solution() {
        searchBuildings(0, N, 1, 1);
        searchBuildings(N - 1, 0, -1, 2);
        findAnswer();
    }

    private static void findAnswer() {
        for (int i = 0; i < N; i++) {
            int leftIdx = buildingCntByIdx[i][1];
            int rightIdx = buildingCntByIdx[i][2];

            if (buildingCntByIdx[i][0] == 0) {
                sb.append(buildingCntByIdx[i][0]).append("\n");
                continue;
            }

            if (Math.abs(i - leftIdx) > Math.abs(i - rightIdx))
                sb.append(buildingCntByIdx[i][0]).append(" ").append(buildingCntByIdx[i][2] + 1).append("\n");
            else
                sb.append(buildingCntByIdx[i][0]).append(" ").append(buildingCntByIdx[i][1] + 1).append("\n");
        }
    }

    private static void searchBuildings(int startIdx, int endIdx, int step, int saveIdx) {
        Stack<int[]> stack = new Stack<>();
        for (int i = startIdx; (step > 0) ? i < endIdx : i >= endIdx; i += step) {
            while (!stack.isEmpty() && stack.peek()[0] <= buildings[i])
                stack.pop();

            buildingCntByIdx[i][0] += stack.size();
            if (stack.isEmpty()) buildingCntByIdx[i][saveIdx] = Integer.MAX_VALUE;
            else buildingCntByIdx[i][saveIdx] = stack.peek()[1];

            stack.add(new int[]{buildings[i], i});
        }
    }

    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        buildingCntByIdx = new int[N][3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }
    }

}
