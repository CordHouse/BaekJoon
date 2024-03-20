package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/***
 * 24. 03. 21 03시 00분 시작
 * 24. 03. 21 03시 47분 종료
 * 성공 유무 -> 실패
 */

/**
 * 주어진 조건
 * 1. 의존성이 있는 컴퓨터는 상위 컴퓨터가 해킹 당했을때 일정 시간이 지나면 같이 해킹 된다.
 * 2. a는 b를 의존하고 있다면, b가 해킹된 경우 a도 s라는 시간이 지날 경우 해킹된다.
 *
 * 제한사항
 * 1 <= testcase <= 100
 * 1 <= n <= 10_000, 1 <= d <= 100_000, 1 <= c <= n
 * 1 <= a, b <= n, a != b, 0 <= s <= 1_000
 *
 * 풀이
 * 다익스트라로 풀어야 한다는 것을 인지해야하는 문제이다.
 * 문제에서 정점, 간선, 가중치 를 의미하는 단어를 언급한다면 의심해야한다.
 * 또한, 음수가 존재하지 않기 때문에 그래프 탐색 알고리즘 중 다익스트라를 연상해야한다.
 * 여기서 주의해야할 점이 있다.
 * 1. 다익스트라 알고리즘은 최소가 되는 값을 우선적으로 갱신해가야 한다.
 * -> 만일 큐에 담기는 순서에 기준이 정해지지 않는다면 흐름대로 갱신하기 때문에 원하는 값이 나오질 않으니 주의하자.
 * 2. 문제에서 시간이라는 개념이 들어간다. 모든 노드를 갱신해 값을 갱신하고 총 해킹되기 까지 시간을 찾아야 하기 때문에
 * 모든 노드를 방문해 갱신한 값 중에 MAX_VALUE 가 아닌 최대 값을 찾으면 문제를 해결할 수 있다.
 */

public class 해킹_10282 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        while(testcase --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 배열의 크기
            int d = Integer.parseInt(st.nextToken()); // 반복 횟수
            int c = Integer.parseInt(st.nextToken()); // start 시작점
            List<List<Node>> graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph.get(b).add(new Node(a, s));
            }

            dijkstra(graph, n, c);
        }
    }

    public static void dijkstra(List<List<Node>> graph, int n, int start) {
        int count = 0;
        int time = 0;
        int[] arr = new int[n+1]; // 인덱스 1번부터 시작
        boolean[] visit = new boolean[n+1]; // 중복 방문 체크
        Arrays.fill(arr, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.sec - o2.sec);
        pq.add(new Node(start, 0));
        arr[start] = 0;

        while(!pq.isEmpty()) {
            Node curNode = pq.poll();

            if(!visit[curNode.a]) {
                count++;
                visit[curNode.a] = true;
            }
            else {
                continue;
            }
            for(Node node : graph.get(curNode.a)) {
                if(!visit[node.a] && curNode.sec + node.sec < arr[node.a]) {
                    arr[node.a] = curNode.sec + node.sec;
                    pq.add(new Node(node.a, arr[node.a]));
                }
            }
        }

        for(int value : arr) {
            if(value == Integer.MAX_VALUE) {
                continue;
            }
            time = Math.max(time, value);
        }
        System.out.println(count + " " + time);
    }

    static class Node {
        int a, sec;

        public Node(int a, int sec) {
            this.a = a;
            this.sec = sec;
        }
    }
}
