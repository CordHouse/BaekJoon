package dfs_bfs;

/***
 * 23. 07. 23 15시 02분 시작
 * 23. 07. 23 16시 03분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. A, B, C 3개의 물통이 주어진다.
 * 2. C 물통이 가득 차 있는 상태로 시작한다.
 * 3. A, B는 빈 물통으로 주어진다.
 * 4. 두 물통을 옮겨 담을 때 하나가 가득차던지, 비던지 해야한다.
 *
 * 제한사항
 * 1 <= A, B, C <= 200
 *
 * 풀이
 * 물통을 옮겨 담을 수 있는 경우의 수를 먼저 파악해야 한다.
 * A -> B
 * A -> C
 * B -> A
 * B -> C
 * C -> A
 * C -> B
 * 6가지 경우로 확인할 수 있고, DFS를 돌면서 각각의 상황에 맞게 물을 옮겨 담아준다.
 * 해당 6가지 방법은 물을 옮겨 담을 수 있는 방법의 가짓수 이기 때문에 A->B로 담는다 식으로 생각해선 안된다.
 * */

public class 물통_2251 {
    private static Set<Integer> set = new HashSet<>();
    private static int[] input;
    private static boolean[][] visit;
    private static List<Integer> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        visit = new boolean[201][201];

        dfs(0, 0, input[2]);

        answer.addAll(set);
        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        for(int v : answer) {
            sb.append(v).append(" ");
        }

        System.out.println(sb);
    }
    public static void dfs(int a, int b, int c) {
        // 이미 방문한 곳인지 확인
        if(visit[a][b]) {
            return;
        }
        // a가 0이 되는 순간 c의 용량 체크
        if(a == 0) {
            set.add(c);
        }
        visit[a][b] = true; // 방문 체크

        // a -> b 로 담을 때 넘치는 경우 ( 차이 발생 )
        if(a + b > input[1]) {
            dfs((a+b)-input[1], input[1], c);
        }
        else {
            dfs(0, a+b, c);
        }

        // b -> a 로 담을 때 넘치는 경우 ( 차이 발생 )
        if(b + a > input[0]) {
            dfs(input[0], (b+a)-input[0], c);
        }
        else {
            dfs(a+b, 0, c);
        }

        // c -> a 로 담을 때 넘치는 경우 ( 차이 발생 )
        if(c + a > input[0]) {
            dfs(input[0], b, (c+a)-input[0]);
        }
        else {
            dfs(c+a, b, 0);
        }

        // c -> b 로 담을 때 넘치는 경우 ( 차이 발생 )
        if(c + b > input[1]) {
            dfs(a, input[1], (c+b)-input[1]);
        }
        else {
            dfs(a, c+b, 0);
        }

        // 이건 a와 b를 한 번씩 c로 옴겨 담는 경우이다.
        dfs(a, 0, b+c); // b -> c
        dfs(0, b, a+c); // a -> c
    }
}
