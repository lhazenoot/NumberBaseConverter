package stage4.ConvertFractions;

import java.math.BigDecimal;
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

    public String[] getConvertNumber(String[] base) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter number in base %s to convert to base %s (To go back type /back) ", base[0], base[1]);
        return scanner.nextLine().split("\\.");
    }
}

class Converter {
    public void converter(String[] base, String[] number) {
        String result;

        if (number.length == 2) {
            result = convertFractionNumber(base, number);
        }
        else {
            result = convertWholeNumber(base, number);
        }
        System.out.printf("Conversion result: %s\n", result);
    }

    public String convertFractionNumber(String[] base, String[] number) {
        String whole = number[0];
        String fraction = number[1];
        int sourceBase = Integer.parseInt(base[0]);
        int targetBase = Integer.parseInt(base[1]);

        // whole number from-to any base
        if (!whole.equals("0")) {
            whole = new BigInteger(whole, sourceBase).toString(targetBase);
        }

        // fraction to decimal
        int raise = -1;
        double fractionDecimal = 0;
        for (int i = 0; i < fraction.length(); i++) {
            double decimal = Character.digit(fraction.charAt(i), sourceBase) * Math.pow(sourceBase, raise);
            fractionDecimal += decimal;
            raise--;
        }

        // fractionDecimal to any base
        BigDecimal division = new BigDecimal(String.valueOf((int) Math.floor(fractionDecimal * (Math.pow(targetBase, 5)))));
        BigDecimal divisor = new BigDecimal(String.valueOf(targetBase));
        BigDecimal[] divide;

        int count = 0;
        String remainder;
        StringBuilder convertFraction = new StringBuilder();

        while (count < 5) {
            divide = division.divideAndRemainder(divisor);
            division = divide[0];
            remainder = new BigInteger(String.valueOf(divide[1])).toString(targetBase);
            convertFraction.append(remainder);
            count++;
        }
        fraction = "." + convertFraction.reverse();
        return whole.concat(fraction);
    }

    public String convertWholeNumber(String[] base, String[] number) {
        int sourceBase = Integer.parseInt(base[0]);
        int targetBase = Integer.parseInt(base[1]);
        String whole = number[0];

        if (sourceBase == 10) {
            return new BigInteger(whole).toString(targetBase);
        }
        else if (targetBase == 10) {
            return String.valueOf(Integer.parseInt(whole, sourceBase));
        }
        else {
            return new BigInteger(whole, sourceBase).toString(targetBase);
        }
    }
}


class Print {
    Input input = new Input();
    Converter converter = new Converter();

    public void print() {
        String[] base = input.getBaseNumbers();
        while (!base[0].equals("/exit")) {
            String[] number = input.getConvertNumber(base);
            while (!number[0].equals("/back")) {
                converter.converter(base, number);
                number = input.getConvertNumber(base);
            }
            base = input.getBaseNumbers();
        }
    }
}

