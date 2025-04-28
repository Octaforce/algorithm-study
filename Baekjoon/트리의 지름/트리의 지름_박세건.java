import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

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
        boolean[] visited = new boolean[N + 1];
        //1에서 가장 멀리 떨어진 노드 찾기
        int[] tmp = new int[]{0, 0};
        tmp = getFarNode(1, 0, tmp, visited);

        Arrays.fill(visited, false);
        int[] result = getFarNode(tmp[0], 0, tmp, visited);
        answerString.append(result[1]);

    }

    private static int[] getFarNode(int num, int val, int[] result, boolean[] visited) {
        visited[num] = true;
        if (result[1] < val) {
            result[0] = num;
            result[1] = val;
        }
        for (int i = 0; i < connInfo.get(num).size(); i++) {
            int[] next = connInfo.get(num).get(i);
            if (visited[next[0]]) continue;
            getFarNode(next[0], val + next[1], result, visited);
        }
        return result;
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            connInfo.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            connInfo.get(parent).add(new int[]{child, val});
            connInfo.get(child).add(new int[]{parent, val});
        }
    }


}
