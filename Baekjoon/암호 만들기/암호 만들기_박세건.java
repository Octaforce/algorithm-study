import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int L, C;
    private static char[] arr;
    private static char[] result;
    private static Set<Character> vowels = new HashSet<>();

    public static void main(String[] args) throws IOException {
        inputSetting();
        makeVowels();
        findAnswerByDfs();    //탐색 시작해야하는 arr 인덱스, 현재 선택된 길이, 모음 개수, 자음 개수
    }

    private static void makeVowels() {
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
    }

    private static void findAnswerByDfs() {
        Arrays.sort(arr);
        dfs(0, 0, 0, 0);    //탐색 시작해야하는 arr 인덱스, 현재 선택된 길이, 모음 개수, 자음 개수
        System.out.println(sb.toString());

    }

    private static void dfs(int idx, int cnt, int vowelCnt, int consonantCnt) {
        if (cnt == L) {
            if (vowelCnt >= 1 && consonantCnt >= 2) addAnswerToStringBuilder();
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            result[cnt] = arr[i];
            if (vowels.contains(arr[i])) {
                dfs(i + 1, cnt + 1, vowelCnt + 1, consonantCnt);
            } else {
                dfs(i + 1, cnt + 1, vowelCnt, consonantCnt + 1);
            }
        }
    }

    private static void addAnswerToStringBuilder() {
        for (int i = 0; i < L; i++) {
            sb.append(result[i]);
        }
        sb.append("\n");
    }

    private static void inputSetting() throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        result = new char[L];
        arr = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
    }
}
