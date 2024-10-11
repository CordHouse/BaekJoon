package slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 수열_2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]); // 입력의 크기 n
        int k = Integer.parseInt(input[1]); // 크기 k

        // 값 입력
        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // Step 1. 변수를 담을 배열 선언
        int[] answer = new int[n-k+1];
        int sum = 0;
        for(int i = 0; i < k; i++) {
            sum += num[i];
        }
        answer[0] = sum;

        // Step 2. 슬라이딩 윈도우로 지나가면서 출력 한 값 중 최고 값 찾기
        int value = answer[0];
        for(int i = 1; i <= n; i++) {
            if(i+k <= n) {
                answer[i] = answer[i - 1] - num[i - 1] + num[(i - 1 + k)];
                value = Math.max(value, answer[i]);
            }
        }
        System.out.println(value);
    }
}
