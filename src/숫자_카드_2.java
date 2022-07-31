import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 숫자_카드_2 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 입력 받기
        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // map을 사용해 중복갯수 카운팅
        Map<Integer, Integer> map = new HashMap<>();

        int createCount = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Step 2. 반복 -> map에 key : value 저장
        for(int create = 0; create < createCount; create++){
            int createKey = Integer.parseInt(st.nextToken());
            if(!map.containsKey(createKey)){
                map.put(createKey, 1);
            }
            else{
                map.put(createKey, map.get(createKey)+1);
            }
        }

        int SearchCount = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        // Step 3. 반복 -> answer에 값 넣기
        for(int search = 0; search < SearchCount; search++){
            int searchKey = Integer.parseInt(st.nextToken());
            if(map.containsKey(searchKey)){
                answer.append(map.get((searchKey)) + " ");
            }
            else{
                answer.append("0 ");
            }
        }

        System.out.println(answer);
    }
}
