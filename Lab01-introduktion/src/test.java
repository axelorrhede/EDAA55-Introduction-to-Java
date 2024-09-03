import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nbr = scan.nextInt();
        int x = 0;
        if (nbr == 0) {
            x = 1;
        } else {
            while (nbr > 0) {
                if (nbr % 10 == 0) {
                    x++;
                }
                nbr = nbr / 10;
            }
        }
        System.out.println(x);
    }
}