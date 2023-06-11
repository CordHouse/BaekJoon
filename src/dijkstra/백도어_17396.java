package dijkstra;

/***
 * 23. 06. 12 03시 07분 시작
 * 23. 06. 12 04시 32분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 주어진 조건
 * 1. 백도어를 진행함에 있어 적에게 시야가 들키지 않도록 이동해야 한다.
 * 2. 분기점 중 안보이는 곳은 0, 보이는 곳은 1로 표기된다.
 * 3. 분기점은 양방향으로 연결되어 있다.
 *
 * 제한사항
 * 1 <= N <= 100_000
 * 1 <= M <= 300_000
 * 0 <= a, b < N, a != b, 1 <= t <= 100_000
 *
 * 풀이
 * 다익스트라로 해결한다.
 * 다익스트라는 N개의 분기점과 M개의 간선으로 이루어져있다.
 * 또한, 분기점 마다 연결된 곳의 길이를 최소로 선택하여 움직여야 한다.
 * 움직이면서 분기점이 갱신될 때 마다 분기점의 거리를 갱신해주는게 핵심이다.
 * 추가적으로 풀다가 찾게된 부분인데, distance 는 100_000 * 300_000 이기 때문에 long 타입으로 선언해야한다.
 * -> 이거 못찾아서 한참을 고생했어요 ㅠㅠ... 코테시 제한사항 주의할 것!
 * 또 이미 방문했던 위치는 재 방문하지 않기 위해서 스킵해주는 것도 필요합니다!
 * 시간복잡도는 O(ElogE)를 갖게 된다. -> 우선순위 큐를 사용했기 때문에 그렇다.
 * 만약 우선순위 큐를 사용하지 않고 선형 탐색으로 풀었을 경우 O(n^2)의 시간복잡도를 갖게 되어 해당 문제에서
 * 시간초과가 발생한다.
 * */
public class 백도어_17396 {
    private static List<Node>[] list; // 분기점에 대한 연관관계를 담을 변수
    private static boolean[] check; // 조건에 주어진 보이지 않는 시야를 구분하기 위해 선언
    private static boolean[] visit; // 방문했던 분기점인지 확인하는 변수
    private static long[] arr; // 각 분기점 별 최소의 거리를 담는 변수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        arr = new long[n];
        Arrays.fill(arr, Long.MAX_VALUE); // 주의 Long 타입으로 선언해야함

        // Step 1. 보이지 않는 위치 설정하기
        check = new boolean[n];
        list = new ArrayList[n];
        input = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            if(input[i].equals("0")) {
                check[i] = true;
            }
            list[i] = new ArrayList<>();
        }

        // Step 2. 분기점 연관관계 형성
        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            long distance = Long.parseLong(line[2]);
            list[start].add(new Node(end, distance));
            list[end].add(new Node(start, distance));
        }

        // Step 3. 다익스트라 시작
        check[n-1] = true; // 문제 조건에 따라 마지막 도착 분기점은 시야가 보여도 이동할 수 있다.
        visit = new boolean[n]; // 방문 분기점 초기화
        dijkstra(0); // 문제 조건에 따라 시작 분기점은 0에서 시작

        long answer = arr[n-1];
        if(answer == Long.MAX_VALUE) { // 마지막 분기점이 long 타입의 max 값이면 -1 리턴
            answer = -1;
        }
        System.out.println(answer);
    }

    public static void dijkstra(int start) {
        // 우선순위 큐를 사용하여 시간복잡도 개선
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> (int)o1.distance - (int)o2.distance);
        queue.add(new Node(start, 0));
        arr[start] = 0; // 시작점은 거리가 0으로 고정

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(!check[node.end]) { // 시야가 보이는 곳이라면 더 이상 갈 수 없다.
                return;
            }
            if(visit[node.end]) { // 이미 방문한 곳이라면 스킵한다.
                continue;
            }
            else { // 방문하지 않았던 곳이라면 방문 체크
                visit[node.end] = true;
            }

            for(Node n : list[node.end]) { // 해당 분기점 별로 최소 값을 개산해 갱신
                if(check[n.end] && !visit[n.end] && arr[n.end] > n.distance + node.distance) {
                    arr[n.end] = n.distance + node.distance;
                    queue.add(new Node(n.end, arr[n.end]));
                }
            }
        }
    }

    static class Node {
        int end;
        long distance;

        public Node(int end, long distance) {
            this.end = end;
            this.distance = distance;
        }
    }
}
