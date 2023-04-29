package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 공통_부분_문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputFirst = br.readLine();
        String inputSecond = br.readLine();

        int firstLen = inputFirst.length();
        int secondLen = inputSecond.length();
        int[][] countMap = new int[firstLen + 1][secondLen + 1];
        int max = 0;

        for (int i = 1; i <= firstLen; i++) {
            for (int j = 1; j <= secondLen; j++) {
                if(inputFirst.charAt(i-1) == inputSecond.charAt(j-1)) {
                    countMap[i][j] = countMap[i-1][j-1] + 1;
                    max = Math.max(max, countMap[i][j]);
                }
            }
        }

        System.out.println(max);
    }
}
