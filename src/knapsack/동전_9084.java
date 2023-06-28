package knapsack;

/***
 * 23. 06. 28 16시 10분 시작
 * 23. 06. 28 16시 40분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 주어진 조건
 * 1. 주어지는 동전의 종류들로 원하는 금액을 만드는 모든 방법을 찾기
 *
 * 제한사항
 * 1 <= T <= 10 테스트 케이스 개수
 * 1 <= N <= 20 동전의 가지수
 * 1 <= M <= 10_000
 *
 * 풀이
 * knapsack 알고리즘으로 해결해야하는 문제이다.
 * knapsack은 배낭 알고리즘으로 배열에 담은 이전 값을 활용하여 다음 값을 구하는 과정이다.
 * 따라서 dp를 사용하고, dp[0]을 1로 초기화하여 현재 위치에서 코인의 숫자만큼을 뺀 위치의 값을 더해줌으로써 해결할 수 있다.
 * ex) T = 1, N = 2, M 10 (동전은 1원 2원이라고 가정)
 * dp[1] += dp[1 - coin[0]] -> dp[1] += dp[0] ( 즉 최초 dp[0]으로 부터 시작하기 때문에 1로 꼭 설정해야한다. )
 * 여기서 1은 가짓수를 의미한다.
 * 예로 3을 만들 수 있는 가짓수는 다음과 같다. ( 1-1-1, 1-2 )
 */

public class 동전_9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cycle = Integer.parseInt(br.readLine());

        for(int i = 0; i < cycle; i++) {
            int c = Integer.parseInt(br.readLine());
            int[] coin = new int[c];
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < c; j++) {
                coin[j] = Integer.parseInt(line[j]);
            }
            int goal = Integer.parseInt(br.readLine());
            knapsack(coin, goal);
        }
    }

    public static void knapsack(int[] coin, int goal) {
        int[] dp = new int[goal+1];
        dp[0] = 1;

        for(int i = 0; i < coin.length; i++) {
            for(int j = coin[i]; j <= goal; j++) {
                dp[j] += dp[j - coin[i]];
            }
        }
        System.out.println(dp[goal]);
    }
}
