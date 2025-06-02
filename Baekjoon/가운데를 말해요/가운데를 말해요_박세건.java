import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    private static int[] arr;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        PriorityQueue<Integer> decreasePq = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> increasePq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            decreasePq.add(arr[i]);
            if (decreasePq.size() == increasePq.size() + 2) {
                increasePq.add(decreasePq.poll());
                answerString.append(decreasePq.peek() < increasePq.peek() ? decreasePq.peek() : increasePq.peek());
            } else if (decreasePq.size() == increasePq.size() + 1) {
                increasePq.add(decreasePq.poll());
                decreasePq.add(increasePq.poll());
                answerString.append(decreasePq.peek());
            }
            answerString.append("\n");
        }
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }
}
