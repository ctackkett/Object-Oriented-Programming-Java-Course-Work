import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int cases = Integer.parseInt(in.nextLine());
        for (int j = 0; j < cases; j++){
            String line1 = in.nextLine();
            String line2 = in.nextLine();
            String finalString = "";
            for (int i = 0; i < line1.length(); i++){
                if (line1.charAt(i) == line2.charAt(i))
                    finalString += ".";
                else
                    finalString += "*";
            }
            System.out.println(finalString);
        }

    }

}
