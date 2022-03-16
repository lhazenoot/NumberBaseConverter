package stage3.ConvertToAnyBase;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Print print = new Print();
        print.print();
    }
}

class Input {
    public String[] getBaseNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
        return scanner.nextLine().split(" ");
    }

    public String getConvertNumber(String[] base) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter number in base %s to convert to base %s (To go back type /back) ", base[0], base[1]);
        return scanner.nextLine();
    }
}

class Converter {
    public void converter(String[] base, String number) {
        String result;
        int from = Integer.parseInt(base[0]);
        int to = Integer.parseInt(base[1]);
        if (to == 10) {
            result = String.valueOf(Integer.parseInt(number, from));
        }
        else if (from == 10) {
            BigInteger big = new BigInteger(number);
            result = big.toString(to);
        }
        else {
            BigInteger big = new BigInteger(number, from);
            result = big.toString(to);
        }
        System.out.printf("Conversion result: %s\n", result);
    }
}

class Print {
    Input input = new Input();
    Converter converter = new Converter();

    public void print() {
        String[] inputBase = input.getBaseNumbers();

        while (!inputBase[0].equals("/exit")) {
            String number = input.getConvertNumber(inputBase);
            while (!number.equals("/back")) {
                converter.converter(inputBase, number);
                number = input.getConvertNumber(inputBase);
            }
            inputBase = input.getBaseNumbers();
        }
    }
}
