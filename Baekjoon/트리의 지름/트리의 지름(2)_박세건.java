import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static List<List<int[]>> connInfo = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        int[] result = findFarNode(1);
        answerString.append(findFarNode(result[0])[1]);
    }

    private static int[] findFarNode(int start) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(new int[]{start, 0});
        visited[start] = true;

        int farNode = -1;
        int farDistance = -1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int num = cur[0];
            int val = cur[1];
            if (farDistance < val) {
                farNode = num;
                farDistance = val;
            }
            for (int i = 0; i < connInfo.get(num).size(); i++) {
                int nextNum = connInfo.get(num).get(i)[0];
                int nextVal = connInfo.get(num).get(i)[1];
                if (visited[nextNum]) continue;
                visited[nextNum] = true;
                queue.add(new int[]{nextNum, val + nextVal});
            }
        }
        return new int[]{farNode, farDistance};
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N + 1; i++) {
            connInfo.add(new ArrayList<>());
        }
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int curNum = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) break;
                int distance = Integer.parseInt(st.nextToken());
                connInfo.get(curNum).add(new int[]{num, distance});
            }
        }
    }
}
