package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 기타_레슨_2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        int right = Integer.MAX_VALUE;

        int result = Integer.MAX_VALUE;
        while(left <= right) {
            int answer = 0;
            int mid = (left + right) / 2;
            int sum = 0;
            int count = 0;

            for(int i = 0; i < n; i++) {
                sum += arr[i];
                if(sum > mid) {
                    answer = Math.max(answer, sum - arr[i]);
                    sum = arr[i];
                    count++;
                }
                else {
                    answer = Math.max(answer, sum);
                }
            }

            if(count < m) {
                result = Math.min(result, answer);
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        System.out.println(result);
    }
}
