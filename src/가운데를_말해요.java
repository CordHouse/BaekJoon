import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

// 배열로 풀었을 때 시간초과 발생
// 우선순위 큐로 접근하여 힙으로 풀어야 시간초과가 발생하지 않음
public class 가운데를_말해요 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i<N; i++){
            int in = Integer.parseInt(br.readLine());
            if(maxPq.size() == minPq.size()){
                maxPq.add(in);

                System.out.println("들어오는 max"+maxPq);
                if(!minPq.isEmpty() && maxPq.peek() > minPq.peek()){
                    System.out.println("1번"+maxPq+" "+minPq);
                    minPq.add(maxPq.poll());
                    maxPq.add(minPq.poll());
                    System.out.println("2번"+maxPq+" "+minPq);
                }
            }
            else{
                minPq.add(in);

                System.out.println("들어오는 min" + minPq);
                if(maxPq.peek() > minPq.peek()){
                    minPq.add(maxPq.poll());
                    maxPq.add(minPq.poll());
                }
            }
            System.out.println(maxPq.peek());
        }
        br.close();
    }
}
