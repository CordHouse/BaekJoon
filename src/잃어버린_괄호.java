import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 잃어버린_괄호 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        // '-'를 기준으로 나눈다.
        List<String> list = new ArrayList<>(List.of(s.split("-")));

        // Step 2. 반복
        for(int i = 0; i < list.size(); i++){
            // 해당 리스트에 '+' 포함한 문자열이 저장된 경우
            if(list.get(i).contains("+")){
                List<String> sum = new ArrayList<>(List.of(list.get(i).split("\\+"))); // '+' 는 메타 데이터 이기 때문에 이렇게 처리해줘야함
                int tmpSum = 0;
                for (String value : sum) {
                    tmpSum += Integer.parseInt(value);
                }
                list.set(i, String.valueOf(tmpSum));
            }
        }

        // Step 3. 출력
        int answer = Integer.parseInt(list.get(0));
        for(int i = 1; i<list.size(); i++){
            answer -= Integer.parseInt(list.get(i));
        }
        System.out.println(answer);
    }
}
