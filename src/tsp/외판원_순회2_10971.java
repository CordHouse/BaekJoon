package tsp;

/***
 * 23. 06. 14 14시 35분 시작
 * 23. 06. 14 15시 38분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 주어진 조건
 * 1. 이미 방문한 노드는 재 방문해서는 안된다.
 * 2. 중복 방문하지 않고 모든 노드를 방문하여 시작 노드로 돌아오도록 순환 설계한다.
 * 3. (i, j), (j, i)의 값이 다를 수 있다.
 *
 * 제한사항
 * 2 <= N <= 10
 * 0 <= 가중치 <= 1_000_000
 *
 * 풀이
 * 해당 문제는 백트래킹을 활용해 접근해본다.
 * 방문 노드를 체크해가며 최적의 값을 구해야하고, n의 제한사항이 10보다 커질 경우는 해당 백트래킹을 이용하면 안된다.
 * 해당 백트래킹 알고리즘을 사용시 O(n!)이 되기 때문에 시간초과가 발생하기 때문이다.
 * */

public class 외판원_순회2_10971 {
    private static int n;
    private static int[][] cost;
    private static boolean[] visit;
    private static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];

        // Step 1. 입력을 배열로 만들어 저장한다. (가중치)
        for(int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(line[j]);
            }
        }

        // Step 2. dfs 시작
        visit = new boolean[n];
        // 시작점은 방문했다고 가정하고 시작한다.
        for(int i = 0; i < n; i++) {
            visit[i] = true;
            dfs(0, i, i, 0);
            visit[i] = false;
        }
        System.out.println(answer);
    }

    public static void dfs(int depth, int start, int cur, int sum) {
        // 시작할때 방문했기 때문에 전체 크기에서 1만큼을 뺴준다.
        if(depth == n-1) {
            if(cost[cur][start] != 0) {
                sum += cost[cur][start];
                answer = Math.min(answer, sum);
            }
            return;
        }

        for(int i = 0; i < n; i++) {
            if(!visit[i] && cost[cur][i] != 0) {
                visit[i] = true;
                dfs(depth+1, start, i, sum + cost[cur][i]);
                visit[i] = false;
            }
        }
    }
}
