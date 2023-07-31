package stacksum;

/**
 * 23. 07. 31 16시 00분 시작
 * 23. 07. 31 16시 30분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 주어진 조건
 * 1. 1번 ~ N번 까지 지점이 있다.
 * 2. 각 지점들을 차례로 그리고 원형으로 연결되어 있다.
 * 3. 이 지점들 중 두 곳에 두 개의 탑을 세우려고 한다.
 * 4. 두 탑의 거리가 최대값을 구하여라.
 * 5. 최대 값은 두 값중 가장 작은 값을 선택한다. -> 왼쪽 방향, 오른쪽 방향 중 작은 값 택한다.
 *
 * 제한사항
 * 2 <= N <= 50_000
 * 전체 거리의 총 합은 <= 1_000_000_000
 *
 * 풀이
 * 누적합을 이용해 푸는 문제이다.
 * 각 지점들이 서로 연결되어 원형의 모습을 이루고 있기 때문에 왼쪽 방향과 오른쪽 방향으로 값을 더해가면서 최적의 값을
 * 구할 수 있다.
 * 그 중 왼쪽과 오른쪽의 값이 가장 작은 값을 선택해야한다.
 * 단, 주의해야하는 점이 왼쪽과 오른쪽을 비교하다보면 원형의 모습으로 도는 것이다 보니 같은 값을 왼쪽이냐 오른쪽이냐만 다르게
 * 나타나게 되어있다. -> 최종적으로 전체 합이 같기 때문이다.
 * */

public class 두_개의_탑_2118 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        // Step 1. 입력에 대한 전체 합을 구한다.
        int sum = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }

        int start = 0; // 시작점
        int end = 1; // 이동하는 지점
        int answer = 0;
        int current = arr[start]; // 현재 누적된 값

        // Step 2. 누적합 시작
        while(start < end && end < n) {
            // 현재 누적된 값과 전체 값에서 누적값을 뺀걸 비교한 최소값을 구한다.
            // -> 오른쪽으로 이동하면서 왼쪽값을 구하는 것이다.
            int min = Integer.min(current, sum-current);

            // 최대 값을 찾아 갱신
            answer = Integer.max(answer, min);

            // min 값이 sum - current 값으로 바뀌게 되면 else 문 동작
            // 중복된 값을 피하기 위해서 current 가 최소 값이 유지될 경우에만 동작
            // -> 전체 합이 같기 때문에 중복해서 나왔던게 나올 수 있음
            if(current == min) {
                current += arr[end++];
            }
            else {
                current -= arr[start++];
            }
        }

        System.out.println(answer);
    }
}
