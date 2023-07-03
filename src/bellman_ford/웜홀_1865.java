package bellman_ford;

/***
 * 23. 07. 03 14시 55분 시작
 * 23. 07. 03 15시 52분 종료
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
 * 1. N개의 지점, M개의 간선, W 개의 웜홀
 * 2. 웜홀을 타게되면 음수 값을 가질 수 있다.
 * 3. 웜홀은 방향성을 가지고 있다. (웜홀이 아닌 경우는 방향성이 없다.)
 *
 * 제한사항
 * 1 <= TC <= 5
 * 1 <= N <= 500
 * 1 <= M <= 2500
 * 1 <= W <= 200
 *
 * 풀이
 * 해당 문제에서 주어진 조건에 웜홀에 대한 개념을 빠르게 이해했다면 다른 그래프 알고리즘보다 벨만포드 알고리즘을
 * 사용해야한다는 것이 눈에 보일 것이다.
 * 벨만포드 알고리즘은 음수 가중치를 계산할 수 있고, 음수 가중치를 찾는 방법은 음수 사이클이 존재할 경우
 * 값이 변경된다는 점을 이용하면 된다.
 * 주의해야할 점은 INF 값을 MAX_VALUE로 잡을 경우 오버플로우 발생으로 잘못된 값이 나올 수 있다는 것이다.
 * 실제로 이 부분에서 틀린 부분 찾는데까지 시간이 오래 걸렸다.
 */

public class 웜홀_1865 {
    private static final int INF = 10_000_000;
    private static int n, m, w;
    private static List<Node>[] graph;
    private static int[] value;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        // Step 1. 테스트케이스 반복
        for(int tc = 0; tc < testCase; tc++) {
            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]); // 지점 수
            m = Integer.parseInt(input[1]); // 도로의 수
            w = Integer.parseInt(input[2]); // 웜홀 수

            // Step 2. 그래프에 대한 초기 값 설정
            graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            // Step 3. 웜홀에 대한 예외처리 해주기
            // 여기선 두 가지 방법이 있다.
            // 1. 함수를 만들어 웜홀에 대한 불린타입을 설정
            // 2. 지금 처럼 영역으로 구분
            for(int i = 0; i < m+w; i++) {
                String[] line = br.readLine().split(" ");
                int s = Integer.parseInt(line[0]);
                int e = Integer.parseInt(line[1]);
                int t = Integer.parseInt(line[2]);
                // 주어진 조건에서 m + 2 ~ m+w+1까지이기 때문에 m보다 큰 경우만 찾으면 된다.
                // i가 0부터 시작하기 때문에 위와 같은 m만 찾으면 되는 것이다.
                if(i < m) {
                    graph[s].add(new Node(e, t));
                    graph[e].add(new Node(s, t));
                    continue;
                }
                graph[s].add(new Node(e, -t));
            }

            // Step 4. 벨만포드 시작
            value = new int[n+1];
            System.out.println(bellmanFord() ? "YES" : "NO");
        }
    }

    public static boolean bellmanFord() {
        Arrays.fill(value, INF); // 여기가 핵심 INF 설정을 잘해야한다.
        boolean check = false;
        value[1] = 0;

        // 모든 지점을 n-1번 순회
        // 모든 지점을 순회하면서 변경되는 값을 확인한다 -> 사이클 확인 과정
        for(int i = 1; i < n; i++) {
            check = false;

            // 모든 간선을 순회
            // n 번째의 경우에도 음수는 발생하기 때문에 n 만큼을 포함시킨다.
            for(int j = 1; j <= n; j++) {
                for(Node node : graph[j]) {
                    if (value[j] != Integer.MAX_VALUE && value[node.e] > value[j] + node.t) {
                        value[node.e] = value[j] + node.t;
                        check = true;
                    }
                }
            }

            // 갱신이 발생하지 않았다면 종료
            if(!check) {
                break;
            }
        }
        return check;
    }

    static class Node {
        int e;
        int t;

        public Node(int e, int t) {
            this.e = e;
            this.t = t;
        }
    }
}
