import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 신입_사원 {
    public static void main(String[] args) throws IOException {
        //Step 1. BufferedReader 클래스로 선언한 br변수로 입력을 받는다.
        //     1-1 입력은 br.readLine()을 통해 한 줄씩 받아온다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //     1-2. StringTokenizer 클래스로 선언한 st변수는 br.readLine()함수를 읽어와 하나씩 받아온다. split과 비슷
        StringTokenizer st;

        //Step 2. 각 테스트 케이스마다 입력되는 서류심사성적과 면접성적을 담기위해 배열을 선언한다.
        //     2-1 1차원 배열로 선언한 이유는 두 점수가 낮은 경우만 해당하기 때문에 중복이 없다는 가정하에 순서를 정렬하여
        //         해당 배열의 Value값을 통해 비교할 예정이기 때문이다.
        int[] num1;
        //     2-2 입력 첫째 줄 2를 읽어와 2번을 반복한다.
        int count = Integer.parseInt(br.readLine());

        //Step 3. 반복한다.
        for(int i = 0; i<count; i++){
            // 3-1 2번째 줄과 8번째줄의 총 개수를 입력받는다.
            int N = Integer.parseInt(br.readLine());
            // 3-2 생성자를 통해 배열을 만들어주는데 배열을 1부터 사용할 예정이기 때문에 +1을 해준다.
            num1 = new int[N+1];
            // 3-3 N만큼 반복하면서 한줄씩 읽어오고 배열에 담아준다. (3~7번째줄, 9~마지막번째줄 해당)
            for(int j = 0; j<N; j++){
                st = new StringTokenizer(br.readLine());
                num1[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
            }
            // 3-4 배열에 담은 후 tmp라는 변수에 첫번째 Value를 집어넣고 선발할 인원을 cnt로 놓은 상태에서 비교한다.
            int tmp = num1[1];
            // 3-5 선발할 수 있는 최대 인원수를 담을 변수 (본인만 있는 경우를 고려하여 초기값은 1)
            int cnt = 1;
            // 3-6 반복을 2부터 하는 이유는 1값을 기준으로 비교하기 때문이며, +1까지 비교하는 이유는 위에서 배열 공간을
            //     +1만큼 만들어줬기 때문이다.
            for(int k = 2; k<N+1; k++){
                // 3-7 num1[1]을 담은 tmp을 통해 적어도 하나만 작은 경우를 비교하여 카운트한다.
                if(tmp > num1[k]){
                    cnt++;
                    tmp = num1[k];
                }
            }
            System.out.println(cnt);
        }
    }
}
