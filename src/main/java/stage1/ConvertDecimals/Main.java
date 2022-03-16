package stage1.ConvertDecimals;

// 2 = binary
// 8 = octal
// 10 = decimal
// 16 = hexadecimal

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Print print = new Print();
        print.result();
    }
}

class Input {
    public Data getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number in decimal system: ");
        int number = scanner.nextInt();
        System.out.print("Enter target base: ");
        int base = scanner.nextInt();
        return new Data(number, base);
    }
}

class Data {
    int number;
    int base;

    Data(int number, int base) {
        this.number = number;
        this.base = base;
    }
}

class Converter {
    public String convertNumber(Data data) {
        String result = null;

        if (data.base == 2) {
            result = Integer.toBinaryString(data.number);
        }
        else if (data.base == 8) {
            result = Integer.toOctalString(data.number);
        }
        else if (data.base == 16) {
            result = Integer.toHexString(data.number);
        }
        return result;
    }
}

class Print {
    Input input = new Input();
    Converter converter = new Converter();

    public void result() {
        Data data = input.getInput();
        String result = converter.convertNumber(data);
        System.out.printf("Conversion result: %s", result);
    }
}