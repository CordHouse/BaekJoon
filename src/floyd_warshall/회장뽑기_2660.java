package floyd_warshall;

/***
 * 23. 06. 13 14시 26분 시작
 * 23. 06. 13 15시 23분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 어떤 사람을 기준으로 연결된 사람 수 만큼이 본인의 점수가 된다.
 * 2. 점수가 가장 낮은 사람이 회장 후보가 되며 여러 사람이 나오는 경우 오름차순 정렬을 통해 출력해야한다.
 *
 * 제한사항
 * N <= 50 회원 수는 50명을 넘지 않는다.
 * 마지막 줄 -1이 두개 존재한다.
 *
 * 풀이
 * 플로이드 워셜로 접근해 문제를 풀어본다.
 * 플로이드 워셜의 경우 O(n^3)의 시간복잡도를 갖기 때문에 n이 작은 경우에만 사용할 수 있다.
 * 특히 어떤 중간 노드에 대해서 고려해보는 상황에 해당 알고리즘이 적합 할 수 있다.
 * 이번 문제의 경우 어떤 한 사람을 기준으로 얼만큼 멀어진 사람이 있는가를 봐야하기 때문에 적합한 문제이다.
 *
 * 주의 -> 플로이드 워셜에서는 i와 j가 같은 경우는 항상 0이여야 한다.
 * 또한, INF의 범위 또한 이동하면서 더해지는 거리의 합이 INF 보다 작아야한다.
 * */
public class 회장뽑기_2660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // Step 1. 점수를 담을 변수 초기화
        int[][] distance = new int[n+1][n+1]; // 거리 점수를 저장할 변수
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i != j) {
                    distance[i][j] = 100_000_000;
                }
            }
        }

        // Step 2. 입력 받은 정보를 통해 점수 갱신 -> 여기까지가 INF 적용됨
        while(true) {
            String[] line = br.readLine().split(" ");
            int i = Integer.parseInt(line[0]);
            int j = Integer.parseInt(line[1]);
            if(i == -1 && j == -1) {
                break;
            }
            distance[i][j] = 1;
            distance[j][i] = 1;
        }

        // Step 3. k를 중간 분기점으로 i -> j 거리와 i -> k -> j 거리중 최소 값을 찾아 갱신
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }

        // Step 4. 점수 별 해당하는 분기점을 하나의 큐에 담아 저장 후 출력
        // 단, 점수가 가장 낮은 분기점 모임들 중 오름차순으로 출력 -> PQ 사용
        Map<Integer, PriorityQueue<Integer>> answer = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            int max = 0;
            for(int j = 1; j <= n; j++) {
                max = Math.max(max, distance[i][j]);
            }
            if(!answer.containsKey(max)) {
                answer.put(max, new PriorityQueue<>());
            }
            answer.get(max).add(i);
        }

        // 정렬을 통해 낮은 점수를 찾는다. 0번째 값이 무조건 최소 값
        List<Integer> list = new ArrayList<>(answer.keySet());
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0)).append(" ").append(answer.get(list.get(0)).size()).append("\n");
        while(!answer.get(list.get(0)).isEmpty()) {
            sb.append(answer.get(list.get(0)).poll()).append(" ");
        }
        System.out.println(sb);
    }
}
