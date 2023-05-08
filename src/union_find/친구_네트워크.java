package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class 친구_네트워크 {
    private static int[] parent, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cycle = Integer.parseInt(br.readLine());
        for(int i = 0; i < cycle; i++) {
            HashMap<String, Integer> friend = new HashMap<>();
            int n = Integer.parseInt(br.readLine());

            parent = new int[n*2]; // n*2인 이유는 주어지는 사람의 수가 2*n명이기 때문이다.
            count = new int[n*2]; // 할당 된 인덱스별 친구 수
            Arrays.fill(count, 1); // 초기화는 최소 1 -> 친구가 최소 한명은 있기 때문이다.
            for(int j = 0; j < n * 2; j++) {
                parent[j] = j;
            }

            int index = 0; // 처음 등록되는 사람마다 인덱스 지정
            for(int j = 0; j < n; j++) {
                String[] line = br.readLine().split(" ");
                String f1 = line[0];
                String f2 = line[1];
                if(!friend.containsKey(f1)) {
                    friend.put(f1, index++);
                }
                if(!friend.containsKey(f2)) {
                    friend.put(f2, index++);
                }
                // 지정된 인덱스 번호를 가지고 union find 실행
                System.out.println(union(friend.get(f1), friend.get(f2)));

            }
        }
    }

    public static int union(int f1, int f2) {
        int n1 = find(f1);
        int n2 = find(f2);

        if(n1 != n2) {
            if(n1 < n2) {
                parent[n2] = n1; // 부모를 n1으로 지정
                count[n1] += count[n2]; // 부모 값에 현재 값을 더해서 저장
                return count[n1];
            } else {
                parent[n1] = n2; // 부모를 n2로 지정
                count[n2] += count[n1]; // 부모 값에 현재 값을 더해서 저장
                return count[n2];
            }
        }
        return count[n1]; // 두 값이 같을때 n1을 리턴
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }

        return parent[num] = find(parent[num]);
    }
}
