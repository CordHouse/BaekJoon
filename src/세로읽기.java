import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 세로읽기 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[][] str = new String[5][15];
        int max = 0;
        
        // Step 2. 반복 -> 최대 길이 max 선택, 입력받는 값을 하나의 문자열로 만들어 배열에 저장
        for(int i = 0; i < 5; i++){
            String request = br.readLine();
            max = Math.max(max, request.length());
            for(int j = 0; j < request.length(); j++){
                str[i][j] = String.valueOf(request.charAt(j));
            }
        }

        // Step 3. 반복 -> 배열에서 값을 꺼내 StringBuilder 시키기
        for(int i = 0; i < max; i++){
            for(int j = 0; j < 5; j++){
                if(str[j][i] != null && !str[j][i].equals(" "))
                    sb.append(str[j][i]);
            }
        }

        System.out.println(sb);
    }
}
