package mst;
/***
 * 23. 06. 09 14시 58분 시작
 * 23. 06. 09 15시 21분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 주어진 조건
 * 1. 컴퓨터를 연결하는 비용을 최소로한다.
 * 2. 모든 컴퓨터를 연결한다.
 *
 * 제한사항
 * 1 <= N <= 1_000
 * 1 <= M <= 100_000
 * a와 b는 같을 수도 있다.
 *
 * 풀이
 * MST -> 최소 신장 트리
 * 크루스칼 알고리즘 사용 -> O(ElogE) -> 간선의 수가 적은 경우 유리
 * 프림 알고리즘 -> O(n^2) -> 간선의 수가 많은 경우 유리
 * 성능 개선 전 List 풀이
 * 성능 개선 후 PriorityQueue 풀이
 * */
public class 네트워크_연결_1922 {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // 유니온 파인드를 구현
        parent = new int[n+1]; // 1부터 시작
        for(int i = 1; i <= n; i++) { // 부모 배열 초기화
            parent[i] = i;
        }

//        // 노드에 컴퓨터간의 간선정보 집어넣기
//        List<Node> nodeList = new ArrayList<>();
//        for(int i = 0; i < m; i++) {
//            String[] line = br.readLine().split(" ");
//            int s = Integer.parseInt(line[0]);
//            int e = Integer.parseInt(line[1]);
//            int v = Integer.parseInt(line[2]);
//            nodeList.add(new Node(s, e, v));
//        }
//
//        // 정렬하기
//        Collections.sort(nodeList, (o1, o2) -> o1.value - o2.value);
//
//        // 컴퓨터 사이를 확인하고 부모 배열에 대해 값을 바꿔 사이클되지 않도록 설정한다.
//        for(int i = 0; i < m; i++) {
//            Node node = nodeList.get(i);
//            if(find(node.start) != find(node.end)) { // 두 부모가 같은지 먼저 확인 -> 오름차순 정렬 했기 때문에 가능
//                answer += node.value;
//                union(node.start, node.end); // 부모 병합과정 시작 -> 부모를 같게 만들어 줌으로서 순환구조를 생성하지 않음
//            }
//        }
//
//        System.out.println(answer);
        // 노드에 컴퓨터간의 간선정보 집어넣기
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);
        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int s = Integer.parseInt(line[0]);
            int e = Integer.parseInt(line[1]);
            int v = Integer.parseInt(line[2]);
            queue.add(new Node(s, e, v));
        }

        // 컴퓨터 사이를 확인하고 부모 배열에 대해 값을 바꿔 사이클되지 않도록 설정한다.
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(find(node.start) != find(node.end)) { // 두 부모가 같은지 먼저 확인 -> 오름차순 정렬 했기 때문에 가능
                answer += node.value;
                union(node.start, node.end); // 부모 병합과정 시작 -> 부모를 같게 만들어 줌으로서 순환구조를 생성하지 않음
            }
        }

        System.out.println(answer);
    }

    public static void union(int start, int end) {
        int startParent = find(start);
        int endParent = find(end);

        if(startParent == endParent) {
            return;
        }

        if(startParent < endParent) {
            parent[endParent] = startParent;
            return;
        }
        parent[startParent] = endParent;
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]); // 최적화 갱신
    }

    static class Node{
        int start;
        int end;
        int value;

        public Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
}
