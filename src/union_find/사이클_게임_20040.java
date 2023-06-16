package union_find;

/***
 * 23. 06. 16 13시 43분 시작
 * 23. 06. 16 13시 59분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 주어진 조건
 * 1. 0부터 n-1까지 고유한 번호가 부여되는 평면상의 점 n개가 있다.
 * 2. 매 차례마다 두 점을 이어 선분을 긋는다.
 * 3. 사이클이 완성되면 종료하고, 처음으로 만들어진 차례의 번호를 출력한다.
 * 4. 모든 선분은 한번씩만 지나서 출발점으로 돌아올 수 있다.
 *
 * 제한사항
 * 3 <= N <= 500_000
 * 3 <= m <= 1_000_000
 * i번째 차례에 해당 플레이어가 선택한 두점 (1 <= i <= m)
 *
 * 풀이
 * 해당 문제는 그래프에 대한 연결처럼 보이지만, 유니온 파인드로 접근해서 푸는 문제이다.
 * 문제에 조건에서 다른 분기점으로 선분을 긋는다는 말이 있는데, 이 말은 부모와 자식관계로 이어주면 된다는 뜻이다.
 * 따라서 부모와 자식관계를 만들어 부모 값으로 갱신해주고, 사이클이 생기는 순간은 첫 비교시 부모가 같다면 사이클이 형성된
 * 것이라고 볼 수 있다.
 *
 * 주의할 사항은 사이클이 이루어지지 않는 경우 0으로 표기해야한다.
 * */

public class 사이클_게임_20040 {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        // Step 1. 부모 배열 선언 및 초기화
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Step 2. 입력 값을 받고 유니온 파인드 진행
        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int num1 = Integer.parseInt(line[0]);
            int num2 = Integer.parseInt(line[1]);
            if(find(num1) == find(num2)) { // 먼저 부모가 같은지 판단
                System.out.println(i+1);
                return;
            }
            union(num1, num2); // 부모가 다르다면 부모를 선분을 그어주며 부모를 갱신
        }

        System.out.println(0);
    }

    public static void union(int num1, int num2) {
        int o1 = find(num1);
        int o2 = find(num2);

        if(parent[o1] < parent[o2]) {
            parent[o2] = o1;
        }
        else {
            parent[o1] = o2;
        }
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }
}
