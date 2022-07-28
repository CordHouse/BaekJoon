import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 팰린드롬_만들기 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력
        // HashMap을 통해 해당 단어에 대해 갯수를 카운트
        HashMap<Character, Integer> hashMap = new HashMap<>();
        String token = br.readLine();

        // Step 2. HashMap -> Key : 단어, Value : 갯수를 저장하기 위함
        for(int i = 0; i < token.length(); i++){
            Character tmp = token.charAt(i);
            if(hashMap.containsKey(tmp)){
                hashMap.put(tmp, hashMap.get(tmp)+1);
            }
            else {
                hashMap.put(tmp, 1);
            }
        }

        // 상위 Object클래스로 변수를 선언하고 HashMap에 key를 정렬
        Object[] map = hashMap.keySet().toArray();
        Arrays.sort(map); // 정렬된 키를 밑에 코드에서 사용할 예정

        // Step 3. 팰린드롬인지 확인하기
        if(hole(hashMap)){ // 밑에 함수 참조
            System.out.println("I'm Sorry Hansoo");
        }
        // Value의 갯수가 하나이상 홀수가 아니라면 해당 코드 실행
        // 하나이상의 홀수가 존재한다면 팰린드롬이 될수 없음.
        else{
            // Object 클래스로 배열을 만든 이유는 위에 key를 기준으로 정렬한 클래스가 Object이기 때문이다.
            Object[] answer = new Object[token.length()]; // answer는 팰린드롬을 만드는 배열
            int left = 0, right = 0; // 배열의 시작과 끝을 기준으로 입력해야 하기 때문에 left와 right로 나눔
            for(Object key : map){ // map의 Object값을 하나씩 가져와 key에 대입 -> 해당 값은 정렬한 key 값
                for(int i = hashMap.get(key); i > 0; i--){ // 가져온 Value만큼 반복
                    // 짝수인 경우 왼쪽시작
                    if(i % 2 == 0) {
                        answer[left] = key;
                        left++;
                    }
                    // 홀수인 경우 오른쪽 시작
                    else {
                        // 대신 Value의 홀수가 하나라도 존재한다면 if문을 한번 실행해 배열 가운데에 집어 넣어준다.
                        if(hashMap.get(key) % 2 == 1 && i == 1){
                            answer[token.length()/2] = key;
                        }
                        // 홀수인 경우 오른쪽에 값을 집어 넣는다.
                        else {
                            answer[token.length() - right - 1] = key;
                            right++;
                        }
                    }
//                    System.out.println(Arrays.toString(answer));
                }
            }
            // Step 4. StringBuilder를 통해 문자열 더하기
            StringBuilder output = new StringBuilder();
            for(Object k : answer)
                output.append(k);
            System.out.println(output);
        }

    }
    // 팰린드롬이 될 수 있는 입력인지 판단
    // 즉 Value에 홀수가 1개 이상이면 부적합
    public static boolean hole(HashMap<Character, Integer> hashMap){
        int count = 0;
        for(Character key : hashMap.keySet()){
            if (hashMap.get(key) % 2 == 1){
                count++;
            }
        }
        return count > 1; // 결과는 true or false로 전달됨
    }
}
