import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static List<Integer> numbers = new ArrayList<>();
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answer);
    }

    private static void solution() {
        Collections.sort(numbers, Comparator.reverseOrder());
        int i = 0;
        for (; i < N - 1; i++) {
            if (i + 2 < N && numbers.get(i) * numbers.get(i + 1) < numbers.get(i) + numbers.get(i + 1) * numbers.get(i + 2)) {
                answer += numbers.get(i) + numbers.get(i + 1) * numbers.get(i + 2);
                i++;
            } else if (numbers.get(i) + numbers.get(i + 1) > numbers.get(i) * numbers.get(i + 1)) {
                answer += numbers.get(i) + numbers.get(i + 1);
            } else {
                answer += numbers.get(i) * numbers.get(i + 1);
            }
            i++;
        }
        if (i == N - 1) answer += numbers.get(i);
    }

    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(br.readLine()));
        }
    }

}
