import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 숨바꼭질_2 {
    private static int[] buffer = new int[100001];
    private static Queue<Point> queue = new LinkedList<>();
    private static int time = 0;
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int start = Integer.parseInt(input[0]);
        int end = Integer.parseInt(input[1]);

        bfs(start, end);
        System.out.println(time-1);
        System.out.println(cnt);
    }

    public static void bfs(int start, int end) {
        queue.add(new Point(start, 0));
        buffer[start] = 1;
        while(!queue.isEmpty()) {
            Point queuePoint = queue.poll();
            if(queuePoint.point == end) {
                if(cnt == 0) {
                    time = queuePoint.count + 1;
                }
                if(time == queuePoint.count + 1) {
                    cnt++;
                }
                continue;
            }

            int[] check = {queuePoint.point-1, queuePoint.point+1, queuePoint.point * 2};
            for(int i = 0; i < 3; i++) {
                if(check[i] < 0 || check[i] > 100000) {
                    continue;
                }
                if(buffer[check[i]] == 0 || buffer[check[i]] == queuePoint.count + 1) {
                    buffer[check[i]] = queuePoint.count + 1;
                    queue.add(new Point(check[i], queuePoint.count+1));
                }
            }
        }
    }
}
class Point{
    int point;
    int count;

    public Point(int point, int count) {
        this.point = point;
        this.count = count;
    }
}
