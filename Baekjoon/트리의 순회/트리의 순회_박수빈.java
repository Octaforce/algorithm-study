import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] in, pre, post;
    static int idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //정점의 개수
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        //인오더와 포스트오더 주어짐
        in = new int[N];
        post = new int[N];
        pre = new int[N];

        //프리오더 출력

        //입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            in[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }

        //루트는 postorder에서 구하고(맨뒤에있음)
        //분할은 inorder이용
        getPreOrder(0, N - 1, 0, N - 1);

        for (int n : pre)
            System.out.print(n + " ");

    }

    static void getPreOrder(int is, int ie, int ps, int pe) {
        // is : 인오더 시작, ie : 인오더 끝
        // ps : 포스트오더 시작, pe : 포스트오더 끝

        if (is <= ie && ps <= pe) {
            //포스트오더의 가장 오른쪽은 루트 노드
            //프리오더는 루트부터 가니까

            //루트 하나씩 찾기
            pre[idx++] = post[pe];
            int pos = is;
            //인오더에서 루트 찾기
            for (int i = is; i <= ie; i++) {
                if (in[i] == post[pe]) {
                    pos = i;
                    break;
                }
            }

            //왼쪽 자식 트리
            getPreOrder(is, pos - 1, ps, ps + pos - is - 1);
            //오른쪽 자식 트리
            getPreOrder(pos + 1, ie, ps + pos - is, pe - 1);

        }

    }
}
