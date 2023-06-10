package lcs;

/***
 * 23. 06. 10 13시 28분 시작
 * 23. 06. 10 13시 52분 종료
 * 성공 유무 -> 성공
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 주어진 조건
 * 1. 두 문자열을 입력받아 공통되는 부분의 최장 값을 구한다.
 * 2. 주의해야할 점은 두 문자열은 서로 다른 길이를 가질 수 있다는 점이다.
 *
 * 제한사항
 * 최대 1000글자
 * 알파벳 대문자로 구성
 *
 * 풀이
 * LCS -> 최장 공통 부분 수열
 * 최장 공통 부분 수열은 비교해야할 문자열 두개를 입력으로 받는다.
 * 입력 받은 두 문자열을 DP로 된 테이블을 구성하여 2가지로 구분하여 값을 입력한다.
 * 1. 공통되는 문자가 있다면 i-1, j-1 위치의 값에서 +1을 해준다.
 * 2. 공통되는 문자가 없다면 [i][j-1], [i-1][j] 값 중 큰 값으로 저장한다.
 * 주의할 점은 각 배열의 위치 중 열에 들어가는 값들은 해당 열 위치에서의 가장 큰 값이 유지 되기 때문에
 * 최대 index 만큼 증가할 수 있는 규칙이 존재한다.
 * 두 가지 조건에 의해 공통되는 문자의 최장 값을 구할 수 있게 된다.
 * 시간복잡도는 O(n*m) -> 따라서 n <= 100_000, m <= 100_000 의 조건으로 주어진다면 터질 가능성이 있다.
 * */
public class lcs_9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split("");
        String[] second = br.readLine().split("");
        int firstLen = first.length;
        int secondLen = second.length;
        int[][] dp = new int[firstLen+1][secondLen+1];

        for(int i = 1; i <= firstLen; i++) {
            for(int j = 1; j <= secondLen; j++) {
                if(first[i-1].equals(second[j-1])) { // 해당 위치의 두 문자가 공통인가?
                    dp[i][j] = dp[i-1][j-1] + 1;
                    continue;
                }
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]); // 두 문자열이 공통이지 않은 경우 이전 i값과 j 값 중 가장 큰 값을 선택
            }
        }

        System.out.println(dp[firstLen][secondLen]);
    }
}
