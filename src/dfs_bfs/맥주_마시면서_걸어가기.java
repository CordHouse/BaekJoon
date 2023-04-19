package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 맥주_마시면서_걸어가기 {
    private static int x, y;
    private static int endX, endY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cycle = Integer.parseInt(br.readLine());

        for(int i = 0; i < cycle; i++) {
            int marketCount = Integer.parseInt(br.readLine());
            List<int[]> list = new ArrayList<>();

            String status = "happy";

            for(int j = 0; j < marketCount+2; j++) {
                String[] input = br.readLine().split(" ");
                if(j == 0) { // 시작 지점을 입력 받음
                    x = Integer.parseInt(input[0]);
                    y = Integer.parseInt(input[1]);
                }
                else if(j == marketCount+1) { // 마지막 도착 지점을 입력 받음
                    endX = Integer.parseInt(input[0]);
                    endY = Integer.parseInt(input[1]);
                }
                else { // 이외 남은 마트 지점을 입력 받음
                    list.add(new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])});
                }
            }
            if(!bfs(list, marketCount)) { // 만약 도착지점에 도달하지 못한 경우 sad 출력
                status = "sad";
            }
            System.out.println(status); // 기본은 happy 로 도착한 경우
        }
    }
    public static boolean bfs(List<int[]> list, int marketCount) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] check = new boolean[marketCount]; // 들른 마트는 체크해야한다. -> 현재 위치에서 갈 수 있는 마트들은 모조리 체크해야하기 때문
        queue.add(new int[]{x, y});
        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            if(Math.abs(endX - point[0]) + Math.abs(endY - point[1]) <= 1000) {
                return true;
            }

            for(int i = 0; i < marketCount; i++) {
                if(!check[i]) {
                    if(Math.abs(point[0] - list.get(i)[0]) + Math.abs(point[1] - list.get(i)[1]) <= 1000) {
                        check[i] = true;
                        queue.add(new int[]{list.get(i)[0], list.get(i)[1]});
                    }
                }
            }
        }
        return false; // 해당 지점에 도착하고, 큐가 비어있는 경우 출력
    }
}