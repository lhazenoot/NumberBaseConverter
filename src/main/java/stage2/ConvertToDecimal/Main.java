package stage2.ConvertToDecimal;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.print();
    }
}

class Input {
    public String getConverter() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
        return scanner.next();
    }

    public Data inputFrom() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number in decimal system: ");
        int decimal = scanner.nextInt();
        System.out.print("Enter the target base: ");
        int base = scanner.nextInt();
        return new Data(decimal, base);
    }

    public Data inputTo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter source number: ");
        String number = scanner.next();
        System.out.print("Enter source base: ");
        int base = scanner.nextInt();
        return new Data(number, base);
    }
}

class Data {
    String number;
    int decimal;
    int base;

    Data(String number, int base) {
        this.number = number;
        this.base = base;
    }

    Data(int decimal, int base) {
        this.decimal = decimal;
        this.base = base;
    }
}

class Converter {
    public void convertFrom(Data data) {
        String result = null;
        if (data.base == 2) {
            result = Integer.toBinaryString(data.decimal);
        }
        else if (data.base == 8) {
            result = Integer.toOctalString(data.decimal);
        }
        else if(data.base == 16) {
            result = Integer.toHexString(data.decimal);
        }
        System.out.printf("Conversion result: %s\n\n", result);
    }

    public void convertTo(Data data) {
        int result = Integer.parseInt(data.number, data.base);
        System.out.printf("Conversion to decimal result: %d\n\n", result);
    }
}

class Game {
    Input input = new Input();
    Converter converter = new Converter();

    public void print() {
        Data data;
        while (true) {
            String convert = input.getConverter();

            switch (convert) {
                case "/exit":
                    return;
                case "/from":
                    data = input.inputFrom();
                    converter.convertFrom(data);
                    break;
                case "/to":
                    data = input.inputTo();
                    converter.convertTo(data);
                default:
                    break;
            }
        }
    }
}
