package dfs_bfs;

/***
 * 23. 06. 27 15시 44분 시작
 * 23. 06. 27 18시 35분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 주어진 조건
 * 1. 이모티콘 초기 값은 1이다.
 * 2. 3가지 연산을 통해 이모티콘을 S개 만들어야 한다.
 * 2-1) 이모티콘 복사
 * 2-2) 이모티콘 붙여넣기
 * 2-3) 이모티콘 삭제
 * 3. 이모티콘을 만드는데 걸리는 최소 시간을 구하여라.
 *
 * 제한사항
 * 2 <= S <= 1000
 *
 * 풀이
 * 넓이 우선 탐색으로 풀어보면 좋을 법한 문제이다.
 * 우선 3가지의 가능성이 동시에 작용해야하기 때문에 BFS를 먼저 떠올려야 한다.
 * 한 동작당 1초의 시간이 흐르기 때문에 큐에 담는 Time을 각각 제어주어야 한다.
 * 주의해야할 점은 동작마다 조건이 있는데, 이모티콘을 모두 복사하는 경우에는 이모티콘이 하나 이상 존재해야하고
 * 이모티콘을 붙여넣을 경우 처음 복사한 어떠한 값이 하나라도 존재해야한다.
 * 마지막으로 삭제 시 이모티콘이 음수가 되는 것을 고려해 음수 방지를 해주어야 한다.
 * 또한 이번 문제는 메모리 초과가 발생했던 문제였고 그때 주의해야할 점은 3가지의 동작이 쌓이면서 발생할 수 있는 문제이다.
 * 따라서 메모리 초과는 방문처리를 통해 갔던 구간의 방문을 줄여 해결해볼 수 있다. -> 여기서 시간 너무 오래 잡아 먹힘...
 */

public class 이모티콘_14226 {
    private static int answer = Integer.MAX_VALUE;
    private static int s;
    private static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = Integer.parseInt(br.readLine());

        visit = new boolean[s+1][s+1];

        bfs();
        System.out.println(answer);
    }

    public static void bfs() {
        PriorityQueue<Time> queue = new PriorityQueue<>((o1, o2) -> o1.stackTime - o2.stackTime);
        queue.add(new Time(0, 1, 0));
        visit[0][1] = true;

        while(!queue.isEmpty()) {
            Time time = queue.poll();

            if(time.stackS == s) {
                answer = Math.min(answer, time.stackTime);
                break;
            }
            // 해당 구간이 추가되면 각각의 복사, 붙여넣기, 삭제에 따른 제제가 아닌 3가지 모두를 제제해버리기 때문에
            // 각각 해당 구간에서의 제제를 해주어야 정상적으로 동작한다.
//            if(time.save >= s || time.stackS <= 0 || time.stackS > s || time.save + time.stackS > s) {
//                continue;
//            }

            if(time.stackS != 0 && !visit[time.stackS][time.stackS]) {
                queue.add(new Time(time.stackS, time.stackS, time.stackTime + 1)); // 이모티콘 모두 복사
                visit[time.stackS][time.stackS] = true; // 방문처리
            }
            if(time.save > 0 && time.save + time.stackS <= s && !visit[time.save][time.save + time.stackS]) {
                queue.add(new Time(time.save, time.save + time.stackS, time.stackTime + 1)); // 이모티콘 붙여 넣기
                visit[time.save][time.save + time.stackS] = true; // 방문처리
            }
            if(time.stackS - 1 >= 0 && !visit[time.save][time.stackS - 1]) {
                queue.add(new Time(time.save, time.stackS - 1, time.stackTime + 1)); // 이모티콘 하나 삭제
                visit[time.save][time.stackS - 1] = true; // 방문처리
            }
        }
    }

    static class Time {
        int save;
        int stackS;
        int stackTime;
        public Time(int save, int stackS, int stackTime) {
            this.save = save;
            this.stackS = stackS;
            this.stackTime = stackTime;
        }
    }
}
