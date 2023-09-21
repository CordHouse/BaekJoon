package lcs;

/**
 * 23. 09. 22 00시 05분 시작
 * 23. 09. 22 01시 05분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 주어진 조건
 * 1. 3개의 문자열이 주어질 때 최장 공통 부분수열을 구하여라
 *
 * 제한사항
 * 단어 길이 <= 100
 *
 * 풀이
 * 해당 문제를 풀면서 LCS의 약자가 두 가지가 있다는 것을 알게 되었다.
 * 내가 처음 풀었던 방법은 최장 공통 문자열을 의미하는 풀이로 작성하여 오답을 만들었고,
 * 문제에서는 최장 공통 부분수열로 풀어야했다.
 * 3차원 배열을 만들어 각각 문자열에 대해 같은 단어를 카운트 하는 테이블을 만들어 갯수를 카운팅하고,
 * 첫 번째 두 번째 세 번째의 문자가 모두 같은 경우 카운팅을 진행하며, 그 이외에는 가장 큰 숫자를 선택해줘야한다.
 * 그래야 최대가 유지된 상태로 원하는 값을 얻을 수 있게 되는 것이다.
 */

public class LCS_3_1958 {
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split("");
        String[] second = br.readLine().split("");
        String[] thread = br.readLine().split("");

        lcs(first, second, thread);
        System.out.println(answer);
    }

    public static void lcs(String[] s1, String[] s2, String[] s3) {
        int[][][] map = new int[s1.length+1][s2.length+1][s3.length+1];

        for(int i = 1; i <= s1.length; i++) {
            for(int j = 1; j <= s2.length; j++) {
                for(int k = 1; k <= s3.length; k++) {
                    if(s1[i-1].equals(s2[j-1]) && s1[i-1].equals(s3[k-1])) {
                        map[i][j][k] = map[i - 1][j - 1][k - 1] + 1;
                    }
                    else {
                        map[i][j][k] = Math.max(map[i-1][j][k], Math.max(map[i][j-1][k], map[i][j][k-1]));
                    }
                }
            }
        }
        answer = map[s1.length][s2.length][s3.length];
    }
}
