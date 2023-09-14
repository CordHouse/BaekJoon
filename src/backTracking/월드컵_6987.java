package backTracking;

/**
 * 23. 09. 15 01시 40분 시작
 * 23. 09. 15 02시 21분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 각 국가별로 한 번씩 경기를 치룬다
 * 2. 각 국가별로 총 5번의 경기가 진행된다.
 *
 * 제한사항
 * 총 4줄이 주어진다.
 * 빈칸을 기준으로 총 18개의 숫자가 주어진다.
 * 승, 무, 패 6보다 작거나 같은 자연수 또는 0이다.
 *
 * 풀이
 * 문제를 처음 접근할 때 숫자로 개수로만 계산해 구하려 했으나, 다음과 같은 반례에 걸리고 말았다.
 * ex) 5 0 0 5 0 0 5 0 0 0 0 5 0 0 5 0 0 5
 * 조건 1번에 따라 국가별로 한 번 씩 경기를 진행하기 때문에 5승인 국가 여러개가 존재할 수 없는 것이다.
 * 또한 다른 국가는 꼭 패배가 존재해야한다.
 * 따라서 미리 배열에 각각 승, 무, 패를 입력받고 조건에따라 갯수를 차감하는 방식을 선택해야한다.
 * 단, 음수가 될 수 없기 때문에 음수가 된다면 0을 출력한다.
 */

public class 월드컵_6987 {
    public static final int MAX_TEAM_COUNT = 6;
    public static int[][] matches;
    public static boolean isEndGame = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // Step 1. 최대 경기 가능한 경우의 수 구하기 -> 총 15회
        int size = 0;
        for(int i = 1; i < MAX_TEAM_COUNT; i++) {
            size += i;
        }

        // Step 2. 경기 매치 별 팀 별도 저장
        matches = new int[size][2];
        int index = 0;
        for(int i = 0; i < MAX_TEAM_COUNT - 1; i++) {
            for(int j = i + 1; j < MAX_TEAM_COUNT; j++) {
                matches[index][0] = i;
                matches[index][1] = j;
                index++;
            }
        }

        for(int count = 0; count < 4; count++) {
            st = new StringTokenizer(br.readLine());
            int[][] worldCup = new int[3][MAX_TEAM_COUNT]; // 열 : 승/무/패, 행 : A,B,C,D,E,F팀
            boolean isPossible = true;

            // 모든 경기 결과 입력받기
            for(int i = 0; i < MAX_TEAM_COUNT; i++) {
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());

                worldCup[0][i] = win;
                worldCup[1][i] = draw;
                worldCup[2][i] = lose;

                // 한 팀당 5번을 경기해야 한다.
                if(win + draw + lose != 5) {
                    isPossible = false;
                    break;
                }
            }

            // 모든 팀의 경기 수가 조건에 일치하는 경우 경기 결과 비교 진행
            if(isPossible) {
                backTracking(worldCup, 0, size);
                if(isEndGame) {
                    sb.append(1);
                }
                else {
                    sb.append(0);
                }
            }
            else {
                sb.append(0);
            }

            sb.append(" ");
            isEndGame = false;
        }

        System.out.print(sb);
    }

    static void backTracking(int[][] worldCup, int matchCount, int size) {
        if(isEndGame) {
            return;
        }

        // 모든 게임을 수행할 수 있다면 이 월드컵은 가능하다.
        if(matchCount == size) {
            isEndGame = true;
            return;
        }

        int myTeam = matches[matchCount][0];
        int enemyTeam = matches[matchCount][1];

        // 승 -> 패
        if(worldCup[0][myTeam] > 0 && worldCup[2][enemyTeam] > 0) {
            worldCup[0][myTeam]--;
            worldCup[2][enemyTeam]--;
            backTracking(worldCup, matchCount + 1, size);
            worldCup[0][myTeam]++;
            worldCup[2][enemyTeam]++;
        }
        // 무 -> 무
        if(worldCup[1][myTeam] > 0 && worldCup[1][enemyTeam] > 0) {
            worldCup[1][myTeam]--;
            worldCup[1][enemyTeam]--;
            backTracking(worldCup, matchCount + 1, size);
            worldCup[1][myTeam]++;
            worldCup[1][enemyTeam]++;
        }
        // 패 -> 승
        if(worldCup[2][myTeam] > 0 && worldCup[0][enemyTeam] > 0) {
            worldCup[2][myTeam]--;
            worldCup[0][enemyTeam]--;
            backTracking(worldCup, matchCount + 1, size);
            worldCup[2][myTeam]++;
            worldCup[0][enemyTeam]++;
        }
    }
}
