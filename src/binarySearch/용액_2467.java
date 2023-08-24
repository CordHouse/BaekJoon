package binarySearch;

/**
 * 23. 08. 24 15시 20분 시작
 * 23. 08. 24 16시 50분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 주어진 조건
 * 1. 용액이 음수와 양수로 주어진다.
 * 2. 오름차순으로 용액의 값이 주어진다.
 * 3. 0에 수렴하는 가장 작은 값을 찾아라
 *
 * 제한사항
 * 2 <= N <= 100_000
 * -1_000_000_000 <= M <= 1_000_000_000
 *
 * 풀이
 * 주어진 배열에서 값 2개를 선택해 0에 수렴하는 가장 작은 값을 찾아야한다.
 * 우선 투포인터를 활용하여 두 값을 선정하고, 그 값을 더한 값을 도출한다.
 * 도출한 값을 토대로 최소 값을 저장하여 비교하고, 그때 가장 최소가 되는 값이 정답으로 나올 수 있게 한다.
 * 또한, 0이 되는 값이 발생한다면 바로 종료하여 값을 도출한다.
 * 문제에서 정답이 여러개라면 하나만 도출되어도 정답으로 인정되며, 중복된 인덱스 값만 아니면 모두 정답이다.
 */

public class 용액_2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        long[] arr = new long[n];

        // Step 1. 변수를 입력받는다. (오름차순 정렬되어 있음)
        for(int i = 0; i < line.length; i++) {
            arr[i] = Long.parseLong(line[i]);
        }

        // Step 2. 투 포인터 활용
        int start = 0;
        int end = arr.length-1;
        long min = Long.MAX_VALUE;
        long[] answer = new long[2];
        // 같은 인덱스가 나오지 않도록 주의
        while(start < end) {
            long sum = arr[start] + arr[end];

            // 합이 0인경우 정답으로 바로 도출
            if(sum == 0) {
                answer[0] = arr[start];
                answer[1] = arr[end];
                break;
            }
            else {
                // 아닌 경우 0에 수렴하는 최소의 값을 찾는다.
                if(Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    answer[0] = arr[start];
                    answer[1] = arr[end];
                }
                // 음수에 더 치우쳐 있다면 오른쪽으로 한칸
                if (sum < 0) {
                    start++;
                } else { // 그게 아니라면 왼쪽으로 한칸
                    end--;
                }
            }
        }

        Arrays.sort(answer);
        StringBuilder sb = new StringBuilder();
        for(long v : answer) {
            sb.append(v).append(" ");
        }
        System.out.println(sb);
    }
}
