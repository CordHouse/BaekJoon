import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 재귀함수가_뭔가요 {
    public static void main(String[] args) throws IOException {
        // Step 1. 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine()); // 입력을 count라는 정수형 변수에 저장

        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다."); // 맨 처음 출력문장
        // Step 2. 재귀함수 시작
        bot(count,0); // 파라미터는 입력 받은 반복횟수와 언더바 모양 반복 초기값
    }

    static void bot(int count, int under_bar){
        // Step 3. 재귀함수 흐름 보기
        // 맨처음 출력 값
        System.out.println("____".repeat(under_bar)+"\"재귀함수가 뭔가요?\"");
        // count는 아래 else문을 실행하며 계속 줄어든 파라미터가 받아 짐
        if(count == 0){
            // 즉 count == 0이되는 순간 해당 문장 실행
            System.out.println("____".repeat(under_bar)+"\"재귀함수는 자기 자신을 호출하는 함수라네\"");
        }
        else{
            // 해당 문장을 실행
            System.out.println("____".repeat(under_bar)+"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n" +
                    "____".repeat(under_bar)+"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n" +
                    "____".repeat(under_bar)+"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
            // 재귀 시작 : 자기 자신을 다시 한 번 호출
            bot(count-1, under_bar+1); // 호출 시 파라미터 변경
        }
        // 재귀호출이 일어나면 그 밑에 문장은 실행하지 않고 재귀먼저 시작 후 밑에 문장을 순차처리
        // 이게 재귀의 개념! -- > 이거 모르겠으면 질문 꼭하세요!
        System.out.println("____".repeat(under_bar)+"라고 답변하였지.");
    }
}
