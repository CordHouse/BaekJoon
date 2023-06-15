package bellman_ford;

/***
 * 23. 06. 15 10시 28분 시작
 * 23. 06. 13 11시 30분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 주어진 조건
 * 1. 가중치가 음수인 경우가 존재한다.
 * 2. 한 분기점에서 다른 분기점으로 이동하는 경로가 있다.
 *
 * 제한사항
 * 1 <= N <= 500
 * 1 <= M <= 6_000
 * 1 <= A, B <= N, -10_000 <= C <= 10_000
 *
 * 풀이
 * 해당 문제는 벨만 포드로 접근하여 풀이한다.
 * 그래프 이론 알고리즘 다익스트라, 벨만포드, 플로이드 워셜 중 벨만포드로 풀어야하는 이유는 다음과 같다.
 * 벨만포드는 음수의 가중치를 다룰 수 있는 알고리즘이기 때문에 해당 조건에서 제시한 음수 가중치를 해결하기 위해
 * 사용한다.
 *
 * 주의해야할 점은 최소 값으로 갱신하는 배열 arr를 int 배열로 만들어 언더플로우가 발생한다.
 * N이 최대 500, M이 최대 6000이므로 최대 300만번 반복하게 되고, 음의 간선이 -10_000이라면 -300억이 되기 때문에
 * 최소의 경우 언더 플로우가 발생하여 출력초과가 발생한다. 따라서 형태를 long으로 수정해야한다.
 * */
public class 타임_머신_11657 {
    private static final int INF = 100_000_000;
    private static int n;
    private static int m;
    private static List<Node> graph = new ArrayList<>();
    private static long[] arr;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        // Step 1. 최대 값으로 초기화
        arr = new long[n+1];
        Arrays.fill(arr, INF);

        // Step 2. 노드를 하나 만들어, 분기점 별 간선 정보를 입력
        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int u = Integer.parseInt(line[0]);
            int w = Integer.parseInt(line[1]);
            int v = Integer.parseInt(line[2]);
            graph.add(new Node(u, w, v));
        }

        // Step 3. 벨만포드 알고리즘 시작
        bellmanFord(1);

        // Step 4. 출력
        if(answer == -1) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= n; i++) {
            if(arr[i] == INF) {
                sb.append("-1\n");
                continue;
            }
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void bellmanFord(int start) {
        arr[start] = 0;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < m; j++) {
                Node node = graph.get(j);
                // 현재 위치가 INF가 아니라면,
                // 다음 위치의 가중치 보다 현재 가중치 + 다음 간선의 값이 작아진다면 갱신
                if(arr[node.u] != INF && arr[node.w] > arr[node.u] + node.v) {
                    arr[node.w] = arr[node.u] + node.v;
                }
            }
        }

        // 벨만포드 특성상 n+1번의 반복을 진행하기에
        // 아랫처럼 한번더 진행해 음수가 되는 조건이 있다면
        // 배열안에 값이 갱신되는 점을 이용해 확인한다.
        for(int i = 0; i < m; i++) {
            Node node = graph.get(i);
            if(arr[node.u] != INF && arr[node.w] > arr[node.u] + node.v) {
                answer = -1;
                return;
            }
        }
    }

    static class Node {
        int u;
        int w;
        int v;
        public Node(int u, int w, int v) {
            this.u = u;
            this.w = w;
            this.v = v;
        }
    }
}
