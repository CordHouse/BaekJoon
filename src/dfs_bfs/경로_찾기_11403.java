package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 24. 03. 20 05시 40분 시작
 * 24. 03. 20 06시 30분 종료
 * 성공 유무 -> 실패
 */

/**
 * 주어진 조건
 * 1. 가중치가 없는 방향 그래프
 * 2. 모든 정점 (i, j), i -> j로 가는 길이가 양수인 경로 찾기
 *
 * 제한사항
 * 1 <= N <= 100
 *
 * 풀이
 * 플로이드 워샬의 문제이다.
 * 문제에서 모든 정점에 대해 다루기 때문에 전체적인 경로를 훑어봐야한다. 그렇기 때문에 다익스트라를 사용해선 안된다.
 * i -> j 로 이동할 때 간선이 있으면 1 없으면 0으로 표현한다.
 * i -> k -> j 를 연상해 문제를 풀어줘야한다.
 *
 */

public class 경로_찾기_11403 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[][] inputTable = new String[n][n];

        for(int i = 0; i < n; i++) {
            String[] splitValue = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                inputTable[i][j] = splitValue[j];
            }
        }

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(inputTable[i][k].equals("1") && inputTable[k][j].equals("1")) {
                        inputTable[i][j] = "1";
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sb.append(inputTable[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
