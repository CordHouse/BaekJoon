package twopoint;

/**
 * 23. 07. 30 18시 51분 시작
 * 23. 07. 30 19시 46분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 대회에 출전해야하는 팀원은 총 3명이다.
 * 2. 코딩 실력은 -10_000 ~ 10_000 사이의 정수로 표현된다.
 * 3. 세 팀원의 코딩 실력의 합을 0으로 만든 총 가지수를 출력한다.
 *
 * 제한사항
 * 1 <= N <= 10_000
 * -10_000 <= Ai <= 10_000
 *
 * 풀이
 * 이번 문제의 시간초과를 해결하기 위해서는 이분탐색을 이용해야만 하며, 하나의 값을 고정으로
 * 나머지 두 값을 투 포인터로 계산해야 빠르게 값을 찾아낼 수 있다.
 * 특히 count 함수의 경우 숫자 계산을 통해 빠르게 몇개의 경우의 수가 만들어지는지 판단할 수 있다.
 * while 문안에 while 문에서 몇개의 조건이 겹치는지 확인하면서 넘겨주는 것 또한 시간을 줄일 수 있는 방법이 된다.
 * 하지만 이 모든 것들은 정렬이 되었다는 가정이 필요하기 때문에 정렬을 우선적으로 진행해주어야 한다.
 * */

public class 합이_0_3151 {
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long answer = 0;

        // Step 1. 변수 입력 및 정렬
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 여기서 정렬을 해야하는 이유는 음수와 양수 구간으로 나누고 하나를 고정한 뒤 해당 구간에서 맨 앞과 맨 뒤의
        // 값을 더해 0을 찾기 위해서이다.
        // 시간복잡도가 줄어듬
        Arrays.sort(arr);

        // Step 2. 값 하나를 고정시킨 후 나머지 두 값을 투포인터로 찾기
        // 이분탐색의 활용
        for(int i = 0; i < n; i++) {
            // 정렬해버리기 때문에 고정되는 값이 0이 넘어가면 이후 값은 다 양수이기 때문에 0으로 만들 수가 없다.
            if(arr[i] > 0) {
                break;
            }
            int s = i+1; // 초기 값
            int e = n-1; // 끝 값

            while(s < e) {
                int left = 1;
                int right = 1;
                int sum = arr[i] + arr[s] + arr[e];

                if(sum == 0) {
                    if(arr[s] == arr[e]) {
                        answer += count(e - s + 1);
                        break;
                    }

                    // s 값과 다음 s의 값이 같을때 중복되는 개수를 세어 계산한다.
                    while(s + 1 < e && arr[s] == arr[s+1]) {
                        left++;
                        s++;
                    }

                    // e 값과 다음 e의 값이 같을때 중복되는 개수를 세어 계산한다.
                    while(s < e - 1 && arr[e] == arr[e-1]) {
                        right++;
                        e--;
                    }

                    answer += ((long) left * right);
                }
                // 3명의 점수를 더해 sum이 0보다 큰 경우 최대 값을 줄여준다.
                if(sum > 0) {
                    e--;
                }
                else {
                    s++;
                }
            }
        }
        System.out.println(answer);
    }

    // 같은 구간이 발생한 곳을 그룹으로 묶어 구별할 수 있다.
    // 예로 0 1 1 1 0 0 ( 1이 연속 3번 겹친다 )
    public static int count(int n) {
        return n * (n-1) / 2;
    }
}
