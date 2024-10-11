package twopoint;

/***
 * 23. 06. 24 15시 40분 시작
 * 23. 06. 24 16시 40분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 벨트의 임의의 한 위치부터 k개의 접시를 "연속"해서 먹을 경우 할인이 들어간다.
 * 2. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발급한다.
 * 3. 쿠폰을 사용할 경우 해당 번호의 초밥을 무료로 얻을 수 있다.
 * 4. 가장 많은 가짓 수를 먹는 경우의 개수를 구하여라
 *
 * 제한사항
 * 2 <= N <= 3_000_000
 * 2 <= d <= 3_000
 * 2 <= k <= 3_000 (k <= N)
 * 1 <= c <= d
 *
 * 풀이
 * 슬라이딩 윈도우 알고리즘을 이용하여 해당 문제를 풀어볼 수 있다.
 * 우선 초밥 번호마다의 개수를 담을 변수가 필요하다. 초밥의 번호는 1번부터 d번까지 이기 때문에 d+1의 크기로 배열을 만든다.
 * 그 다음 슬라이딩 윈도우를 활용하는데, 흐름은 다음과 같다.
 * 1. 0 ~ k 까지의 초밥의 번호를 배열에 담아주고, 스시 번호 또한 중복되지 않도록 담아준다.
 * 2. 그럼 k까지의 슬라이딩 된 윈도우가 형성된 것이기 때문에 이것을 기준으로 앞에 초밥 숫자를 제거, k+1번째의 초밥 숫자를 추가한다.
 * 3. 그러면서 변화하는 가짓 수의 최대값을 리턴해준다.
 */

public class 회전_초밥_15961 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());  // 회전 초밥 벨트에 놓인 접시의 수
        int d = Integer.parseInt(st.nextToken());  // 초밥의 가짓수
        int k = Integer.parseInt(st.nextToken());  // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken());  // 쿠폰 번호

        // Step 1. 벨트에 입력되는 번호들을 담아준다.
        int[] belt = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            belt[i] = Integer.parseInt(st.nextToken());
        }

        // Step 2. 초밥의 종류의 개수를 담을 배열을 선언한다.
        int[] count = new int[d + 1];  // 초밥 종류별 개수를 저장하는 배열
        int maxCount = 0;  // 손님이 먹을 수 있는 초밥 가짓수의 최댓값

        Set<Integer> sushiSet = new HashSet<>(); // 중복 방지를 위해 Set 선언

        // Step 3. 초기 윈도우 설정한다.
        for (int i = 0; i < k; i++) {
            count[belt[i]]++;
            sushiSet.add(belt[i]);
        }

        // 재채점 해결사항
        // 초기 윈도우를 설정하고 처음 maxCount 값을 설정할 때 티켓이 있는 경우를 고려하지 않음
        int currentCount = sushiSet.size();
        if(count[c] == 0) {
            currentCount++;
        }
        maxCount = currentCount;

        // Step 4. 슬라이딩 윈도우를 사용하여 초밥 가짓수 계산한다.
        for (int i = 1; i < N; i++) {
            count[belt[i - 1]]--; // 맨 앞에 있는 초밥의 개수를 하나 뺀다.
            if (count[belt[i - 1]] == 0) { // 만약 뺀 초밥의 수가 0 이 되면 해당 초밥은 먹지 않은 것이기 때문에 빼준다.
                sushiSet.remove(belt[i - 1]);
            }

            count[belt[(i + k - 1) % N]]++; // k의 길이 만큼 윈도우의 크기를 가지기 때문에 한칸 슬라이딩한 위치의 초밥의 개수를 추가한다.
            sushiSet.add(belt[(i + k - 1) % N]); // 스시 셋에도 초밥의 번호를 담아준다.

            currentCount = sushiSet.size(); // 현재 최대 가짓수는 스시 셋에 담긴 크기와 같다.
            if (count[c] == 0) { // 만약 쿠폰 번호의 개수가 0이 아니라면 해당 번호가 있는 것이기 때문에 가짓 수를 1만큼 증가시켜준다.
                currentCount++;
            }

            maxCount = Math.max(maxCount, currentCount); // 최대 값을 계속 갱신한다.
        }

        System.out.println(maxCount);
    }
}
