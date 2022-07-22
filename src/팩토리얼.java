import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 팩토리얼 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();
        System.out.println(factory(Integer.parseInt(st)));
    }

    public static int factory(int num){
        if(num <= 1)
            return 1;
        else{
            return num * factory(num-1);
        }
    }
}
