package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static String secret="";

    public static String generateSecretNumber(int length) {
        long pseudoRandomNumber = System.nanoTime();
        String lst=Long.toString(pseudoRandomNumber);
        StringBuilder sb=new StringBuilder(lst).reverse();
        StringBuilder code = new StringBuilder();
        int pointer=0;
        while(sb.charAt(pointer)=='0'){
            pointer++;
        }
        while (code.length() < length) {
            char digit=sb.charAt(pointer);
            if (code.toString().indexOf(digit) != -1) {
                pointer++;
            } else {
                code.append(digit);
            }
            if (pointer == sb.length()) {
                generateSecretNumber(length);
            }
        }

        return code.toString();
    }

    public static String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        while (code.length() < length) {
            int num=random.nextInt(9)+1;
            String Snum = Integer.toString(num);
            if (code.toString().indexOf(Snum) == -1) {
                code.append(Snum);
            }
        }

        return code.toString();
    }

    public static int calculateBullCows(String input) {
        int slen=input.length();

        int cow=0;
        int bull=0;

        for (int i = 0; i < slen; i++) {
            if (input.charAt(i) == secret.charAt(i)) {
                bull+=1;
            } else if (secret.indexOf(input.charAt(i))!=-1) {
                cow+=1;
            }
        }
        if (bull == 0 && cow == 0) {
            System.out.println("Grade: None.");
        }
        else if (bull==0 && cow>0){
            System.out.printf("Grade: %d cow(s).\n",cow);
        } else if (bull > 0 && cow == 0) {
            System.out.printf("Grade: %d bull(s).\n", bull);
        } else {
            System.out.printf("Grade: %d bull(s) and %d cow(s).\n", bull, cow);
        }
        return bull;

    }
    public static void main(String[] args) {
        String symbol="0123456789abcdefghijklmnopqrstuvwxyz";
        String star="*";
        System.out.println("Input the length of the secret code:");
        Scanner scanner = new Scanner(System.in);
        String ipt="";
        try{
        ipt=scanner.next();
        int input=Integer.parseInt(ipt);
        if (input > 36 || input<1) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        } else {
//            secret=generateSecretNumber(input);
            System.out.println("Input the number of possible symbols in the code:");
            String nsb="";
            try{
            nsb=scanner.next();
            int numOfSymbol=Integer.parseInt(nsb);
            if (numOfSymbol-input<0){
                System.out.printf("Error: it's not possible to generate a code with" +
                        " a length of %d with %d unique symbols.\n",numOfSymbol,input);
            }
            else{
//            secret=generateRandomNumber(input);
            String chosen=symbol.substring(0,numOfSymbol);
            String masked=star.repeat(numOfSymbol);
            if (numOfSymbol <= 10) {
                System.out.printf("The secret is prepared: %s (0-%c).\n",masked, chosen.charAt(chosen.length() - 1));
            }
            else{
                System.out.printf("The secret is prepared: %s (0-9,a-%c).\n",masked, chosen.charAt(chosen.length() - 1));
            }
            secret=generateRandomString(input,chosen);
            System.out.println("Okay, let's start a game!");

        int turn=0;
        int match=0;
        while (match != input) {
            turn++;
            System.out.printf("Turn %d:\n",turn);
            String guess=scanner.next();
            match=calculateBullCows(guess);
        }
        System.out.println("Congratulations! You guessed the secret code.\n");

    }
            }
            catch (Exception e){
                System.out.printf("Error: \"%s\" isn't a valid number.",ipt);
            }
        }
        }

        catch(Exception e){
            System.out.printf("Error: \"%s\" isn't a valid number.",ipt);
        };
    }


    private static String generateRandomString(int input, String chosen) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        int clen=chosen.length();

        while (code.length() < input) {
            int num=random.nextInt(clen);
            char chr = chosen.charAt(num);
            if (code.toString().indexOf(chr) == -1) {
                code.append(chr);
            }
        }

        return code.toString();
    }
}