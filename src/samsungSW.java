import java.util.Scanner;

public class samsungSW {
    public static void main(String[] args){
        //가로 M, 세로 N, 구슬 (R, B), 구멍 O
        //움직임은 상하좌우만 가능
        //가장자리는 모두 # (벽으로 진행불가)
        Scanner line = new Scanner(System.in);
        int M = line.nextInt(); // 가로
        int N = line.nextInt(); // 세로
        String[] board = new String[M]; // 보드 만들기
        for(int i = 0; i<N; i++){
            board[i] = line.next();
        }

        int[][] dotM = new int[1][M];
        int[][] dotN = new int[1][N];
        char[][] shop = new char[M][N];
        int lineCount = 0;

        int rM=0,rN=0, bM=0,bN=0, oM=0,oN=0;
        for(int i = 0; i<M; i++){ // 각 R, B, O, . 좌표 구하기
            for(int j = 0; j<N; j++){
                if(board[i].charAt(j)=='R') {
                    rM = i;
                    rN = j;
                }
                else if(board[i].charAt(j)=='B') {
                    bM = i;
                    bN = j;
                }
                else if(board[i].charAt(j)=='O') {
                    oM = i;
                    oN = j;
                }
                else if(board[i].charAt(j)=='#'){
                    shop[i][j] = '#';
                }
                else if(board[i].charAt(j)=='.'){
                    dotM[0][lineCount] = i;
                    dotN[0][lineCount] = j;
                    lineCount++;
                }
            }
        }

        shop[rM][rN] = 'R';
        char tmp;
        int countR = 0;

        // R의 위치를 바꿔주는 구문
        for(int i =0; i<N; i++) {
            if(shop[rM][i] != '#' && shop[rM][i] != 'R'){
                countR++;
            }
        }
        // 아래 구문 바꿔주는 구문
        tmp = shop[rM][rN];
        shop[rM][rN] = shop[rM][rN+countR];
        shop[rM][rN+countR] = tmp;

        for(int i =0; i<N; i++) {
            System.out.print(shop[rM][i]+" ");
        }

    }
}