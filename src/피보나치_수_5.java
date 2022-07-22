import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 피보나치_수_5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();
        System.out.println(Fibonacci(Integer.parseInt(st)));
    }

    public static int Fibonacci(int num){
        if(num == 1){
            return 1;
        }
        else if(num == 0){
            return 0;
        }
        else{
            return Fibonacci(num-1) + Fibonacci(num-2);
        }
    }

}
