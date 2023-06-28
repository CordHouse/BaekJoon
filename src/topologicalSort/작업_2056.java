package topologicalSort;

/***
 * 23. 06. 29 02시 10분 시작
 * 23. 06. 29 03시 14분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 작업을 수행하기 이전 선행해야 하는 작업이 존재한다.
 * 2. 해당 작업을 선행관계가 있다고 표현한다.
 * 3. K번 작업을 시작하기 이전에 반드시 먼저 완료되어야하는 작업들의 번호는 모두 1 이상 k-1 이하이다.
 * 4. 선행 관계가 없는 작업이 반드시 하나 이상 존재한다.
 *
 * 제한사항
 * 0 <= 개수 <= 100
 *
 * 풀이
 * 해당 문제는 4번 조건을 보고 위상정렬을 떠올려야한다.
 * 위상 정렬은 인풋이 0이고 아웃풋이 여러개일때, 인풋이 0인 모든 노드 값을 큐에 담고, 아웃 풋을 줄여
 * 다른 노드의 인풋을 줄이는 과정이 필요하다. 그렇게 해야 위상 정렬의 조건이 달성되기 때문이다.
 * 따라서 해당 문제는 Step 3 ~ 5 과정이 핵심이라고 볼 수 있다.
 */

public class 작업_2056 {
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] time = new int[n+1];
        int[] count = new int[n+1];
        List<Integer>[] match = new ArrayList[n+1];
        Queue<Node> queue = new LinkedList<>();

        // Step 1. 변수 초기화
        // match 안에는 선행 작업해야하는 숫자들이 들어간다.
        for(int i = 1; i <= n; i++) {
            match[i] = new ArrayList<>();
        }
        // Step 2. 입력 값 저장
        // time : 주어진 시간 값
        // count : 해당 위치에서 선행해야하는 작업의 개수
        for(int i = 1; i <= n; i++) {
            String[] input = br.readLine().split(" ");
            time[i] = Integer.parseInt(input[0]);
            count[i] = Integer.parseInt(input[1]);
            // 2보다 커야하는 이유는 0과 1이 시간과 개수로 쓰이기 때문
            if(input.length > 2) {
                for (int j = 2; j < input.length; j++) {
                    match[Integer.parseInt(input[j])].add(i);
                }
            }
        }

        // Step 3. temp 변수에 time 값 집어넣기
        // 해당 과정은 time이 베이스 값이기 때문에 실시간으로 변하면 전체적인 값이 변하기 때문에
        // 변하는 과정을 담을 변수 배열이 필요하기 때문에 선언
        int[] temp = new int[n+1];
        for(int i = 1; i <= n; i++) {
            temp[i] = time[i];

            // 위상 정렬은 인풋이 0인 값만을 담아야한다.
            if(count[i] == 0) {
                queue.add(new Node(i, match[i]));
            }
        }

        // Step 4. 위상정렬 시작
        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for(int v : node.list) {
                count[v]--; // 위상정렬을 위해 인풋 값을 하나씩 없애준다.

                // 점화식
                temp[v] = Math.max(temp[v], temp[node.index] + time[v]);

                if(count[v] == 0) {
                    queue.add(new Node(v, match[v]));
                }
            }
        }

        // Step 5. 최소 값 찾기
        // 최소 값인데 max로 쓰는 이유는 최소한의 시간을 가지기 위해선 한 위상에서의 최대 시간을 알아야
        // 다음 위상이 활용할때 최소가 되기 때문이다.
        // 같은 위상은 동시작업 가능이기 때문에 한 위상에서 제일 큰 값을 골라준다.
        // -> 모든 작업은 수행되어야 하기 때문이다.
        int answer = 0;
        for(int v : temp) {
            answer = Math.max(answer, v);
        }
        System.out.println(answer);
    }
    static class Node{
        int index;
        List<Integer> list;
        public Node(int index, List<Integer> list) {
            this.index = index;
            this.list = list;
        }
    }
}
