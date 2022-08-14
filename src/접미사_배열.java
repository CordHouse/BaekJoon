import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 접미사_배열 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        List<String> list = new ArrayList<>();

        // Step 2. 반복
        for(int i = 0; i<s.length(); i++){
            list.add(s.substring(i));
        }

        // Step 3. 정렬
        Collections.sort(list);

        // Step 4. 출력
        for(String key : list)
            System.out.println(key);
    }
}
