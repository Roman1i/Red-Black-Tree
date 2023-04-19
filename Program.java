import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Boolean working = true;
        Tree tree = new Tree();
        while (working) {
            System.out.print("Введите число: ");
            String input = sc.nextLine();
            try {
                tree.add(Integer.parseInt(input));
                tree.print();
            } catch (Exception e) {
                if (input.equals("exit"))
                    working = false;
                else
                    System.out.println(e.getMessage());
            }
        }
    }
}