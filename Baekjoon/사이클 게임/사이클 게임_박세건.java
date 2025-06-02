import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] orders;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        for (int i = 0; i < M; i++) {
            if (find(orders[i][0]) == find(orders[i][1])) {
                answerString.append(i + 1);
                return;
            }
            union(orders[i][0], orders[i][1]);
        }
        answerString.append(0);
    }

    private static void union(int target1, int target2) {
        int parent1 = find(target1);
        int parent2 = find(target2);
        if (parent1 < parent2) parents[parent2] = parent1;
        else parents[parent1] = parent2;
    }

    private static int find(int target) {
        if (parents[target] == target) return target;
        return parents[target] = find(parents[target]);
    }


    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
        orders = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            orders[i][0] = Integer.parseInt(st.nextToken());
            orders[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}
