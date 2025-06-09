import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static StringTokenizer st;
    private static StringBuilder answerString = new StringBuilder();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static List<Integer> studentNum = new ArrayList<>();
    private static Map<Integer, Set<Integer>> studentInfo = new HashMap<>();
    private static int[][] map;

    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        inputSetting();
        solution();
        System.out.println(answerString);
    }

    private static void solution() {
        for (int i = 0; i < studentInfo.size(); i++) {
            int cur = studentNum.get(i);
            List<int[]> firstPoints = getFirstPoints(cur);
//            for (int j = 0; j < firstPoints.size(); j++) {
//                System.out.println(Arrays.toString(firstPoints.get(j)));
//            }
            if (firstPoints.size() == 1) {
//                System.out.println("첫번째 규칙");
                map[firstPoints.get(0)[0]][firstPoints.get(0)[1]] = cur;
            } else {
                List<int[]> secondPoints = getSecondPoints(firstPoints);
//                for (int j = 0; j < secondPoints.size(); j++) {
//                    System.out.println(Arrays.toString(secondPoints.get(j)));
//                }
                if (secondPoints.size() == 1) {
//                    System.out.println("두번째 규칙");
                    map[secondPoints.get(0)[0]][secondPoints.get(0)[1]] = cur;
                } else {
//                    System.out.println("세번째 규칙");
                    int[] thirdPoint = getThirdPoint(secondPoints);
                    map[thirdPoint[0]][thirdPoint[1]] = cur;
                }
            }
        }
        answerString.append(getAnswer());

//            for (int ii = 0; ii < N; ii++) {
//                for (int jj = 0; jj < N; jj++) {
//                    System.out.print(map[ii][jj] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
    }

    private static int getAnswer() {
        int answer = 0;
        int[] scoreArr = {0, 1, 10, 100, 1000};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = map[i][j];
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dir[k][0];
                    int ny = j + dir[k][1];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (studentInfo.get(num).contains(map[nx][ny])) cnt++;
                }
                answer += scoreArr[cnt];
            }
        }
        return answer;
    }

    private static int[] getThirdPoint(List<int[]> secondPoints) {
        int rx = Integer.MAX_VALUE;
        int ry = Integer.MAX_VALUE;
        for (int i = 0; i < secondPoints.size(); i++) {
            int x = secondPoints.get(i)[0];
            int y = secondPoints.get(i)[1];
            if (x < rx || (x == rx && y < ry)) {
                rx = x;
                ry = y;
            }
        }
        return new int[]{rx, ry};
    }

    private static List<int[]> getSecondPoints(List<int[]> firstPoints) {
        List<int[]> secondPoints = new ArrayList<>();
        int curVal = 0;
        for (int i = 0; i < firstPoints.size(); i++) {
            int x = firstPoints.get(i)[0];
            int y = firstPoints.get(i)[1];
            int cnt = 0;
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (map[nx][ny] == 0) cnt++;
            }
            if (cnt > curVal) {
                curVal = cnt;
                secondPoints.clear();
            }
            if (cnt == curVal) secondPoints.add(new int[]{x, y});
        }
        return secondPoints;
    }

    private static List<int[]> getFirstPoints(int cur) {
        List<int[]> firstPoints = new ArrayList<>();
        int curVal = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) continue;
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dir[k][0];
                    int ny = j + dir[k][1];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (studentInfo.get(cur).contains(map[nx][ny])) cnt++;
                }
                if (cnt > curVal) {
                    curVal = cnt;
                    firstPoints.clear();
                }
                if (cnt == curVal) firstPoints.add(new int[]{i, j});
            }
        }
        return firstPoints;
    }


    private static void inputSetting() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N * N; i++) {
            Set<Integer> tmp = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            studentNum.add(Integer.parseInt(st.nextToken()));
            for (int j = 0; j < 4; j++) {
                tmp.add(Integer.parseInt(st.nextToken()));
            }
            studentInfo.put(studentNum.get(i), tmp);
        }
    }
}
