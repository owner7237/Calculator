import java.util.Scanner;

public class Main {
    static String[] romanNumMap = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };
    static boolean isExpArabic = false;

    public static void main(String[] args) throws Exception {

        System.out.println("Напишите выражение:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println("Ответ:" + calc(input));
    }

    public static String calc(String input) throws Exception {
        int a;
        int b;
        int result;
        String[] expression;
        char expSign;

        if (input.split("\\+").length == 2) {
            expression = input.split("\\+");
            expSign = '+';
        } else if (input.split("-").length == 2) {
            expression = input.split("\\-");
            expSign = '-';
        } else if (input.split("\\*").length == 2) {
            expression = input.split("\\*");
            expSign = '*';
        } else if (input.split("\\/").length == 2) {
            expression = input.split("\\/");
            expSign = '/';
        } else {
            throw new Exception("Неподходящее выражение");
        }

        if (expression.length != 2) {
            throw new Exception("Неподходящее выражение");
        }

        a = getArabicNum(expression[0], true);
        b = getArabicNum(expression[1], false);

        switch (expSign) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;

            default:
                throw new Exception("Неподходящее выражение");
        }

        if (!isExpArabic) {
            if (result > 0) {
                return arabicToRoman(result);
            } else {
                throw new Exception("Неподходящее выражение. Ответ римскими числами не может быть меньше 1.");
            }
        }

        return String.valueOf(result);
    }

    static int getArabicNum(String str, boolean isFirstCheck) throws Exception {
        int num;

        try {
            num = Integer.parseInt(str.trim());
            if (isFirstCheck) {
                isExpArabic = true;
            }
        }
        catch (NumberFormatException e) {
            if (isExpArabic) {
                throw new Exception("Неподходящее выражение");
            }
            for (int i = 1; i < 12; i++) {
                if (str.trim().equals(romanNumMap[i])) {
                    num = i;
                    return num;
                }
            }

            throw new Exception("Неподходящее выражение");

        }

        if (isExpArabic && 0 < num && num < 11) {
            return num;
        } else {
            throw new Exception("Неподходящее выражение");
        }

    }

    static String arabicToRoman(int arabicNum) {
        int[] arabicValues = { 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] romanSymbols = { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        StringBuilder romanNum = new StringBuilder();

        for (int i = 0; i < arabicValues.length; i++) {
            while (arabicNum >= arabicValues[i]) {
                romanNum.append(romanSymbols[i]);
                arabicNum -= arabicValues[i];
            }
        }

        return romanNum.toString();
    }

}
