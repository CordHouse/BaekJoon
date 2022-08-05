import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class 소트인사이트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int[] save = new int[input.length()];

        for(int i = 0; i<input.length();i++){
            save[i] = Character.getNumericValue(input.charAt(i));
        }

        IntStream intStream = Arrays.stream(save);
        intStream.boxed().sorted(Collections.reverseOrder()).forEach(System.out::print);
    }
}
