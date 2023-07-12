package lis;

/***
 * 23. 07. 12 23시 58분 시작
 * 23. 07. 13 01시 04분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 두 전봇대 사이에 전깃줄이 매우 꼬여있다. -> LIS 힌트
 * 2. 연결된 수가 최대로 될 수 있도록 최소한의 전깃줄을 잘라야한다.
 *
 * 제한사항
 * 1 <= N <= 100_000
 *
 * 풀이
 * 해당 문제의 1번 조건을 보고 LIS를 떠올리면 가장 좋은 문제이다.
 * LIS는 DP와 이진탐색을 활용하여 풀어보는 것이 시간적인 이득을 가져올 수 있다.
 * 특히 이 문제에선 N이 10만까지 제시될 수 있기 때문에 n^2이상으로 시간복잡도를 갖지 않게 주의해야한다.
 * 또한, 입력을 받으면서 Split을 사용하게 되면 메모리 초과가 발생할 수 있기 때문에 Token으로 활용하여 받아야 한다.
 * 전깃줄이나 연결된 선처럼 문제가 제시될 경우 DP안에 값을 상대 인덱스의 번호를 넣어주고
 * 다음 값을 비교할 때 현재 DP에 담긴 번호보다 크다면 next를 증가시켜준다. 총 연결된 갯수를 의미하기 때문이다.
 * 하지만 작은 경우에는 이전 값들을 지나가며 이어져 있기 때문에 연결을 해제해주고 현재 위치의 최대 값을 바꿔줘야한다.
 * 예를들어 4 -> 9, 5 -> 7, 9 -> 8 으로 연결되어 있다면 1번부터 순서대로 증가하기 때문에
 * 4에 연결된 값인 9가 제일 크게 되어 5에 연결된 7과 9에 연결된 8이 연결될 수 없게 된다.
 * 반면 4에 연결된 9를 없애고 5에서 7로 된 값을 9위치에서의 갱신이 일어난다면 다음 9에 연결된 8을 연결할 수 있게 되면서
 * 최종 2개의 선을 그릴 수 있게 된다.
 */

public class 꼬인_전깃줄_1365 {
    private static int[] dp;
    private static List<Integer> link = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // Step 1. DP 생성 -> 상대 index 값들이 담겨 비교될 예정
        dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Step 2. 상대 index 번호를 찾아 link에 저장
        for(int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            for(int j = 0; j < n; j++) {
                if (j+1 == number) {
                    link.add(j);
                    break;
                }
            }
        }

        // Step 3. DP의 처음 값 삽입
        int next = 0;
        dp[next] = link.get(next);

        // Step 4. DP와 상대 index 값 비교
        // 1부터 시작하는 이유는 dp에 이미 link.get(0)번 값이 들어있기 때문
        for(int i = 1; i < n; i++) {
            // dp에 현재 담긴 값보다 크다면 선이 연결될 수 있기 때문에 next를 증가 시켜준다.
            if(dp[next] < link.get(i)) {
                dp[++next] = link.get(i);
            }
            // 그게 아니라면 현재 상대 index 값보다 DP 안에서 크거나 같은 값을 선택해 변경해준다.
            else {
                int index = binarySearch(link.get(i), 0, next);
                dp[index] = link.get(i);
            }
        }
        // 전체에서 next만큼 빼주면 정답이지만, 번호는 1번부터 있고 index는 0번부터 시작이기 때문에
        // -1 만큼을 꼭 해줘야한다.
        System.out.println(n - next - 1);
    }

    public static int binarySearch(int index, int left, int right) {
        while(left <= right) {
            int mid = (left + right) / 2;

            if(index <= dp[mid]) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }
}
