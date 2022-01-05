package com.company;

import java.util.*;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int number1, number2;
    static char operator;
    static int result;

    public static void main(String[] args) {

        while (true) {
            System.out.println("input: ");
            String input = scanner.nextLine().toUpperCase(Locale.ROOT);
            char[] charMassive = new char[10];
            for (int i = 0; i < input.length(); i++) {
                charMassive[i] = input.charAt(i);
                if (input.charAt(i) == '+') {
                    operator = '+';
                }
                if (input.charAt(i) == '-') {
                    operator = '-';
                }
                if (input.charAt(i) == '*') {
                    operator = '*';
                }
                if (input.charAt(i) == '/') {
                    operator = '/';
                }
            }

            String charMassiveString = String.valueOf(charMassive);
            String[] symbols = charMassiveString.split("[+-/*]");
            String input0 = symbols[0];
            String input1 = symbols[1];
            String input2 = input1.trim();
            try {
                number1 = RomanToArabic(input0);
                number2 = RomanToArabic(input2);
                if (number1 < 0 && number2 < 0) {
                    result = 0;
                } else {
                    result = Calculate(number1, number2, operator);
                    String resultRoman = ArabicToRoman(result);
                    System.out.println(input0 + " " + operator + " " + input2 + " = " + resultRoman);
                }
            } catch (NullPointerException e) {
                try {
                    number1 = Integer.parseInt(input0);
                    number2 = Integer.parseInt(input2);
                    if (number1 > 10 || number2 > 10) try {
                        throw new Exception();
                    } catch (Exception exception) {
                        System.out.println("Input numbers can't be bigger than 10 !!!");
                        continue;
                    }
                    result = Calculate(number1, number2, operator);
                    System.out.println(number1 + " " + operator + " " + number2 + " = " + result);
                } catch (NumberFormatException ex) {
                    System.out.println("Wrong input !!!");
                }
            }
        }
    }

    public static int Calculate(int number1, int number2, char operator) {
        int result = 0;
        if (operator == '+') {
            result = number1 + number2;
        } else if (operator == '-') {
            result = number1 - number2;
        } else if (operator == '*') {
            result = number1 * number2;
        } else if (operator == '/') {
            result = number1 / number2;
        } else {
            System.out.println("Write the correct operator!!!");
        }
        return result;
    }

    public static String ArabicToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[]{"I", "IV", "V", "IX", "X", "XL", "L",
                "XC", "C", "CD", "D", "CM", "M"};
        int[] ints = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500,
                900, 1000};
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

    private static final Map<Character, Integer> roman = new HashMap<>() {
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

    public static int RomanToArabic(String s) throws NumberFormatException {
        int sum = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (i != n - 1 && roman.get(s.charAt(i)) < roman.get(s.charAt(i + 1))) {
                sum += roman.get(s.charAt(i + 1)) - roman.get(s.charAt(i));
                i++;
            } else {
                sum += roman.get(s.charAt(i));
            }
        }
        return sum;
    }

}

