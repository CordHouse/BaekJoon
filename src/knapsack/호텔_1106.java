package knapsack;

/**
 * 23. 08. 01 14시 28분 시작
 * 23. 08. 01 15시 09분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 인원에 비례하게 돈을 증가시키며 여기서 최솟값을 찾아야한다.
 *
 * 제한사항
 * c <= 1000
 * N <= 20
 * 각 도시에서 홍보할 때 대는 비용 100보다 작은 값이다.
 *
 * 풀이
 * DP와 우선순위 큐를 활용하여 문제를 풀 수 있다.
 * 이번 문제의 핵심은 배열의 크기가 100정도의 여유분 만큼 더 훑어봐야 최솟값이 도출될 수 있다.
 * 기존에는 배열의 크기를 + 100만큼 지정하지 않고, 가장 큰 인원의 수 만큼을 더 봤었는데 여기서 최솟값이 나올 수도 있고
 * 안나올 수도 있다. 이 부분만 주의해야한다.
 */

public class 호텔_1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // Step 1. 우선순위 큐를 활용하여 인원 별 비용을 정리한다.
        PriorityQueue<Cost> queue = new PriorityQueue<>((o1, o2) -> o1.person-o2.person);

        int max = 0;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            int person = Integer.parseInt(st.nextToken());
            queue.add(new Cost(money, person));
            max = Math.max(max, person);
        }

        // Step 2. dp를 만들고 여유 분 크기 + 100만큼을 추가로 잡아준다.
        int[] dp = new int[c+100];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        // Step 3. 반복을 통해서 인원에 따른 비용 최솟값을 구해 DP에 저장한다.
        while(!queue.isEmpty()) {
            Cost cost = queue.poll();

            for(int i = cost.person; i < dp.length; i++) {
                if(dp[i-cost.person] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - cost.person] + cost.money);
                }
            }
        }

        // Step 4. 최솟값 비용을 한번 훑으면서 찾아야한다.
        // "적어도"라고 문제에서 주어졌기 때문에 훑어봐야하는 것이다.
        int answer = Integer.MAX_VALUE;
        for(int i = c; i < dp.length; i++) {
            answer = Math.min(answer, dp[i]);
        }
        System.out.println(answer);
    }

    static class Cost {
        int money, person;

        public Cost(int money, int person) {
            this.money = money;
            this.person = person;
        }
    }
}
