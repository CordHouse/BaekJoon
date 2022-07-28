import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 컵홀더 {
    public static void main(String[] args) throws IOException {
        // Step 1. 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫번째줄 입력
        int chair = Integer.parseInt(br.readLine());
        // 두번째줄 입력
        String seat = br.readLine();
        // S와 LL의 개수 카운트 + 1 -> 컵 홀더의 개수 자릿수 보다 항상 하나 더 많다.
        long star = (seat.chars().filter(c->c == 'L').count()/2) + (seat.chars().filter(c -> c == 'S').count()) + 1;

        if(star >= chair)
            System.out.println(chair);
        else
            System.out.println(star);
    }
}
