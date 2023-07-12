package tsp;

/***
 * 23. 07. 11 14시 41분 시작
 * 23. 07. 11 16시 30분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 주어진 조건
 * 1. 1 ~ N번까지 매겨져 있는 도시들이 있고, 도시 사이에는 길이 있다.
 * 2. 어느 한 도시를 출발해 다시 원래의 도시로 돌아오는 순한 여행경로를 계획하려 한다.
 * 3. 도시 사이의 비용이 주어진다.
 *
 * 제한사항
 * 2 <= n <= 16
 * 1_000_000이하의 양의 정수, 갈 수 없는 경우 0
 * W[i][j] 도시 i -> 도시 j로 가기 위한 비용
 *
 * 풀이
 * 외판원 순회 문제는 A노드에서 B노드로 이동할때 비용이 발생하는데, 모든 노드를 다 방문하고 처음으로 돌아오는 최소의
 * 비용을 구하는 문제이다. 대체적으로 비트마스크와 DP를 활용하여 문제를 접근한다.
 * 또한 어떤 노드에서 부터 출발한다고 명시하지 않아도 자연스럽게 최소 값을 구하며 사이클이 형성되기 때문에
 * 1 -> 2 -> 3, 2 -> 3 -> 1, 3 -> 1 -> 2 모두가 같은 경로이다.
 * 이미 방문 했던 노드가 겹치게 되는 순간이 있는데 이를 DP를 활용해 최소비용을 구할 수 있다.
 * 1 -> 2 -> 3 -> 5 -> 4 -> 1
 * 1 -> 3 -> 2 -> 5 -> 4 -> 1
 * 두 루트에서 5, 4, 1이 중복되기 때문에 방문하지 않고 데이터를 처리할 수 있다.
 */

public class 외판원_순회_2098 {
    private static int n;
    private static final int INF = 10_000_000;
    private static int[][] dp, map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // Step 1. 주어진 인풋 값을 2차원 배열에 초기화
        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < input.length; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        // Step 2. DP를 사용하기 위해 DP 값을 초기화 해준다.
        // 여기서 중요한건 visit 부분은 비트마스크로 표현되기 떄문에 n이 5면 32 즉 31까지 표현되어야 32개가 된다.
        dp = new int[n][(1<<n) - 1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(0, 1));
    }

    // now : 시작노드
    // visit : 방문노드
    public static int dfs(int now, int visit) {
        // 모든 노드를 방문했다면 아래 조건문 실행
        if(visit == (1 << n) - 1) {
            // now -> 출발도시로 돌아가지 못한다면 INF로 리턴
            if(map[now][0] == 0) {
                return INF;
            }
            return map[now][0];
        }

        // 이미 방문한적 있는 곳이라면 해당 값을 리턴
        if(dp[now][visit] != -1) {
            return dp[now][visit];
        }

        dp[now][visit] = INF;

        for(int i = 0; i < n; i++) {
            // 해당 부분이 조금 어려운데
            // 아직 방문하지 않은 노드가 있는 경우를 찾아 방문
            if((visit & (1 << i)) == 0 && map[now][i] != 0) {
                // 1. i번째 도시와, 그 다음 도시를 방문했다는 가정 + 현재 위치에서 i번 도시까지의 거리
                // 2. 현재 위치의 최소 거리
                // 두 거리를 비교하여 최솟값을 찾는다.
                dp[now][visit] = Math.min(dfs(i, visit | (1 << i)) + map[now][i], dp[now][visit]);
            }
        }
        return dp[now][visit];
    }
}
