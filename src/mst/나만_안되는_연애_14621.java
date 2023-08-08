package mst;

/**
 * 23. 08. 09 01시 12분 시작
 * 23. 08. 09 02시 58분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 사심 경로는 남초 대학교와 여초 대학교들을 연결하는 도로로만 이루어져 있다.
 * 2. 다양한 사람들과 미팅할 수 있도록 어떤 대학교에서든 모든 대학으로 이동 가능하다.
 * 3. 최단 거리를 구해야 한다.
 *
 * 제한사항
 * 2 <= N <= 1_000
 * 1 <= M <= 10_000
 * 1 <= u, v <= N
 * 1 <= d <= 1_000
 *
 * 풀이
 * 최소 신장 트리의 알고리즘 중 하나인 크루스칼 알고리즘으로 해결할 수 있는 문제이다.
 * 일반적으로 다른 크루스칼 알고리즘과 비슷하지만, 다른점은 M과 W에 대한 연결점에 대한 고민이다.
 * 각 정점에 대해 연결이 되면서 최소를 선택하기 때문에 연결이 모두가 정상적으로 이루어진다면 전체갯수의 -1 만큼이
 * 계산 되어야한다. 즉, 문제 조건 중 모두를 연결할 수 없는 경우를 꼭 고려해 -1 처리를 해주어야한다.
 */

public class 나만_안되는_연애_14621 {
    private static int n, m;
    private static String[] type;
    private static List<Node> list = new ArrayList<>();
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        type = new String[n+1]; // M, W에 대한 구분이 이루어진다.
        parent = new int[n+1]; // 정점에 대한 방문처리가 이루어진다.

        // Step 1. M과 W에 대해 표시하고, 정점에 대한 초기 값을 설정해준다.
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            type[i] = st.nextToken();
            parent[i] = i;
        }

        // Step 2. 각 정점에 연결된 정보를 list 형식으로 담아준다.
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            list.add(new Node(s, e, distance));
        }

        // Step 3. 거리에 따른 최소 값으로 정렬하여 최소 값을 구해야한다.
        list.sort((o1, o2) -> o1.distance - o2.distance);

        int answer = 0;
        int count = 0;
        // Step 4. 여기서 핵심은 s와 e에 대해 연결했는지 여부를 확인하고 다를 경우 M과 W에 의해 연결할 수 있는 도로인
        // 지 확인 해주어야 한다.
        for(int i = 0; i < list.size(); i++) {
            Node node = list.get(i);

            if(find(node.s) != find(node.e)) {
                if(!type[node.s].equals(type[node.e])) {
                    count++;
                    answer += node.distance;

                    union(node.s, node.e);
                }
            }
        }

        // 간선이 만들어질 수 없는 경우 -1 처리
        if(n-1 != count) {
            answer = -1;
        }
        System.out.println(answer);
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }

        return parent[num] = find(parent[num]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) {
            parent[y] = x;
        }
    }

    static class Node {
        int s;
        int e;
        int distance;

        public Node(int s, int e, int distance) {
            this.s = s;
            this.e = e;
            this.distance = distance;
        }
    }
}
