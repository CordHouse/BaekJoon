package tree;

/***
 * 23. 07. 16 01시 25분 시작
 * 23. 07. 16 02시 25분 종료
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
 * 1. 1 ~ N번의 숫자로 이루어진 섬이 존재한다.
 * 2. 1번을 제외한 섬에서부터 시작하여 1번의 섬으로 이동하면서 W = Wolf를 만나면 양이 1:1 비율로 잡아먹힌다.
 * 3. S = Sheep 인 섬은 해당 인원만큼 더해져 이동할 수 있게 된다.
 * 4. 최종 목적지에 도착했을때 1번섬에 모두 몇명의 양이 도착할 수 있는지 구하시오
 *
 * 제한사항
 * 2 <= N <= 123_456
 * 1 <= 늑대, 양 <= 10^9
 * 1 <= 섬 <= N
 *
 * 풀이
 * 이 문제를 처음 접근할 때 우선순위 큐를 활용하여 접근해보려고 했으나 실패했다.
 * 해당 문제의 해법은 3가지 순회 방법 중 후위 순회를 이용해야 한다.
 * 후위 순회는 리프 노드로 시작하여 상위 노드로 올라가는 방식인데, 해당 트리 구조가 가장 먼 리프 노드부터
 * 상위 노드로 연산하면서 올라와 1번 섬으로 도달해야하는 것이기 때문이다.
 * 참고 : 순회방법은 - 전위 순회, 후위 순회, 중위 순회의 방식이 존재한다.
 * 기억하기 쉬운 방법으로는 부모 노드가 앞에 있냐, 중간에 있냐, 뒤에 있냐를 기준으로 생각하면 빠르게 외울 수 있다.
 */
public class 양_구출_작전_16437 {
    private static int n;
    private static int[] countList; // 마릿 수
    private static char[] animalList; // S, W
    private static List<Integer>[] list; // index : 부모노드, value : 리프 노드
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // Step 1. 입력을 담을 변수를 만든다.
        countList = new int[n+1];
        animalList = new char[n+1];
        list = new ArrayList[n+1];
        for(int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // Step 2. 입력 값 대입
        StringTokenizer st;
        for(int i = 2; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            char animal = st.nextToken().charAt(0);
            int count = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            animalList[i] = animal;
            countList[i] = count;
            list[num].add(i);
        }

        // Step 3. 후위 순회 (최 상위 노드를 입력해주고 리프노드로 이동한다.)
        System.out.println(postOrder(1));
    }

    public static long postOrder(int start) {
        long answer = 0; // 양과 늑대의 갯수가 10^9까지 존재하기 떄문에 long으로 선언

        // 리프노드를 하나씩 방문하면서 값을 더해준다.
        // 여기서 주의해야할 사항으로는 각 부모 노드 별로 값이 누적되어야 하기 때문에
        // 전역변수로 answer를 선언해주면 다른 값이 나온다.
        for(int next : list[start]) {
            answer += postOrder(next);
        }

        // 방문한 섬에 양들이 있는 경우에 양들의 수를 더해주고
        if(animalList[start] == 'S') {
            return answer + countList[start];
        }
        // 늑대가 있는 경우 그만 큼 빼준다. (음수인경우 0으로 초기화)
        return Math.max(answer - countList[start], 0);
    }
}
