package topologicalSort;

/***
 * 23. 07. 30 01시 51분 시작
 * 23. 07. 30 02시 10분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 선수과목으로 이루어진 과목이 주어지며, 항상 A < B이다.
 *
 * 제한사항
 * 1 <= N <= 1000
 * 0 <= M <= 500_000
 * 1 <= A < B <= N
 *
 * 풀이
 * 이번 문제는 위상정렬로 푸는 문제이다.
 * 입력에 대한 값을 보면 항상 A < B이기 때문에 A에 대해 오름차순 정렬을 진행한다면 선수과목에 대한 정렬이 이루어지는데
 * 이 값을 기준으로 선수 과목 + 1을 진행해주면 위상이 한칸 더 높은 값으로 바뀌게 된다.
 * 하지만 여기서 주의해야하는 점이 선수과목 + 1로만 무조건 바꾸면 되는 것이 아니라, 위상은 변경되면 안되기 때문에 가장
 * 큰 값으로만 가질 수 있도록 설정해야한다.
 * */

public class 선수과목_14567 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // Step 1. 각 위상의 값을 담을 변수 1로 초기화
        int[] arr = new int[n+1];
        Arrays.fill(arr, 1);

        // Step 2. 우선순위 큐를 활용하여 정렬한 값을 가져옴
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.f - o2.f);
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            queue.add(new Node(f, s));
        }

        // Step 3. 반복을 통해 위상의 큰 값을 찾음
        while(!queue.isEmpty()) {
            Node node = queue.poll();

            arr[node.s] = Math.max(arr[node.s], arr[node.f] + 1);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }

    static class Node {
        int f;
        int s;
        public Node(int f, int s) {
            this.f = f;
            this.s = s;
        }
    }
}
