import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 빗물_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int h = Integer.parseInt(input[0]);
        int w = Integer.parseInt(input[1]);

        int[] block = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int sum = 0;
        for(int i = 1; i < w - 1; i++) { // 현재 내 위치
            int left = block[0];
            int right = block[block.length-1];

            for(int j = 0; j < i; j++) {
                left = Math.max(left, block[j]);
            }

            for(int j = i+1; j < w; j++) {
                right = Math.max(right, block[j]);
            }

            if(Math.min(left, right) - block[i] > 0) {
                sum += Math.min(left, right) - block[i];
            }
        }
        System.out.println(sum);
    }
}
