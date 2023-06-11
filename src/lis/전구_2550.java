package lis;

/***
 * 23. 06. 11 13시 39분 시작
 * 23. 06. 11 17시 54분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 주어진 숫자들이 lis(최장 증가 부분 수열)을 이룰 때 서로 겹치지 않게 전구를 켤 수 있다.
 *
 * 제한사항
 * 1 <= N <= 10_000
 *
 * 풀이
 * LIS(최장 증가 부분 수열)로 해결해야 하는 문제임을 빠르게 판단 해야한다.
 * 전구가 연결된 선들이 겹치지 않는 위치에 있기 위해선 오름차순으로 정렬된 최장 증가 부분 수열이 있어야 하기 때문이다.
 * 최장 증가 부분 수열의 문제를 풀 수 있는 방법이 두 가지가 존재한다.
 * 1. DP를 활용한 O(n^2)
 * 2. 이진탐색을 활용한 O(nlogn)
 * 두 가지 방법 중에 2번의 방법을 택해야 한다.
 * 이유는 1번의 경우 DP를 활용하여 담기는 갯수에만 치우치게 되는데 그럼 또 배열을 하나 만들어서 해당 하는 숫자를 담아줘야한다.
 * 즉, 시간 복잡도도 더 오래 걸릴 뿐만아니라 한번 더 어떤 숫자가 들어가는지 담아주는 과정을 거쳐야한다.
 * 2번의 경우 배열을 하나 더 만들어 하나의 최장 증가 부분 수열 값을 저장하게 되는데,
 * 문제에서 요구하는 가장 많이 전구가 켜지는 스위치의 개수와 번호를 출력해 줘야하기 때문이다.
 * 따라서 1번보다 2번이 적합한 풀이라고 볼 수 있다.
 * */
public class 전구_2550 {
    private static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n];

        String[] button = br.readLine().split(" "); // 스위치 입력
        String[] light = br.readLine().split(" "); // 전구 입력

        // Step 1. 스위치와 전구에 대해 연관관계를 맺어준다.
        Link[] links = new Link[n]; // 스위치와 전구의 연결 상태를 담는다.
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(button[i].equals(light[j])) { // 스위치와 전구가 같을때
                    // j : 전구 index, Integer.parseInt(button[i]) : 스위치 value
                    links[i] = new Link(j, Integer.parseInt(button[i]));
                    break;
                }
            }
        }

        // Step 2. 맺은 연관관계를 통해 최장 증가 부분 수열을 구해준다.
        int next = 0; // dp에서 사용할 index
        dp[next] = links[next].targetIndex; // dp는 전구 index를 최장 증가 부분 수열로 담을 배열.
        Link[] tracking = new Link[n]; // tracking은 해당 레벨 별로 담을 수 있는 value가 담긴다. (역추적)
        tracking[next] = new Link(next, links[next].switchNumber);
        for(int i = 1; i < n; i++) {
            // dp 현재 위치 보다 작은 index 값일 경우 -> 이진탐색 하지않고 바로 추가
            if(dp[next] < links[i].targetIndex) {
                dp[++next] = links[i].targetIndex;
                tracking[i] = new Link(next, links[i].switchNumber);
            }
            else {
                // dp 현재 위치보다 크거나 같은 경우 -> 이진탐색 시작
                int index = binarySearch(links[i].targetIndex, 0, next);
                dp[index] = links[i].targetIndex; // 변경할 위치 인덱스 자리에 값을 넣어 갱신
                tracking[i] = new Link(index, links[i].switchNumber);
            }
        }

        // Step 3. 최장 증가 부분 수열을 구한 후 겹치지 않는 구간을 출력한다.
        List<Integer> answer = new ArrayList<>();
        for(int i = tracking.length-1; i >= 0; i--) {
            if(next == tracking[i].targetIndex) {
                answer.add(tracking[i].switchNumber);
                next--;
            }
        }
        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        for(int v : answer) {
            sb.append(v).append(" ");
        }
        System.out.println(answer.size());
        System.out.println(sb);
    }

    public static int binarySearch(int num, int left, int right) {
        while(left <= right) {
            int mid = (left + right) / 2;

            if(num <= dp[mid]) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static class Link{
        int targetIndex;
        int switchNumber;
        public Link(int targetIndex, int switchNumber) {
            this.targetIndex = targetIndex;
            this.switchNumber = switchNumber;
        }
    }
}
