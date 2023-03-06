import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 회의실_배정 {
    private static int[][] table;
    private static int max = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        table = new int[count][2];

        for(int i = 0; i < count; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            table[i][0] = Integer.parseInt(st.nextToken());
            table[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(table, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });

        int end = table[0][1];
        for(int i = 1; i < count; i++) {
            if(end <= table[i][0]) {
                end = table[i][1];
                max++;
            }
        }
        System.out.println(max);
    }
}

class Schedule {
    int start;
    int end;

    public Schedule(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
