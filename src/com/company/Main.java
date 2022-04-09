package com.company;

import java.util.Scanner;
import static java.lang.String.join;
import static java.util.Collections.nCopies;

enum Roman{
    ONE("I", 1),
    TWO("II", 2),
    THREE("III", 3),
    FOUR("IV", 4),
    FIVE("V",5),
    SIX("VI",6),
    SEVEN("VII", 7),
    EIGHT("VII", 8),
    NINE("IX",9),
    TEN("X",10);
    private final int value;
    private final String romanNumeral;
    Roman(String romanNumeral, int value) {
        this.value = value;
        this.romanNumeral = romanNumeral;
    }
    public int value(){
        return value;
    }
    public String romanNumeral(){
        return romanNumeral;
    }
}

enum Operation{
    DIVIDE("/"),
    MULTIPLY("*"),
    ADD("+"),
    SUBTRACT("-");
    final String s;
    Operation(String s) {
        this.s = s;
    }
    public String value(){
        return s;
    }
}

class Main {

    //СКОПИРОВАЛ СО СТАКОВЕРФЛОУ! ГЕНИАЛЬНОЕ РЕШЕНИЕ
    static String getRomanNumber(int number) {
        return join("", nCopies(number, "I"))
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C")
                .replace("LXL", "XC");
    }

    static int valueRoman(String num){
        for (Roman r : Roman.values()){
            if (r.romanNumeral().equals(num)){
                return r.value();
            }
        }
        return -10;
    }

    static int operation(String expr){
        for (Operation operation : Operation.values()){
            if (operation.value().equals(expr)){
                return operation.ordinal();
            }
        }
        return -1;
    }

    static int valueArabic(String num) {
        int a;
        try {
            a = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            //Здесь не ловим, ловим дальше
            return 0;
        }
        return ((a > 0)&&(a < 11)) ? a : -10;
    }

    static int calculate(int a, String s, int b) throws Exception {
        return switch (operation(s)) {
            case 0 -> a / b;
            case 1 -> a * b;
            case 2 -> a + b;
            case 3 -> a - b;
            case -1 -> throw new Exception("Это не операция");
            default -> -10;
        };
    }

    static void getResult(String[] expr) throws Exception {

        int result;
        int a = valueRoman(expr[0]);
        int b = valueRoman(expr[2]);
        int c = valueArabic(expr[0]);
        int d = valueArabic(expr[2]);
        if (a > 0 && b > 0){
            result = calculate(a, expr[1], b);
            if (result < 1){
                throw new Exception("Римляне знали только о положительных числах");
            } else{
                System.out.println(getRomanNumber(result));
            }
        }
        else if (c > 0 && d > 0){
            System.out.println(calculate(c, expr[1], d));
        }
        else if (((a * b) < 0) && ((c * d) < 0)){
            throw new Exception("Нельзя работать с римскими и арабскими цифрами одновременно");
        }
        else if ((c * d) == 0){
            throw new Exception("По крайней мере один из параметров не число");
        }

    }

    static String[] input(String[] line) throws Exception {
        String[] expr = new String[3];
        try {
            for (int i = 0; i < line.length; i++) {
                expr[i] = line[i];
            }
            for (int i = 0; i < 3; i++) {
                if (expr[i]==null) throw new Exception("Слишком мало параметров");
            }
            return expr;
        } catch (ArrayIndexOutOfBoundsException exception){
            System.out.println("Слишком много параметров");
            System.exit(1);
        }
        return expr;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] line = input.trim().split("\s");
        getResult(input(line));
    }
}