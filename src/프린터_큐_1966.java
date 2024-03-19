import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 24. 03. 17 05시 40분 시작
 * 24. 03. 17 06시 30분 종료
 * 성공 유무 -> 실패
 */

/**
 * 주어진 조건
 * 1. 현재 Queue의 가장 앞에 있는 문서의 "중요도"를 확인한다
 * 2. 나머지 문서들 중 현재 문서보다 높은 문서가 하나라도 있다면 문서를 인쇄하지 않고 가장 뒤로 배치한다.
 * 3. 그렇지 않다면 바로 인쇄한다.
 *
 * 제한사항
 * 1 <= N <= 100
 * 0 <= M < N
 * 1 <= 중요도 <= 9
 *
 * 풀이
 * 이 문제의 핵심은 두 가지 문제를 해결 할 수 있는가 이다.
 * 1. 일반적으로 보이지 않는 "순서"라는 개념이 존재한다.
 * 2. 내가 알고자 하는 인덱스 값을 기준으로 큰 값이 모두 없어질때까지 반복하면서 큐에서 값을 뺴준다.
 * 결국 큐에 남은 숫자는 나와 같은 중요도를 갖거나 낮은 중요도를 갖는 값이다.
 * 위에 주어진 조건과 같이 높은 문서가 없기 때문에 바로 인쇄하면 된다. 하지만 중요도를 확인하기 떄문에
 * 중요도가 낮은건 계속 가장 마지막 위치로 보내줘야하고 같은 중요도에서 해당 인덱스 번호가 나올떄까지 돌려주면 된다.
 */
public class 프린터_큐_1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        while(testcase --> 0) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = input[0];
            int m = input[1];
            int[] number = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Queue<Index> queue = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            int count = 0;

            // 큐에 값 넣기 ( 순서를 위해서 )
            // 우선순위 큐 값 넣기 ( 가장 큰 값을 넣어 우선적으로 뺴준다. ) 조건 필수! 큰 값이 먼저!
            for(int i = 0; i < n; i++) {
                queue.add(new Index(i, number[i]));
                if(i != m && number[m] < number[i]) {
                    pq.add(number[i]);
                }
            }

            // 반복하면서 값을 체크해준다.
            while(!queue.isEmpty()) {
                Index value = queue.poll();

                // 우선순위 큐에 큰 값이 남아있는 경우
                if(!pq.isEmpty()) {
                    if(pq.peek() == value.priority) {
                        pq.poll();
                        count++;
                    }
                    else {
                        queue.add(value);
                    }
                }
                // 중요도가 같거나 낮은 경우
                // 우선순위가 같다면 카운트를 증가시키고, 해당 인덱스가 나오면 증가시키고 탈출해준다.
                else {
                    if (value.index == m) {
                        count++;
                        break;
                    }
                    else if(value.priority == number[m]) {
                        count++;
                    }
                    else {
                        queue.add(value);
                    }
                }
            }
            System.out.println(count);
        }
    }

    static class Index {
        int index;
        int priority;

        public Index(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}
