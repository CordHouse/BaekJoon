import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 별_찍기_2 {
    public static void main(String[] args) throws IOException {
        // 공통 변수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        // 풀이 1번
//        for(int i = 0; i<num; i++){
//            System.out.println(" ".repeat(num-(i+1))+"*".repeat(i+1));
//        }

        // 풀이 2번
//        for(int i=0; i<num; i++){
//            String string_save = "";
//            for(int j=0; j<num; j++){
//                if(num-(j+1)>=(i+1))
//                    string_save += " ";
//                else
//                    string_save += "*";
//            }
//            System.out.println(string_save);
//        }

    }
}
