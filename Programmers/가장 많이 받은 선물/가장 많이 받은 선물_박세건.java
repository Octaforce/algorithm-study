import java.util.*;
import java.io.*;

class Solution {
    Map<String,int[]> giftInfo=new HashMap<>();
    Map<String,Integer> fromToCnt=new HashMap<>();
    Map<String,Integer> reciveCnt=new HashMap<>();
    StringBuilder sb=new StringBuilder();

    public int solution(String[] friends, String[] gifts) {
        for(int i=0;i<friends.length;i++){
            giftInfo.put(friends[i],new int[] {0,0});
            reciveCnt.put(friends[i],0);
        }
        
        for(int i=0;i<gifts.length;i++){
            String[] splits=gifts[i].split(" ");
            String from=splits[0];
            String to=splits[1];
            giftInfo.get(from)[0]++;
            giftInfo.get(to)[1]++;
            fromToCnt.put(gifts[i],fromToCnt.getOrDefault(gifts[i],0)+1);
        }
        for(int i=0;i<friends.length;i++){
            for(int j=i+1;j<friends.length;j++){
                //각각 저장시켰던 정보 조회
                int firstSecondCnt=getFromToCnt(friends[i],friends[j]);
                int secondFirstCnt=getFromToCnt(friends[j],friends[i]);
                //서로의 선물 주고받은 결과 확인
                if(firstSecondCnt>secondFirstCnt){
                    reciveCnt.put(friends[i],reciveCnt.get(friends[i])+1);
                }else if(firstSecondCnt<secondFirstCnt){
                    reciveCnt.put(friends[j],reciveCnt.get(friends[j])+1);
                }else{ //같은 경우
                    int firstScore=giftInfo.get(friends[i])[0]-giftInfo.get(friends[i])[1];
                    int secondScore=giftInfo.get(friends[j])[0]-giftInfo.get(friends[j])[1];
                    if(firstScore>secondScore){
                        reciveCnt.put(friends[i],reciveCnt.get(friends[i])+1);
                    }else if(firstScore<secondScore){
                        reciveCnt.put(friends[j],reciveCnt.get(friends[j])+1);
                    }
                }
                    
            }
        }
        int answer=0;
        for(Integer x : reciveCnt.values()){
            answer=Math.max(answer,x);
        }
        return answer;
    }
    
    private int getFromToCnt(String first, String second){
        sb.append(first).append(" ").append(second);
        String connFirstSecond=sb.toString();
        int result=fromToCnt.getOrDefault(connFirstSecond,0);
        sb.setLength(0);
        return result;
    }
}
