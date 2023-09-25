package lis;

/**
 * 23. 09. 25 23시 20분 시작
 * 23. 09. 26 00시 12분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 주어진 조건
 * 1. 1 ~ N번까지의 순서의 책을 순서대로 재배열해야한다.
 *
 * 제한사항
 * 1 <= N <= 200_000
 *
 * 풀이
 * 최장 증가 부분수열의 문제로 주어진 제한사항에 따라 일반 배열식으로는 시간초과가 발생한다.
 * 배열을 하나 만들어 배열 값 하나를 가져올 때 마다 가장 큰 값과 비교해주고, 현재 값이 더 크다면 정렬된 배열안에 값을
 * 넣어줘야 하기 때문에 2진 탐색을 이용하여 넣어줄 자리를 찾는다.
 * 단 조건중에 번호는 하나씩 있고 중복되지 않기 때문에 이미 나온 숫자에 대해서는 바꿔줘도 문제가 되지 않는다.
 * 또한, 이진탐색을 이용해야하는 이유는 알고리즘 특성상 nlongn으로 시간초과가 발생하지 않기 때문이다.
 */

public class 책정리_1818 {
    private static int[] num;
    private static int[] lis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // Step 1. lis 공간 잡기
        lis = new int[num.length+1];
        int next = 0;
        int answer = 0;
        lis[0] = num[0];

        // Step 2. lis와 num을 비교하여 lis가 큰 값만 이전 값에서 자리 찾아주기.
        for(int i = 1; i < n; i++) {
            if(lis[next] < num[i]) {
                lis[++next] = num[i];
            }
            else {
                int index = search(0, next, num[i]);
                lis[index] = num[i];
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static int search(int start, int end, int target) {
        while(start < end) {
            int mid = (start + end) / 2;

            if(lis[mid] < target) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return end;
    }
}
