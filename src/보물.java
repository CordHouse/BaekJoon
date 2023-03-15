import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 보물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cycle = Integer.parseInt(br.readLine());
        int[] a = new int[cycle];
        int[] b = new int[cycle];
        StringTokenizer stA = new StringTokenizer(br.readLine(), " ");
        StringTokenizer stB = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < cycle; i++) {
            a[i] = Integer.parseInt(stA.nextToken());
            b[i] = Integer.parseInt(stB.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int answer = 0;
        for(int i = 0; i < cycle; i++) {
            answer += a[i] * b[cycle-i-1];
        }
        System.out.println(answer);
    }
}
