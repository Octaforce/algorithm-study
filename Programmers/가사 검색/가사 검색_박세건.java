import java.util.*;
import java.io.*;

class Solution {
    
    private List<String>[] wordsByLength=new List[11111];
    private List<String>[] wordsByLengthRe=new List[11111];
    
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        inputSetting(words);
        for(int i=0;i<queries.length;i++){
            answer[i]=getCorrectCnt(queries[i]);
        }
        
        return answer;
    }
    
    
    private int getCorrectCnt(String cur){
        List<String> searchList=null;
        if(cur.charAt(0)=='?'){
            cur=new StringBuilder(cur).reverse().toString();
            searchList=wordsByLengthRe[cur.length()];
        } 
        else {
            searchList=wordsByLength[cur.length()]; 
        }
        
        int start=0;
        int end=searchList.size()-1;
        String startStr=cur.replace('?','a');
        String endStr=cur.replace('?','z');
        int startIdx=getIdx(searchList,startStr);    
        int endIdx=getIdx(searchList,endStr);  
        // System.out.println(startStr+" "+endStr);
        // System.out.println(searchList);
        // System.out.println(startIdx+" "+endIdx);
 
        return endIdx-startIdx;
    }
    
    private int getIdx(List<String> searchList, String cur){
        int start=0;
        int end=searchList.size();
        while(start<end){
            int mid=(start+end)/2;
            int compareVal=cur.compareTo(searchList.get(mid));
            if(compareVal<0){
                end=mid;
            }else{
                start=mid+1;
            }
        }
        return end;
    }
    
    private void inputSetting(String[] words){
        String[] reverseWords=new String[words.length];
        for(int i=0;i<words.length;i++) reverseWords[i]=new StringBuilder(words[i]).reverse().toString();
        Arrays.sort(words);
        Arrays.sort(reverseWords);
        for(int i=0;i<11111;i++) wordsByLength[i]=new ArrayList<>();
        for(int i=0;i<11111;i++) wordsByLengthRe[i]=new ArrayList<>();
        for(int i=0;i<words.length;i++) wordsByLength[words[i].length()].add(words[i]);
        for(int i=0;i<reverseWords.length;i++) wordsByLengthRe[reverseWords[i].length()].add(reverseWords[i]);
    }
    
}
