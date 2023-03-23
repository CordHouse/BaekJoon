import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 배 {
    private static int index = 0; // while 문에서 사용할 box의 위치
    private static int craneIndex = 0; // while 문에서 사용할 crane의 위치

    private static Integer[] nCrane; // 크레인의 무게를 담을 배열
    private static Integer[] nBox; // 움길 박스의 무게를 담을 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 1;
        int craneMax = 0, boxMax = 0; // 이외 상황 즉 크레인 보다 박스가 무거운 경우 다 못 옮기기 때문에 -1 출력

        // 크레인에 대한 무게를 배열에 담고, 가장 큰 무게를 가져온다.
        int nCycle = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        nCrane = new Integer[nCycle];
        craneMax = setArr(st, nCycle, nCrane);

        // 박스에 대한 무게를 배열에 담고 가장 큰 무게를 가져온다.
        int boxCycle = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        nBox = new Integer[boxCycle];
        boxMax = setArr(st, boxCycle, nBox);

        // 아래 문장을 실행하기 이전에 가장 큰 값을 기준으로 박스가 크레인보다 무거운 경우를 살펴본다.
        if(craneMax < boxMax) {
            System.out.println(-1);
            return;
        }

        // 무게가 큰 순서대로 정렬하고 싶어서 Collections에서 지원하는 reverseOrder() 사용
        List<Integer> nCraneList = Arrays.asList(nCrane);
        List<Integer> nBoxList = Arrays.asList(nBox);
        boolean[] nBoxCheck = new boolean[boxCycle];
        nCraneList.sort(Collections.reverseOrder());
        nBoxList.sort(Collections.reverseOrder());

        int cycle = 0; // while을 탈출하는 조건
        while(cycle < nBoxList.size()) {
            // 박스를 한바퀴 다 돌았거나, 크레인을 한바퀴 다 돌았다면 초기화와 동시에 answer 증가
            if(craneIndex == nCycle || index == boxCycle) {
                index = 0;
                craneIndex = 0;
                answer++;
            }
            // 움긴 박스를 체크하기 위해 인덱스에서 빼는 방법이 아닌 체크하는 방식으로 진행
            if(!nBoxCheck[index] && nBoxList.get(index) <= nCraneList.get(craneIndex)) {
                nBoxCheck[index] = true;
                craneIndex++;
                cycle++;
                continue;
            }
            index++;
        }

        System.out.println(answer);
    }

    // 배열을 만듬과 동시에 큰 숫자를 찾는 함수
    public static int setArr(StringTokenizer st, int cycle, Integer[] arr) {
        int max = 0;
        for(int i = 0; i < cycle; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        return max;
    }
}
