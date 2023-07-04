package floyd_warshall;

/***
 * 23. 07. 05 01시 02분 시작
 * 23. 07. 05 02시 34분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 1번부터 N번 까지 번호가 붙여있는 학생들에게 두 학생끼리의 키를 비교한다.
 * 2. 입력 값들 중 앞은 작은 뒤는 큰 키를 가진 학생을 의미한다.
 * 3. 학생들의 키를 여러번 비교하면서 확실하게 키의 순서를 알 수 있는 학생은 몇명인지 찾아야한다.
 * 4. N명의 학생들의 키는 모두 다르다.
 *
 * 제한사항
 * 2 <= N <= 500
 * 0 <= M <= N(N-1)/2
 * a != b ( a < b )
 *
 * 풀이
 * 해당 문제의 알고리즘의 원래 의도는 플로이드 워샬로 풀어야한다.
 * 그래프를 사용하며, 인풋 값이 최대 500이기 때문에 O(n^3)으로 풀어도 시간초과 우려가 없기 때문이다.
 * 하지만 나는 이번 문제를 해당 알고리즘을 적용시킨다기 보다 구현 문제로 풀어보게 되었다.
 * 나의 풀이의 핵심은 한 학생을 선택했을때 나보다 앞에 있는 사람이 정해져있고, 뒤에 있는 사람이 정해져있다면
 * 나의 앞에 있는 사람부터 중복을 제외하고 체크한 뒤, 나의 뒷 사람을 또 체크해 내려간다.
 * 해당 문제를 풀면서 앞과 뒤를 한번에 하려 했을때는 모든 경우를 훑어보기 때문에 전체가 나올 수 밖에 없었다.
 * 위와 같은 문제를 해결하기 위해 앞과 뒤에 따른 서칭을 구별해주었고, 정확한 정답을 도출 할 수 있었다.
 */
public class 키_순서_2458 {
    private static int n, m, answer;
    private static List<Integer>[] front;
    private static List<Integer>[] back;
    private static Set<Integer> set;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        // Step 1. 변수 입력
        n = Integer.parseInt(input[0]); // 학생들 인원 수
        m = Integer.parseInt(input[1]); // 비교 횟수

        // Step 2. 앞과 뒤에 따른 변수 설정
        front = new ArrayList[n+1];
        back = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            front[i] = new ArrayList<>();
            back[i] = new ArrayList<>();
        }

        // Step 3. 입력 값을 저장
        // front -> 내 기준 앞에 있는 경우
        // back -> 앞 사람 기준 내가 뒤에 있는 경우
        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);

            front[a].add(b);
            back[b].add(a);
        }

        // Step 4. 구현 시작
        // 1번부터 N번까지 다 확인해주어야 한다.
        // -> 번호에 따라 기준이 달라지기 때문이다.
        for(int i = 1; i <= n; i++) {
            function(i);
        }
        System.out.println(answer);
    }
    // 찾는 함수
    public static void search(Queue<List<Integer>> queue, boolean type) {
        while(!queue.isEmpty()) {
            List<Integer> list = queue.poll();

            // 비어있을 수도 있기 때문에 비어있는 경우는 제외한다.
            if(list.size() == 0) {
                continue;
            }

            // list 값을 하나씩 뽑아서 방문하지 않는 곳이라면 방문하고, set에 추가해준다.
            for(int v : list) {
                if(!visit[v]) {
                    set.add(v);
                    if(type) {
                        queue.add(front[v]);
                    }
                    else {
                        queue.add(back[v]);
                    }
                    visit[v] = true;
                }
            }
        }
    }
    // 구현 함수
    public static void function(int start) {
        set = new HashSet<>(); // 중복된 값이 없도록 set 사용
        set.add(start); // 기준이 되는 지점은 먼저 담아준다.
        visit = new boolean[n+1]; // 방문처리로 중복된 방문이 없도록 확인한다.
        Queue<List<Integer>> frontQueue = new LinkedList<>(); // 앞에 해당하는 값들을 큐에 담아준다.
        Queue<List<Integer>> backQueue = new LinkedList<>(); // 뒤에 해당하는 값들을 큐에 담아준다.
        frontQueue.add(front[start]); // 초기 값 입력
        backQueue.add(back[start]); // 초기 값 입력

        search(frontQueue, true); // 앞에 있는 친구들을 확인해준다.
        search(backQueue, false); // 뒤에 있는 친구들을 확인해준다.

        // 앞과 뒤를 확인했을때 전체인원 n과 같다면 위치를 알 수 있는 학생이다.
        if(set.size() == n) {
            answer++;
        }
    }
}
