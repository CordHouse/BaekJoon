import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 숫자의_합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        String tmp = br.readLine();
        int[] number = new int[count];
        int sum = 0;

        for(int i = 0; i<count; i++){
            number[i] = tmp.charAt(i)-'0';
            sum += number[i];
        }
        System.out.println(sum);

    }
}
