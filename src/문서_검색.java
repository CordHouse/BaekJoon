import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문서_검색 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String check = br.readLine();
        String[] tmp;
        int count = 0;
        if(input.contains(check)){
            input = input.replace(check, "0");
        }
        tmp = input.split("");
        for(int i = 0; i< tmp.length; i++){
            if(tmp[i].equals("0"))
                count++;
        }
        System.out.println(count);
    }
}
