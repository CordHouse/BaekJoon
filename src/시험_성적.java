import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 시험_성적 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());

        if(total >= 90 && total <= 100){
            System.out.println("A");
        }
        else if(total >= 80 && total <= 89){
            System.out.println("B");
        }
        else if(total >= 70 && total <= 79){
            System.out.println("C");
        }
        else if(total >= 60 && total <= 69){
            System.out.println("D");
        }
        else
            System.out.println("F");
    }
}
