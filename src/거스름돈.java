import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 거스름돈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int coin = 1000 - Integer.parseInt(br.readLine());
        int min_count = 0;
        while(coin - 500 >= 0){
            coin -= 500;
            min_count += 1;
        }
        while(coin - 100 >= 0){
            coin -= 100;
            min_count += 1;
        }
        while(coin - 50 >= 0){
            coin -= 50;
            min_count += 1;
        }
        while(coin - 10 >= 0){
            coin -= 10;
            min_count += 1;
        }
        while(coin - 5 >= 0){
            coin -= 5;
            min_count += 1;
        }
        while(coin - 1 >= 0){
            coin -= 1;
            min_count += 1;
        }
        System.out.println(min_count);
    }
}
