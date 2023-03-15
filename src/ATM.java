import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ATM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cycle = Integer.parseInt(br.readLine());
        int[] wait = new int[cycle+1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= cycle; i++) {
            wait[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(wait);

        int min = 0;
        for(int i = 1; i < wait.length; i++) {
            wait[i] += wait[i-1];
            min += wait[i];
        }
        System.out.println(min);
    }
}
