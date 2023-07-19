package union_find;

/***
 * 23. 07. 19 14시 10분 시작
 * 23. 07. 19 14시 47분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 모든 친구들에게 친구비를 내야한다.
 * 2. 하지만, 친구의 친구에게는 비용을 지불하지 않고 친구를 할 수 있다.
 *
 * 제한사항
 * 1 <= N <= 10_000
 * 0 <= M <= 10_000
 * 1 <= k <= 10_000_000
 *
 * 풀이
 * 친구비 문제는 유니온파인드로 해결할 수 있는 문제이다.
 * 각 노드간에 연결성을 파악하고, 그에따라 비용을 계산하는 문제이기 때문이다.
 * 문제에서 주어진 조건을 잘못 이해해 처음에는 반례를 찾느라 힘들었는데, 해당 문제에서는 모든 친구들과 사귀기 위해서는
 * 친구비를 지불해야하며, 그 친구비를 아끼기 위해 친구의 친구는 친구라고 명명할 수 있다는 것이다.
 * 이전에 접근했던 방식은 친구의 친구만 계산을 해서 최소비용이 얼마나오는지 계산하는 코드를 작성했었다.
 * 즉, 친구의 연결성이 없는애들은 계산하지 않아 틀렸던 것이었다.
 * 문제를 해결했던 반례는 다음과 같다.
 * ex)
 * 5 1 1000
 * 1 2 3 4 5
 * 1 4
 * ans : 11
 * ---
 * 5 3 10
 * 1 1 1 1 1
 * 5 4
 * 5 3
 * 5 2
 * ans : 2
 */

public class 친구비_16562 {
    private static int[] parent, cost;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 학생 수
        int m = Integer.parseInt(st.nextToken()); // 친구관계 수
        int k = Integer.parseInt(st.nextToken()); // 보유한 돈

        // Step 1. 부모 배열 초기화
        parent = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Step 2. 비용 부분 람다식
        cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // Step 3. 유니온 시작 (병합과정)
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = find(Integer.parseInt(st.nextToken()));
            int n2 = find(Integer.parseInt(st.nextToken()));
            if(n1 != n2) {
                union(n1, n2);
            }
        }

        // Step 4. 친구 비용 계산
        boolean[] visit = new boolean[n]; // 방문한 부모 체크
        for(int i = 1; i <= n; i++) {
            int index = find(i) - 1; // -1 해주는 이유는 위에 람다식으로 인해 인덱스가 0부터 시작하기 때문
            if(visit[index]) {
                continue;
            }
            if(k - cost[index] >= 0) {
                visit[index] = true;
                k -= cost[index];
                answer += cost[index];
            }
            else {
                System.out.println("Oh no");
                return;
            }
        }
        System.out.println(answer);
    }

    public static void union(int n1, int n2) {
        if(cost[n1 - 1] < cost[n2 - 1]) { // 최소의 비용발생이기 때문에 비용으로 조건을 둔다.
            parent[n2] = n1;
        }
        else {
            parent[n1] = n2;
        }
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }
}
