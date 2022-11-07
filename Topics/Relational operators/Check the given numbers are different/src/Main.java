import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        boolean diff = false;

        if (a != b && b != c && a != c) {
            diff = true;
        }

        System.out.println(diff);
    }
}