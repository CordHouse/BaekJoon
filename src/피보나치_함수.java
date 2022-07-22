import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 피보나치_함수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        for(int i = 0; i<count; i++){
            int num = Integer.parseInt(br.readLine());
            int[] fibonacci0 = new int[2+num];
            int[] fibonacci1 = new int[2+num];
            fibonacci0[0] = 1;
            fibonacci0[1] = 0;
            fibonacci1[0] = 0;
            fibonacci1[1] = 1;
            for(int j = 2; j<=num; j++){
                fibonacci0[j] = fibonacci0[j-1] + fibonacci0[j-2];
                fibonacci1[j] = fibonacci1[j-1] + fibonacci1[j-2];
            }
            System.out.println(fibonacci0[num] + " " + fibonacci1[num]);
        }
    }
}
