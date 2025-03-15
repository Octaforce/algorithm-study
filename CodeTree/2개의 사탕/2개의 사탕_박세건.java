import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N,M;
    private static char[][] map;
    private static int srx,sry,sbx,sby;

    public static void main(String[] args) throws Exception{
        inputSetting();
        System.out.println(getAnswerByBfs());
    }

    private static int getAnswerByBfs(){
        Queue<int[]> queue=new ArrayDeque<>();
        queue.add(new int[] {srx,sry,sbx,sby,0});
        boolean[][][][] visited=new boolean [N][M][N][M];
        visited[srx][sry][sbx][sby]=true;
        int[][] dir={{-1,0},{0,1},{1,0},{0,-1}};
        while(queue.size()>0){
            int cur[]= queue.poll();
            // System.out.println(Arrays.toString(cur));
            int rx=cur[0];
            int ry=cur[1];
            int bx=cur[2];
            int by=cur[3];
            int cnt=cur[4];
            if(cnt>=10) return -1;

            for(int i=0;i<4;i++){
                boolean isFirstGoal=false, isSecondGoal=false;
                int firstX=0,firstY=0,secondX=0,secondY=0;
                boolean redFirst=isRedFirst(rx,ry,bx,by,i);
                if(redFirst){
                    firstX=rx;
                    firstY=ry;
                    secondX=bx;
                    secondY=by;
                }else{
                    firstX=bx;
                    firstY=by;
                    secondX=rx;
                    secondY=ry;
                }

                while(map[firstX+dir[i][0]][firstY+dir[i][1]]!='#'){
                    firstX+=dir[i][0];
                    firstY+=dir[i][1];
                    if(map[firstX][firstY]=='O'){
                        isFirstGoal=true;
                        firstX=firstY=-1;
                        break;
                    }
                }
                while(map[secondX+dir[i][0]][secondY+dir[i][1]] != '#' &&
                    (firstX == -1 || secondX+dir[i][0] != firstX || secondY+dir[i][1] != firstY)) {
                    secondX += dir[i][0];
                    secondY += dir[i][1];
                    if(map[secondX][secondY] == 'O'){
                        isSecondGoal = true;
                        break;
                    }
                }


                if((redFirst&&isFirstGoal&&!isSecondGoal)||(!redFirst&&isSecondGoal&&!isFirstGoal)) return cnt+1;
                if(!isFirstGoal&&!isSecondGoal){
                    if(redFirst){
                        if(visited[firstX][firstY][secondX][secondY]) continue;
                        visited[firstX][firstY][secondX][secondY]=true;
                        queue.add(new int[] {firstX,firstY,secondX,secondY,cnt+1});
                    } 
                    else {
                        if(visited[secondX][secondY][firstX][firstY]) continue;
                        visited[secondX][secondY][firstX][firstY]=true;
                        queue.add(new int[] {secondX,secondY,firstX,firstY,cnt+1});
                    } 
                }
            }
        }
        return -1;
    }

    private static boolean isRedFirst(int rx,int ry,int bx,int by,int dir){
        if(dir==0&&rx<bx) return true;
        else if(dir==1&&ry>by) return true;
        else if(dir==2&&rx>bx) return true;
        else if(dir==3&&ry<by) return true;
        return false;
    }

    private static void inputSetting() throws Exception{
        st=new  StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new char[N][M];
        for(int i=0;i<N;i++){
            String inputTmp=br.readLine();
            for(int j=0;j<M;j++){
                map[i][j]=inputTmp.charAt(j);
                if(map[i][j]=='R'){
                    map[i][j]='.';
                    srx=i;
                    sry=j;
                }else if(map[i][j]=='B'){
                    map[i][j]='.';
                    sbx=i;
                    sby=j;
                }
            }
        }
    }
}
