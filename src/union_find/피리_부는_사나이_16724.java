package union_find;

/**
 * 23. 08. 14 13시 24분 시작
 * 23. 08. 14 14시 13분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 움직일 수 있는 방향은 상, 하, 좌, 우
 * 2. 최소의 SAFE ZONE 생성
 *
 * 제한사항
 * 1 <= N <= 1_000
 * 1 <= M <= 1_000
 * 지도 밖으로 나가는 입력은 없다.
 *
 * 풀이
 * 해당 문제는 DFS로 해결이 가능하나, 유니온 파인드로 접근하여 풀어보고자 한다.
 * 두 알고리즘의 해결방안의 핵심은 싸이클이 생기는 위치에 대해 한 집합으로 묶고, 그에 해당하는 숫자를 카운팅 해주면 된다.
 * DFS의 경우엔 지나갈 수 있는 경로는 1, 2, 3, 등과 같이 숫자로 표기하여 체크할 수 있고 갯수를 파악할 수 있다.
 * 유니온 파인드의 경우 부모의 집합을 하나로 만드는 과정에서 몇개가 나오는지 확인할 수 있다.
 * 유니온 파인드의 경우 풀이시 2차원 배열을 1차원으로 만들었기 때문에 인덱싱을 잘 해주어야 한다.
 */

public class 피리_부는_사나이_16724 {
    private static int[] parent;
    private static char[][] map;
    private static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // Step 1. 변수 선언 및 값 입력받기
        map = new char[n][m];
        parent = new int[n * m];

        // 2차원 값 -> 1차원 값으로 매칭
        for(int i = 0; i < n*m; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // Step 2. 현재 나의 위치와 다음 이동할 위치를 찾고 병합하는 과정
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int index = i * m + j;
                int nextIndex = searchIndex(i, j);

                int cur = find(index);
                int next = find(nextIndex);

                if(cur != next) {
                    union(cur, next);
                }
            }
        }

        // Step 3. 서로 다른 숫자를 찾으면 그것이 SAFE ZONE 의 갯수가 된다.
        Set<Integer> set = new HashSet<>();
        for(int v : parent) {
            set.add(find(v));
        }

        System.out.println(set.size());
    }

    public static void union(int cur, int next) {
        if(cur < next) {
            parent[next] = cur;
        } else {
            parent[cur] = next;
        }
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }

    // 주의 : 1차원 배열로 만든 것이기 때문에 그것에 맞게 이동해야한다.
    public static int searchIndex(int i, int j) {
        if(map[i][j] == 'U') {
            i-=1;
        }
        else if(map[i][j] == 'D') {
            i+=1;
        }
        else if(map[i][j] == 'L') {
            j-=1;
        }
        else {
            j+=1;
        }
        return i * m + j;
    }
}
