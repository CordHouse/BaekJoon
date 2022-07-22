import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class 수_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str_N = br.readLine().split(" ");
        HashMap<Integer, Integer> in = new HashMap<>();

        int M = Integer.parseInt(br.readLine());
        String[] str_M = br.readLine().split(" ");
        int[] target = new int[M];

        for (int i = 0; i < N; i++) {
            in.put(Integer.parseInt(str_N[i]), Integer.parseInt(str_N[i]));
        }
        for (int i = 0; i < M; i++) {
            target[i] = Integer.parseInt(str_M[i]);
        }

        for (int i = 0; i < M; i++) {
            if(in.get(target[i]) == null)
                System.out.println("0");
            else if(in.get(target[i]) == target[i])
                System.out.println("1");
        }
    }
}
