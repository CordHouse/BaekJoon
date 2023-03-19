import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 강의실_배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cycle = Integer.parseInt(br.readLine());
        int[][] schedule = new int[cycle][2];

        for(int i = 0; i < cycle; i++) {
            String[] split = br.readLine().split(" ");
            schedule[i][0] = Integer.parseInt(split[0]);
            schedule[i][1] = Integer.parseInt(split[1]);
        }

        // 2차원 배열일 경우 정렬
        // o1[0]과 o2[0] 비교하여 같은 값인 경우 뒤에 오는 배열에 의해 정렬하기 위해서 아래와 같이 작성
        Arrays.sort(schedule, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        // 우선순위 큐를 사용하여, 큐에 새로운 값을 넣어 갱신할 때 매번 가장 작은 값이 나와 비교되도록 해야하기 때문
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        // 스케줄은 오름차순 정렬되어 있으므로, 가장 작은 값을 맨 처음에 담아준다.
        queue.offer(schedule[0][1]);

        // 반복문 한번으로 끝낼 수 있다.
        // 이유 -> 스케줄 내의 정렬된 값들을 한번씩 훑어보면서 값을 갱신 할 수 있기 때문이다.
        // 따라서 시간복잡도는 스케줄의 크기 * 우선순위 큐 라고 볼 수 있다.
        for(int i = 1; i < schedule.length; i++) {
            // queue.peek() : 우선순위 큐 내의 가장 작은 값을 선택
            // 스케줄 배열 값과 비교하여 큐보다 크거나 같은 값이 있는 경우만 갱신
            // ex) 1, 8 -> 8, 10 으로 변경되면 이상적이지만 해당 코드로 값을 찾아보면
            // 1, 8 -> 9, 16 으로 갱신되는 것을 볼 수 있다.
            // 애초에 시작시간을 기준으로 정렬했기 때문에 큐 배열을 위와같이 갱신한다면, 큐 내에는 가장 빨리끝나는
            // 시간을 위주로 값이 갱신되기 때문에 총 빌릴 강의실의 개수를 파악할 수 있게 된다.
            if(queue.peek() <= schedule[i][0]) {
                // 갱신시 가장 작은 값은 빼준다.
                queue.poll();
            }
            // 현재 값을 넣어 반영시킨다.
            // 큐 값보다 크다면 강의실을 만든다고 볼 수 있고, 아니라면 끝난 강의실에 새로운 강의를 넣을 수 있다.
            queue.offer(schedule[i][1]);
        }
        System.out.println(queue.size());
    }
}
