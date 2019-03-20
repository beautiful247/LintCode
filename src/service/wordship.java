package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//字符串操作题目集合
public class wordship {
    //翻转字符串
    public String reverseWords(String s){
        s = s.replaceAll("\\s{1,}"," ");
        s = s.trim();
        String[] temp = s.split(" ");
        int len = temp.length;
        for(int i=0;i<len/2;i++){
            String test=temp[i];
            temp[i] = temp[len-i-1];
            temp[len-i-1] = test;
        }
        return String.join(" ",temp);
    }
    //压缩字符创
    public String compress(String s){
        List<String> ans = new ArrayList<String>();
        String[] temp = s.split("");
        String a = "\0";
        for(int i=0;i<temp.length;){
            a=temp[i];
            ans.add(temp[i]);
            int j=1;
            int k=i+1;
            while(k<temp.length && temp[k].equals(a)){
                j++;
                k++;
            }
            ans.add(Integer.toString(j));
            i=k;
        }
        if(ans.size()>=s.length()){
            return s;
        }
        return String.join("",ans);
    }

    //是否是回文子串
    public boolean isPalindrome(String s){
        String temp = new StringBuffer(s).reverse().toString();
        String temp_o = new StringBuffer(s.toLowerCase()).toString().split(" ").toString();
        String temp_n = new StringBuffer(s.toLowerCase()).reverse().toString().split(" ").toString();
        return temp_o.equals(temp_n);
    }

    //回文子串个数
    public int countPalindromicSubstrings(String str) {
        int res=0;
        String[] temp = str.split("");
        for(int i=0;i<temp.length;i++){
            int j=0;
            while(i-j>=0&&i+j<temp.length&&temp[i-j].equals(temp[i+j])){
                res++;
                j++;
            }
        }
        for(int i=1;i<temp.length;i++){
            if(temp[i-1].equals(temp[i])){
                int j=0;
                while(i-j-1>=0&&i+j<temp.length&&temp[i-j-1].equals(temp[i+j])){
                    res++;
                    j++;
                }
            }
        }
        return res;
    }

    //最后一个单词的长度
    public int lengthOfLastWord(String s) {
        String[] temp = s.split(" ");
        return temp[temp.length-1].length();
    }

    //最长的回文子串
    public String longestPalindrome(String s) {
        //字符串变化
        String[] temp = s.split("");
        String[] s_new = new String[2*s.length()+3];
        s_new[0] = "$";
        s_new[1] = "#";
        int j = 2;
        for(int i=0;i<s.length();i++){
            s_new[j++] = temp[i];
            s_new[j++] = "#";
        }
        s_new[j] = "\0";
        //计算p[]
        int[] p = new int[2000];
        p[1] = 0;
        int mx = 0;//回文串能延伸到的最右端的位置
        int id = 0;//能延伸到最右端的位置的回文子串的中心点位置
        int max_len = 0;
        int resCenter = 0;
        for(int i=1;i<s_new.length;i++){
            p[i] = mx > i ? Math.min(p[2 * id - i],mx - i) : 1;
            while(i+p[i]<s_new.length&&s_new[i + p[i]].equals(s_new[i - p[i]])){
                p[i]++;
            }
            if(mx < i + p[i]){
                mx = i + p [i];
                id = i;
            }
            if(max_len < p[i]){
                max_len = p[i];
                resCenter = i;
            }
        }
        System.out.println("中心位置："+(resCenter-max_len)/2+" 半径："+((max_len-1+(resCenter-max_len)/2)-1));
        String res = s.substring((resCenter-max_len)/2,((max_len-1+(resCenter-max_len)/2)));
        return res;
    }
}
