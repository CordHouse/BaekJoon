import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class 서로_다른_부분_문자열의_개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        Set<String> set = new LinkedHashSet<>();

        for(int i = 1; i<input.length()+1; i++){
            for(int j = 0; j<input.length(); j++){
                if(j+i > input.length())
                    break;
                set.add(input.substring(j,j+i));
            }
        }
        System.out.println(set.size());

//        List<String> list = new LinkedList<>();
//        String input = br.readLine();
//
//        for(int i = 1; i<input.length()+1; i++){
//            for(int j = 0; j<input.length(); j++){
//                if(j+i > input.length())
//                    break;
//                list.add(input.substring(j,j+i));
//            }
//        }
//
//        Stream<String> stream = list.stream();
//        System.out.println(stream.distinct().count());

    }
}
