import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 카드_정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int ansSum = 0;
        for(int i = 0; i < count; i++) {
            queue.add(Integer.parseInt(br.readLine()));
        }

        while(queue.size() > 1) {
            int nextQueue = queue.poll() + queue.poll();
            ansSum += nextQueue;
            queue.add(nextQueue);
        }
        System.out.println(ansSum);
    }
}
