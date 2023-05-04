package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    1. 이번 문제의 핵심은 암호 중간에 나오는 0을 어떻게 처리할 것인가.
    2. 점화식을 어떻게 찾을 것인가.

    조건)
    1. 숫자 범위는 1 ~ 26 범위 내에만 존재한다.
    2. 현재 숫자가 0 또는 앞 숫자가 0이면 건너 뛴다. ( 만들 수 없는 암호이기 때문이다. )
    -> 101 -> 1, 0, 1 (x) / 10, 1 (o) / 1, 01 (x)
 */
public class 암호코드 {
    private static final int MOD = 1000000; // 문제에서 제시해준 나눠야하는 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String password = br.readLine();
        int n = password.length();
        int[] dp = new int[n+1];
        dp[0] = 1; // 숫자가 하나만 있을 때는 경우의 수 하나이다.

        for(int i = 1; i <= n; i++) {
            // 캐릭터형 변수를 정수형 변수로 변경
            // 일의자리 숫자를 가져온다.
            int currentPoint = password.charAt(i-1) - '0';
            if(currentPoint > 0 && currentPoint < 10) {
                dp[i] += dp[i-1];
                dp[i] %= MOD; // 계산하는 중간에 큰 수가 나올 수 있기 때문에 미리 나눠준다.
            }
            // 1을 건너 뛰는 이유는 2자리 숫자는 2부터 나올 수 있기 때문이다.
            if(i == 1) {
                continue;
            }
            // 맨 마지막 자리보다 앞자리의 숫자를 가져올 변수이다.
            int beforePoint = password.charAt(i-2) - '0';
            // 해당 변수가 0인 경우 01 같은 상황이 만들어지기 때문에 건너 뛴다.
            if(beforePoint == 0) {
                continue;
            }
            // 이후 10 이상의 값을 보기 위해 해당 공식을 적용한다.
            beforePoint = beforePoint * 10 + currentPoint;
            if(beforePoint >= 10 && beforePoint <= 26) {
                dp[i] += dp[i-2];
                dp[i] %= MOD;
            }
        }
        System.out.println(dp[n]);
    }
}
