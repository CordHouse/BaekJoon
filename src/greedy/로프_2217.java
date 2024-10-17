package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 로프_2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int max = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());
            pq.add(input);
            max = Math.max(max, input);
        }

        while(!pq.isEmpty()) {
            int out = pq.poll();
            max = Math.max(max, out * (pq.size() + 1));
        }
        System.out.println(max);
    }
}
