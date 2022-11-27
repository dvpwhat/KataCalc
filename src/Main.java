import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input");
        String inputString = scanner.nextLine();
        System.out.println(calc(inputString));
    }

    public static String calc(String input) throws Exception {
        RomeNumber1 romanNumber1;
        RomeNumber1 romanNumber2;
        int arabNumber1;
        int arabNumber2;
        String[] enterNumber = input.split(" ");

        if (enterNumber.length != 3)
            throw new Exception("Cтрока не является математической операцией");

        try {
            romanNumber1 = RomeNumber1.valueOf(enterNumber[0]);
            try {
                romanNumber2 = RomeNumber1.valueOf(enterNumber[2]);
            } catch (IllegalArgumentException e) {
                try {
                    Integer.parseInt(enterNumber[2]);
                    throw new Exception("Разные системы счисления");
                } catch (NumberFormatException ee) {
                    throw new Exception("Числа должны быть от 1 до 10 и от I до X");
                }
            }

            int result = calcProgres(romanNumber1.getArabicNumber(), romanNumber2.getArabicNumber(), enterNumber[1], true);
            return "Output \n" + toRoman1.toRoman(result);

        } catch (IllegalArgumentException e) {
            try {
                arabNumber1 = Integer.parseInt(enterNumber[0]);
                if (arabNumber1<1 || arabNumber1>10)
                    throw new Exception("Числа могу быть только от 1 до 10 и от I до X");

                try {
                    arabNumber2 = Integer.parseInt(enterNumber[2]);
                    if (arabNumber2<1 || arabNumber2>10)
                        throw new Exception("Числа могу быть только от 1 до 10 и от I до X");
                }
                catch (NumberFormatException ee) {
                    try {
                        RomeNumber1.valueOf(enterNumber[2]);
                        throw new Exception("Только римские иди арабские числа одновременно");
                    } catch (IllegalArgumentException eee) {
                        throw new Exception("Числа могу быть только от 1 до 10 и от I до X");
                    }
                }

                int result = calcProgres(arabNumber1, arabNumber2, enterNumber[1], false);

                //Выводим результат арабскими цифрами
                return "Output \n" + result;

            } catch (NumberFormatException ee) {
                throw new Exception("Только (+, -, /, *)");
            }

        }
    }

    static int calcProgres(int a, int b, String operation, boolean roman) throws Exception {
        int result;
        switch (operation) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new Exception("Только (+, -, /, *)");
        }
        if (roman && result < 1)
            throw new Exception("Римские числа не могут быть отрицательные");
        return result;
    }
}

class toRoman1 {

    final static TreeMap<Integer, String> map = new TreeMap<>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    static String toRoman(int number) {
        int l = map.floorKey(number);
        if (number == l) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number - l);
    }
}

enum RomeNumber1 {
    I(1), II(2), III(3), IV(4), V(5),
    VI(6), VII(7), VIII(8), IX(9), X(10);

    private int arabicNumber;

    RomeNumber1(int arabicNumber) {
        this.arabicNumber = arabicNumber;
    }

    public int getArabicNumber() {
        return arabicNumber;
    }
}