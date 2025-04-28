import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static List<Integer> buildings = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        int[] result = new int[N];
        Stack<int[]> stack = new Stack<>();
        for (int i = buildings.size() - 1; i >= 0; i--) {
            int cur = buildings.get(i);
            while (!stack.isEmpty() && cur > stack.peek()[1]) {
                result[stack.peek()[0]] = i + 1;
                stack.pop();
            }
            stack.add(new int[]{i, cur});
        }
        //빈칸 채우기
        for (int i = 0; i < N; i++) {
            answerString.append(result[i]).append(" ");
        }
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings.add(Integer.parseInt(st.nextToken()));
        }
    }


}
