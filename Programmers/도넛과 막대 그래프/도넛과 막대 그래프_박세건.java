import java.util.*;
import java.io.*;

class Solution {
    Set<Integer> nodes=new HashSet<>();
    Map<Integer,Integer> comeInCnt=new HashMap<>();
    Map<Integer,Boolean> visited=new HashMap<>();
    Map<Integer,Queue<Integer>> connInfo= new HashMap<>();
    int donutCnt, poleCnt, eightCnt;
    
    public int[] solution(int[][] edges) {
        setting(edges);
        int newNode=findNewNode();
        Queue<Integer> nextNodes=connInfo.get(newNode);
        while(!nextNodes.isEmpty()){
            int next=nextNodes.poll();
            visited.put(next,true);
            int result=findConnNode(next);
            if(result==0) poleCnt++;
            else if(result==1)donutCnt++;
            else if(result==2) eightCnt++;
        }
        return new int[] {newNode,donutCnt,poleCnt,eightCnt};
    }
    
    private int findNewNode(){
        for(int cur:nodes){
            if(comeInCnt.get(cur)==0&&connInfo.get(cur).size()>1) return cur;
        }
        return -1;
    }
    
    private int findConnNode(int cur){
        int result=0;
        Queue<Integer> nextNodes=connInfo.get(cur);
        while(!nextNodes.isEmpty()){
            int next=nextNodes.poll();
            if(visited.get(next)) result++;
            visited.put(next,true);
            result+=findConnNode(next);
        }
        return result;
    }
    
    private void setting(int[][] edges){
        for(int i=0;i<edges.length;i++){
            int from=edges[i][0];
            int to=edges[i][1];
            nodes.add(from);
            nodes.add(to);
            visited.putIfAbsent(from,false);
            visited.putIfAbsent(to,false);
            comeInCnt.putIfAbsent(from,0);
            comeInCnt.put(to,comeInCnt.getOrDefault(to,0)+1);
            connInfo.putIfAbsent(from,new ArrayDeque<>());
            connInfo.get(from).add(to);
            connInfo.putIfAbsent(to,new ArrayDeque<>());
        }
    }
}
