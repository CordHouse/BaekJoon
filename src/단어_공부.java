import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 단어_공부 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine().toUpperCase();
        // map -> 문자를 담아주기 위해 선언
        Map<Character, Integer> map = new HashMap<>();

        // Step 2. 반복
        // 입력받은 문자열을 char형태로 key : value로 넣어준다.
        for(int i = 0; i < inputStr.length(); i++){
            char key = inputStr.charAt(i);
            if(!map.containsKey(key))
                map.put(key, 1);
            else
                map.put(key, map.get(key)+1);
        }

        // Step 3. key를 List로 만들어 value를 기준으로 정렬한다.
        List<Character> list = new LinkedList<>(map.keySet());
        Collections.sort(list, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                if(map.get(o1) < map.get(o2))
                    return 1;
                else if(map.get(o1) > map.get(o2))
                    return -1;
                else
                    return 0;
            }
        });

        if(list.size() == 1){
            System.out.println(list.get(0));
        }
        else {
            if (map.get(list.get(0)).equals(map.get(list.get(1)))) {
                System.out.println("?");
            } else if (map.get(list.get(0)) > map.get(list.get(1))) {
                System.out.println(list.get(0));
            }
        }
    }
}
