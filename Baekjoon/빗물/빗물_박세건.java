import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        inputSetting();
        System.out.println(getAnswer());
    }

    private static int getAnswer() {
        int answer = 0;

        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int next = arr[i];
            if (stack.isEmpty() || stack.peek()[0] >= next) stack.add(new int[]{next, i});
            else {
                int preVal = -1;
                while (!stack.isEmpty() && stack.peek()[0] <= next) {
                    int[] cur = stack.pop();
                    if (stack.isEmpty()) break;
                    int beforeIdx = stack.peek()[1];
                    int beforeHeight = Math.min(next, stack.peek()[0]);
                    if (preVal != cur[0]) {
                        answer += (i - beforeIdx - 1) * (beforeHeight - cur[0]);
                    }
                }
                stack.add(new int[]{next, i});
            }
        }

        return answer;

    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
