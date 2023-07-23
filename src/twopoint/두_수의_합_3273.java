package twopoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 두_수의_합_3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(num);
        int goal = Integer.parseInt(br.readLine());

        int left = 0;
        int right = n-1;
        int count = 0;
        while(left < right) {
            int sum = num[left] + num[right];
            if(sum <= goal) {
                left++;
            }
            else {
                right--;
            }
            if(sum == goal) {
                count++;
            }
        }
        System.out.println(count);
    }
}
