import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int [] u = new int[2];
        int [] v = new int[2];

        u[0] = scanner.nextInt();
        u[1] = scanner.nextInt();
        v[0] = scanner.nextInt();
        v[1] = scanner.nextInt();

        double uLength = Math.sqrt(Math.pow(u[0], 2) + Math.pow(u[1], 2));
        double vLength = Math.sqrt(Math.pow(v[0], 2) + Math.pow(v[1], 2));

        double uvLength = uLength * vLength;

        double uv = (u[0] * v[0]) + (u[1] + v[1]);

        double cosFi = uv / uvLength;

        double fi = Math.pow(Math.cos(cosFi), -1);

        System.out.println(fi);

    }
}