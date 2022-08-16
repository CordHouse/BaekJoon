import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 전화번호_목록 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        for(int i = 0; i<count; i++){
            int N = Integer.parseInt(br.readLine());
            // Step 2. 함수 호출
            System.out.println(checkString(N, br)); // return 값 출력
        }

    }

    public static String checkString(int N, BufferedReader br) throws IOException {
        List<String> list = new ArrayList<>();
        // Step 3. list 초기화
        for(int i = 0; i<N; i++){
            list.add(br.readLine());
        }
        // Step 4. 정렬
        Collections.sort(list);

        // Step 5. 문자 비교
        for(int i = 0; i<list.size()-1; i++){
            if(list.get(i+1).startsWith(list.get(i))) // 정렬 후 앞과 뒤의 값만 비교하면 된다.
                return "NO";
        }
        return "YES";
    }
}
