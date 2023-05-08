package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 집합의_표현 {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int cycle = Integer.parseInt(input[1]);
        parent = new int[n+1];

        // 부모가 될 배열은 0 ~ n 까지의 숫자 값으로 대입한다.
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < cycle; i++) {
            // 처음 응답 값을 YES 로 설정
            String answer = "YES";
            String[] line = br.readLine().split(" ");
            int check = Integer.parseInt(line[0]);
            int n1 = Integer.parseInt(line[1]);
            int n2 = Integer.parseInt(line[2]);

            // check 0 이라면 두 값을 하나의 집합으로 만든다.
            if(check == 0) {
                union(n1, n2);
                continue;
            }
            // check 1 이라면 두 집합을 비교하여 같으면 yes 아니면 no를 출력한다.
            if(find(n1) != find(n2)) {
                answer = "NO";
            }
            System.out.println(answer);
        }
    }

    public static void union(int n1, int n2) {
        int first = find(n1);
        int second = find(n2);

        // 작은 숫자의 부모 값으로 배열을 정하기 위하여 해당 조건문으로 설정
        if(first < second) {
            parent[second] = first;
        }
        else if(first > second) {
            parent[first] = second;
        }
    }

    public static int find(int num) {
        // 배열안의 값과 찾고자 하는 값이 같다면 그 값을 리턴
        // parent[1] = 1 인 경우
        if(parent[num] == num) {
            return num;
        }
        // 값을 찾으면서 갱신 -> 시간복잡도를 줄일 수 있음.
        return parent[num] = find(parent[num]);
    }
}
