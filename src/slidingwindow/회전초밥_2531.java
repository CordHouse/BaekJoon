package slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class 회전초밥_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]); // 입력의 크기 n
        int d = Integer.parseInt(input[1]); // 초밥의 갯수 d
        int k = Integer.parseInt(input[2]); // 크기 k
        int c = Integer.parseInt(input[3]); // 쿠폰 번호 c

        int[] num = new int[n];
        for(int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        // 서로 다른 초밥의 갯수로 최대 값을 찾아야 하기 때문에 중복 카운트는 피해야한다.
        Set<Integer> eat = new HashSet<>();
        // 어떤 조건에서 eat (서로 다른 초밥을 먹은걸 확인하는 변수) 에서 뺴줄 것인가?
        // 조건은 내가 먹은 윈도우(범위) 안에 없을 때
        int[] windowCount = new int[d+1];
        for(int i = 0; i < k; i++) {
            windowCount[num[i]]++;
            eat.add(num[i]);
        }

        int cur = eat.size();
        if(windowCount[c] == 0) {
            cur++;
        }
        int max = cur;

        // i는 처음꺼 계산 했기 때문에 1번 부터 확인
        for(int i = 1; i < n; i++) {
            windowCount[num[i-1]]--;
            if(windowCount[num[i-1]] == 0) {
                eat.remove(num[i-1]);
            }

            windowCount[num[(i + k - 1) % n]]++;
            eat.add(num[(i + k - 1) % n]);

            cur = eat.size();
            if(windowCount[c] == 0) {
                cur++;
            }
            max = Math.max(max, cur);
        }

        System.out.println(max);
    }
}
