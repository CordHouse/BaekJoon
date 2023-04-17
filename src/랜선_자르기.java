import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 랜선_자르기 {
    private static long max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int k = Integer.parseInt(input[0]);
        long n = Integer.parseInt(input[1]);
        int[] len = new int[k];
        for(int i = 0; i < k; i++) {
            len[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(len);

        binary(1, len[len.length-1], len, n);
        System.out.println(max);
    }
    public static void binary(long left, long right, int[] len, long n) {
        while(left <= right) {
            long mid = (left + right) / 2;
            int sum = 0; // 총 자른 갯수가 들어갈 변수
            for(int i = 0; i < len.length; i++) {
                sum += len[i] / mid; // 길이를 자르고 각각 나오는 갯수를 더해준다.
            }
            // 길이를 크게 자른경우 sum이 모자르기 때문에 큰 길이를 줄인다.
            if(sum < n) {
                right = mid - 1;
            }
            // 길이를 작거나 딱 맞게 자른경우 왼쪽 크기를 늘린다.
            else {
                max = mid;
                left = mid + 1;
            }
        }
    }
}
