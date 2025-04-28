import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static List<Integer> buildings = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            inputSetting();
            solution();
        }
        System.out.println(answerString);
    }

    private static void solution() throws IOException {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            if (input.charAt(0) == 'I') {
                int num = Integer.parseInt(input.substring(2, input.length()));
                maxPq.add(num);
                minPq.add(num);
                map.put(num, map.getOrDefault(num, 0) + 1);
            } else {
                if (input.charAt(2) == '-') {
                    while (!minPq.isEmpty() && map.get(minPq.peek()) == 0) minPq.poll();
                    if (!minPq.isEmpty()) {
                        int removedNum = minPq.poll();
                        map.put(removedNum, map.get(removedNum) - 1);
                    }
                } else {
                    while (!maxPq.isEmpty() && map.get(maxPq.peek()) == 0) maxPq.poll();
                    if (!maxPq.isEmpty()) {
                        int removedNum = maxPq.poll();
                        map.put(removedNum, map.get(removedNum) - 1);
                    }
                }
            }
//            System.out.println(input);
//            System.out.println(maxPq);
//            System.out.println(minPq);
        }
        while (!minPq.isEmpty() && map.get(minPq.peek()) == 0) minPq.poll();
        while (!maxPq.isEmpty() && map.get(maxPq.peek()) == 0) maxPq.poll();

        if (minPq.isEmpty()) answerString.append("EMPTY").append("\n");
        else answerString.append(maxPq.peek()).append(" ").append(minPq.peek()).append("\n");
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
    }
}
