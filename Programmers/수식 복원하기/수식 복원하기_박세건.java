import java.util.*;
import java.io.*;

class Solution {
    
    private StringBuilder sb=new StringBuilder();
    
    public String[] solution(String[] expressions) {
//         입력값 확인해서 가장 큰 자릿수 확인해서 진수법 가능한 최대값 구하기
        int possibleMaxVal=getPossibleMaxVal(expressions);
        // 구한 최대 진수 값으로 수식이 올바르게 성립하는 값 구하기
        List<Integer> possibleVals=getPossibleVals(possibleMaxVal,expressions);
        // System.out.println(possibleVals);
//         구한 PossibleVals 들이 모두 같은 X값을 추출하는지 확인, 같은 값이라면 해당 값 설정, 아니라면 ? 처리
        List<String> answer = new ArrayList<>();
        for(int i=0;i<expressions.length;i++){
            String[] splits=expressions[i].split(" ");
            if(splits[4].equals("X")){
                for(int k=0;k<4;k++){
                    sb.append(splits[k]).append(" ");
                }
                sb.append(getCorrectVal(possibleVals,expressions[i]));
                answer.add(sb.toString());
                sb.setLength(0);
            }
        }
        return answer.toArray(new String[0]);
    }
    
    private String getCorrectVal(List<Integer> possibleVals, String expression){
        // System.out.println();
        
        int correctVal=-1;
        for(int i=0;i<possibleVals.size();i++){
            int val=possibleVals.get(i);
            
            String[] splits=expression.split(" ");
            int A=Integer.parseInt(splits[0]);
            boolean isPlus=splits[1].equals("+")?true:false;
            int B=Integer.parseInt(splits[2]);
            
            int valA=(A/100*(val*val))+(A%100/10*val)+(A%10);
            int valB=(B/100*(val*val))+(B%100/10*val)+(B%10);
            int valC=-1;
            
            if(isPlus) valC=valA+valB;
            else valC=valA-valB;
            // System.out.println(correctVal+" "+valC);
            
//             나온 값을 진수로 변환
            valC=toFormation(valC,val);
            
            if(correctVal==-1) correctVal=valC;
            else if(correctVal!=valC) {
                return "?";
            }
        }
        return String.valueOf(correctVal);
    }
    
    private int toFormation(int valC,int val){
        if(valC==0) return 0;
        StringBuilder tmp=new StringBuilder();
        // System.out.println(valC+" "+val);
        while(valC!=0){
            tmp.append(valC%val);
            valC/=val;
        }
        tmp.reverse();
        // System.out.println(" = " +tmp);
        int result=Integer.parseInt(tmp.toString());
        return result;
        // return -1;
    }
    
    private List<Integer> getPossibleVals(int possibleMaxVal,String[] expressions){
        List<Integer> possibleVals=new ArrayList<>();
        for(int i=possibleMaxVal;i<=9;i++){
            if(isPossible(i,expressions)) possibleVals.add(i);
        }
        return possibleVals;
    }
    
    private boolean isPossible(int val, String[] expressions){
        for(int i=0;i<expressions.length;i++){
            if(!isMatch(val,expressions[i])) return false;
        }
        return true;
    }
    
    private boolean isMatch(int val, String expressions){
        // System.out.println(val);
        String[] splits=expressions.split(" ");
        if(splits[4].equals("X")) return true;
        int A=Integer.parseInt(splits[0]);
        boolean isPlus=splits[1].equals("+")?true:false;
        int B=Integer.parseInt(splits[2]);
        int C=Integer.parseInt(splits[4]);
        // System.out.println(A+" "+B+" "+C);
        int valA=(A/100*(val*val))+(A%100/10*val)+(A%10);
        int valB=(B/100*(val*val))+(B%100/10*val)+(B%10);
        int valC=(C/100*(val*val))+(C%100/10*val)+(C%10);
                // System.out.println(valA+" "+valB+" "+valC);
        // System.out.println();

        if(isPlus&&valA+valB==valC) return true;
        else if(!isPlus&&valA-valB==valC) return true;
        return false;

    }
    
    private int getPossibleMaxVal(String[] expressions){
        int maxVal=-1;
        for(int i=0;i<expressions.length;i++){
            String[] splits=expressions[i].split(" ");
            // System.out.println(Arrays.toString(splits));
            for(int j=0;j<splits.length;j++){
                if(splits[j].charAt(0)>='0'&&splits[j].charAt(0)<='9'){
                    for(int k=0;k<splits[j].length();k++){
                        // System.out.println(splits[i].charAt(k));
                        maxVal=Math.max(maxVal,splits[j].charAt(k)-'0');
                    }
                }
            }
        }
        return maxVal+1;
    }
}
