import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 숨바꼭질 {
    private static int[] buffer;
    private static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int start = Integer.parseInt(input[0]);
        int end = Integer.parseInt(input[1]);
        buffer = new int[100001];

        System.out.println(graph(start, end));
    }

    public static int graph(int start, int end) {
        queue.add(start);
        buffer[start] = 1;
        while(!queue.isEmpty()) {
            int point = queue.remove();
            if(point == end) {
                return buffer[point] - 1;
            }

            if(point - 1 >= 0 && buffer[point - 1] == 0) {
                buffer[point-1] = buffer[point]+1;
                queue.add(point-1);
            }
            if(point + 1 <= 100000 && buffer[point + 1] == 0) {
                buffer[point+1] = buffer[point]+1;
                queue.add(point+1);
            }
            if(point * 2 <= 100000 && buffer[point * 2] == 0) {
                buffer[point*2] = buffer[point]+1;
                queue.add(point*2);
            }
        }
        return -1;
    }
}
