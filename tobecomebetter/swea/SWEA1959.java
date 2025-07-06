package com.kguard.tobecomebetter.swea;

import java.util.Arrays;
import java.util.Scanner;

public class SWEA1959 {
    public static  void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int t = 0;t<tc;t++){
            int n = sc.nextInt();
            int m = sc.nextInt();
            String bf = sc.nextLine();
            int[] nList = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] mList = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int max = 0;
            if(n > m){
                for(int i = 0; i < n-m+1; i++){
                    int sum = 0;
                    for(int j = 0; j < m; j++){
                        sum += mList[j] * nList[i+j];
                    }
                    max = Math.max(sum, max);
                }
            }else{
                for(int i = 0; i < m-n+1; i++){
                    int sum = 0;
                    for(int j = 0; j < n; j++){
                        sum += nList[j] * mList[i+j];
                    }
                    max = Math.max(sum, max);
                }
            }
            System.out.println("#"+(t+1)+" "+max);
        }
    }
}
