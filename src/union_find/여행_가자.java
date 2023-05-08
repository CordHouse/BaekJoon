package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 여행_가자 {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int city = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        String answer = "YES";

        // 담을 배열 초기화
        parent = new int[city+1];
        for(int i = 1; i <= city; i++) {
            parent[i] = i;
        }

        // 주어진 그림을 잘 이해하기
        // i = 1번부터 시작, j = 1번부터 시작해야 매칭된 정보를 알 수 있기 때문에
        // union 에 값을 +1로 변경하여 넣어주어야 한다.
        for(int i = 0; i < city; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < line.length; j++) {
                if(Integer.parseInt(line[j]) == 1) {
                    union(i+1, j+1);
                }
            }
        }

        // 3개의 정보가 다 같은 값을 가르키는지?
        String[] search = br.readLine().split(" ");
        int start = Integer.parseInt(search[0]);
        for(int i = 1; i < n; i++) {
            if(find(start) != find(Integer.parseInt(search[i]))) {
                answer = "NO";
            }
        }
        System.out.println(answer);
    }

    public static void union(int i, int j) {
        int n1 = find(i);
        int n2 = find(j);

        if(n1 > n2) {
            parent[n1] = n2;
        }
        else if(n1 < n2) {
            parent[n2] = n1;
        }
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }

        return parent[num] = find(parent[num]);
    }
}
