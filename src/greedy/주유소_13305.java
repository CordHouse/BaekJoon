package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 주유소_13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] w = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 1; i < cost.length; i++) {
            cost[i] = Math.min(cost[i], cost[i-1]);
        }

        long answer = 0;
        for(int i = 0; i < w.length; i++) {
            answer += (long) w[i] * cost[i];
        }

        System.out.println(answer);
    }
}
