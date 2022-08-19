import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 회문 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());

        // Step 2. 반복
        for(int i = 0; i < count; i++){
            String in = br.readLine();
            // Step 2-1. 팰린드롬 문자열인 경우
            if(check(in, 0, in.length()-1))
                System.out.println("0");
            // Step 2-2. 유사 팰린드롬 문자열인 경우
            else if(reCheck(in, 0, in.length()-1))
                System.out.println("1");
            // Step 2-3. 팰린드롬이 아닌경우
            else
                System.out.println("2");
        }
    }
    public static boolean check(String in, int left, int right){
        // Step 3. 반복 ( 왼쪽과 오른쪽이 같아지면 문자열 모든 곳을 순회한 경우 )
        while(left<=right){
            // 대칭이 이루어 지지 않는 경우는 false 리턴
            if(in.charAt(left) != in.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public static boolean reCheck(String in, int left, int right){
        // Step 4. 반복
        while(left<=right){
            // 대칭이 이루어 지지 않는 경우 ( 유사 팰린드롬을 찾기 )
            if(in.charAt(left) != in.charAt(right)){
                // 왼쪽에서 하나 빠지던지 오른쪽에서 하나 빠지던지해서 싸이클이 이루어 져야함.
                // 따라서 왼쪽 싸이클에서 하나 빼고 팰린드롬이 형성되는 경우
                // 오른쪽 싸이클에서 하나 빼고 팰린드롬이 형성되는 경우
                // 즉, 두 가지 방법 모두에서 찾지 못한 경우를 제외하면 유사 팰린드롬이라 할 수 있다.
                boolean leftCycle = check(in, left+1, right);
                boolean rightCycle = check(in, left, right-1);
                if(!leftCycle && !rightCycle){
                    return false;
                }
                else return true;
            }
            left++;
            right--;
        }
        return true;
    }
}
