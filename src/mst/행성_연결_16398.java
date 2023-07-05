package mst;

/***
 * 23. 07. 05 14시 41분 시작
 * 23. 07. 05 15시 14분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 주어진 조건
 * 1. N개의 행성은 정수 1 ~ N으로 표기하고, i와 j사이의 플로우 관리비용은 Cij이며 i = j 인경우 항상 0이다.
 *
 * 제한사항
 * 1 <= N <= 1_000
 * 행성은 N * N 행렬 (Cij), ( 1 <= i, j <= N, 1 <= Cij <= 100_000_000, Cij = Cji, Cii = 0 )
 *
 * 풀이
 * 해당 문제는 최소 스패닝 트리로 접근하여 풀어야하는 문제이다.
 * 최소 스패닝 트리는(MST) 모든 정점을 방문하여 사이클이 생기지 않도록 하는 방법이다.
 * 정석 풀이로는 유니온 파인드 알고리즘을 활용하여, 시작 정점과 종료 정점을 기준으로 부모 값을 찾고
 * 찾은 부모 값이 일치한다면 사이클이 형성되기 때문에 부모가 다른 경우에만 확인해주면 된다.
 * 또한, 부모가 다른 경우 값을 누적시키고, 병합하여 부모 값을 동일시 해주어야 한다.
 * 주의할 점은 사이클이 존재하지 않기 때문에 N개의 정점이 있다면, 연결된 간선의 수는 N-1개여야만 한다.
 *
 * 아래 풀이에서는 최소 값을 찾기 때문에 우선순위 큐를 활용하여, 값에 따라 오름차순 정렬을 진행하고
 * 큐 안에 값을 입력과 함께 다 담아준다. 그 후 큐가 비어있을때 까지 반복하며 부모가 다른 값들을 찾아주면
 * 해답을 얻게 되는 것이다.
 */

public class 행성_연결_16398 {
    private static int n;
    private static int[] parent;
    private static long answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // Step 1. 유니온 파인드에서 사용할 부모 값을 초기화 한다.
        parent = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Step 2. 우선순위 큐를 만들어 입력된 값들을 알맞게 담아준다.
        // 담는 값은 시작 정점, 종료 정점, 사이 값 이다.
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.v - o2.v);
        for(int i = 1; i <= n; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 1; j <= n; j++) {
                queue.add(new Node(i, j, Integer.parseInt(line[j-1])));
            }
        }

        // Step 3. 큐가 빌때까지 반복
        // 해당 과정을 통해 부모가 다른 경우는 사이클이 형성되지 않게 확인한다.
        while(!queue.isEmpty()) {
            Node node = queue.poll();

            int start = find(node.s); // find로 부모 값 확인
            int end = find(node.e);

            if(start != end) { // 부모가 다르다면?
                answer += node.v;
                union(start, end); // 병합 -> 부모를 하나의 값으로 일치시킴
            }
        }
        System.out.println(answer);
    }
    public static void union(int start, int end) {
        if(start == end) {
            return;
        }

        if(start < end) {
            parent[end] = start;
        }
        else {
            parent[start] = end;
        }
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }

    static class Node {
        int s;
        int e;
        int v;
        public Node(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }
}
