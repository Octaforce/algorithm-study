import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
    int next;
    int dist;

    Node(int next, int dist){
        this.next = next;
        this.dist = dist;
    }
}

public class Main {
    static List<List<Node>> tree;
    static int maxDist, maxNode;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //정점의 개수
        int V = Integer.parseInt(br.readLine());
        StringTokenizer st;

        //ArrayList는 내부적으로 배열을 이용해서 접근시 O(1)
        //LinkedList는 노드를 하나씩 따라가야해서 접근시 O(n)
        //삽입 삭제가 많은 경우에는 LinkedList가 유리함 (O(1))
        //지금은 한 번만 삽입하고 조회를 자주 하니, ArrayList가 유리
        tree = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            tree.add(new ArrayList<>());
        }

        //입력
        for(int i=0;i<V;i++){
            st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());

            while(true){
                int next = Integer.parseInt(st.nextToken());
                if (next == -1)
                    break;
                int dist = Integer.parseInt(st.nextToken());

                //방향이 없으니까
                tree.get(cur).add(new Node(next, dist));
                tree.get(next).add(new Node(cur, dist));

            }
        }

        //트리는 사이클이 없어서 어떤 노드에서 다른 노드로 가는 경로가 유일함
        //임의의 점에서 한 점을 찾으면 트리의 지름이 되는 양 끝 점 중 하나가 나오게 됨

        visited = new boolean[V+1];
        visited[1] = true;
        dfs(1,0);

        //한 끝 점에서 다시 시행하면, 다른 끝 점이 나오게 됨
        visited = new boolean[V+1];
        maxDist = 0;
        visited[maxNode] = true;
        dfs(maxNode,0);

        System.out.println(maxDist);
    }

    static void dfs(int cur, int length){
        if(length > maxDist){
            maxDist = length;
            maxNode = cur;
        }

        for(Node next : tree.get(cur)){
            if(!visited[next.next]){
                visited[next.next] = true;
                dfs(next.next, length+ next.dist);
            }
        }

    }
}
