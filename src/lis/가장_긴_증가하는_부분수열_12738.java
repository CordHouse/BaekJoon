package lis;

/**
 * 23. 08. 07 15시 10분 시작
 * 23. 08. 07 15시 50분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 주어지는 수열에 대해 가장 길게 증가하는 부분 수열을 찾는다.
 *
 * 제한사항
 * 1 <= N <= 1_000_000
 * -1_000_000_000 <= Ai <= 1_000_000_000
 *
 * 풀이
 * 이번 문제는 음수까지 수열이 나올 수 있는 것을 고려해 음수가 나온 경우 양수로 만들어주고,
 * Collections 클래스에 binarySearch 함수를 활용해 문제를 풀어보았다.
 * Collections 클래스에 이진탐색을 위한 함수가 있다는 것을 처음 알게 되었고, 시도해 보게 되었다.
 * 해당 코드는 타인의 코드를 사용한 것으로, 아직 이해를 잘 하지 못해 Collections 클래스에 이진탐색 함수를 공부해볼 필요성
 * 을 느끼게 되었다.
 */

public class 가장_긴_증가하는_부분수열_12738 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> bs = new ArrayList<>();
        // Step 1. 입력 값 삽입
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken()) + 1000000000;
            // 리스트가 비었거나, 현재 들어가 있는 마지막 값보다큰 경우 추가
            if (bs.isEmpty() || bs.get(bs.size()-1) < cur) {
                bs.add(cur);
                continue;
            }
            // 만약 위 조건문에 해당하지 않는다면, 해당 숫자가 있을 위치를 찾아 값을 변경해준다.
            int idx = Collections.binarySearch(bs, cur);
            int targetIdx = idx>=0?idx:-idx-1;
            bs.set(targetIdx, cur);
        }
        System.out.println(bs.size());
    }
}
