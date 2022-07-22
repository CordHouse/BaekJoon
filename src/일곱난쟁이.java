import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class 일곱난쟁이 {
    public static void main(String[] args) throws IOException {
        ArrayList arrayList = new ArrayList();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        for(int i = 0; i < 9; i++) {
            arrayList.add(Integer.parseInt(br.readLine()));
        }
        arrayList.sort(Comparator.naturalOrder()); // 오름차순 정렬

        for(int i = 0; i< arrayList.size(); i++){
            sum += (int) arrayList.get(i);
        }
        for(int i = 0; i < arrayList.size()-1; i++){
            ArrayList answer = new ArrayList();
            for(int j = 1; j < arrayList.size(); j++){
                int cal = sum;
                cal -= ((int) arrayList.get(i) + (int) arrayList.get(j));
                if(cal == 100) {
                    for(int k = 0; k<arrayList.size(); k++){
                        if(k == i || k == j)
                            continue;
                        answer.add(arrayList.get(k));
                    }
                    break;
                }
            }
            if(answer.size() == 7) {
                for (int p = 0; p < answer.size(); p++) {
                    System.out.println(answer.get(p));
                }
                break;
            }
        }
    }
}
