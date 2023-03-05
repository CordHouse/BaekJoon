import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 바이러스 {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int cycle = Integer.parseInt(br.readLine());
        parent = new int[size + 1];
        init(size);
        for(int i = 1; i < cycle + 1; i++) {
            String[] line = br.readLine().split(" ");
            int num1 = Integer.parseInt(line[0]);
            int num2 = Integer.parseInt(line[1]);
            union(num1, num2);
        }
        for(int value : parent) {
            if(value == 1) {
                answer++;
            }
        }
        System.out.println(answer - 1);
    }

    public static void init(int size) {
        for(int i = 1; i < size + 1; i++) {
            parent[i] = i;
        }
    }

    public static void cycle(int before, int after) {
        int i = 0;
        for(int value : parent) {
            if(value == before) {
                parent[i] = after;
            }
            i++;
        }
    }
    public static void union(int num1, int num2) {
        int x = find(num1);
        int y = find(num2);

        if(x == y) {
            return;
        }

        if(x <= y) {
            cycle(y, x);
        }
        else {
            cycle(x, y);
        }
    }

    public static int find(int number) {
        if(parent[number] == number) {
            return parent[number];
        }
        return find(parent[number]);
    }
}
