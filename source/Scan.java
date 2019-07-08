import java.io.*;
import java.lang.*;
import java.util.Scanner;

public class Scan{
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Simple Calculator\t(press ^C to exit)\n");
        while(sc.hasNextLine()){
            String in = sc.nextLine();
            in = in.trim();
            if(in.length() > 0){                    //call parser only for a "valid" input
                Parser p = new Parser(in);
            }
        }
        sc.close();
    }
}
