package union_find;

/**
 * 23. 09. 14 11시 30분 시작
 * 23. 09. 15 00시 33분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. p개의 노드와 w개의 길이 표현된 그래프에서 모든 길은 양방향으로 연결되어 있다.
 * 2. 좁은 길목을 지나갈 수 있는 너비의 최대화라는 의미는 c와 v가 연결되는 길 중 가장 작은 길을 선택한다.
 *
 * 제한사항
 * 2 <= p <= 1_000
 * 1 <= w <= 50_000
 * 0 <= c, v < p, c != v
 * 0 <= s, e < p, s != e, 1 <= w <= 1_000
 *
 * 풀이
 * 유니온 파인드 알고리즘을 활용해야 하는 문제이다.
 * 크루스칼 알고리즘을 통해서 그래프 상에서 주어진 조건에 따라 연결된 여부를 판단해야하기 때문이다.
 * 우선 가장 넓이가 가장 큰 노드의 연결을 선택하면서 c와 v가 연결되는 시점을 찾는다면 위 주어진 조건 2번처럼
 * 좁은 길목을 지나가는 너비가 최대가 되는 것이다.
 */

public class 군사_이동_11085 {
    private static int[] parent;
    private static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Step 1. 변수 입력
        // p : 노드 개수, w : 주어지는 길의 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        // c : B world, v : C world
        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        // Step 2. 부모노드 초기화
        parent = new int[p];
        for(int i = 1; i < p; i++) {
            parent[i] = i;
        }

        // Step 3. Pq를 활용해 너비가 큰 노드를 먼저 선택해 가져온다
        PriorityQueue<Load> queue = new PriorityQueue<>((o1, o2) -> o2.width - o1.width);
        for(int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            queue.add(new Load(s, e, width));
        }

        // Step 4. 반복하면서 c == v가 되면 두 노드가 연결된 것으로 간주한다.
        while(!queue.isEmpty()) {
            Load load = queue.poll();

            if(find(c) == find(v)) {
                break;
            }
            union(load.s, load.e);
            answer = load.width;
        }

        System.out.println(answer);
    }

    public static void union(int s, int e) {
        int findS = find(s);
        int findE = find(e);
        if(findS < findE) {
            parent[findE] = findS;
        }
        else {
            parent[findS] = findE;
        }
    }
    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }

        return parent[num] = find(parent[num]);
    }

    static class Load {
        int s, e, width;
        public Load(int s, int e, int width) {
            this.s = s;
            this.e = e;
            this.width = width;
        }
    }
}
