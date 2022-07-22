import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 오와_육의_차이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int min = 0, max = 0;
        String[] num = br.readLine().split(" ");
        min = Integer.parseInt(num[0].replace("6","5")) + Integer.parseInt(num[1].replace("6","5"));
        max = Integer.parseInt(num[0].replace("5","6")) + Integer.parseInt(num[1].replace("5","6"));
        System.out.println(min + " " + max);
    }
}
