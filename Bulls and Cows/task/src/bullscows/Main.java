package bullscows;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

//Setting secret code length
        int size = setSize();

        int turns = 1;

//Setting the number of possible signs in secret code
        System.out.println("Input the number of possible symbols in the code:");
        int possibleSymbols = 0;
        do {
            possibleSymbols = scanner.nextInt();
            if (possibleSymbols < size) {
                System.out.println("Error: it's not possible to generate a code with a length of " + size + " with " + possibleSymbols + " unique symbols.");
            } else if (possibleSymbols > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            }
            if (possibleSymbols > 36 || possibleSymbols < size) {
                exit(0);
            }
        } while (possibleSymbols < size || possibleSymbols > 36);

//Set of all possible char
        String signSet = "0123456789abcdefghijklmnopqrstuvwxyz";

//List of possible char chosen by the player
        List<Character> chosenChars = new ArrayList<>();
//Randomly generated secret code list
        List<Character> shuffledList = new ArrayList<>();

        for (int i = 0; i < possibleSymbols; i++) {
            chosenChars.add(i, signSet.charAt(i));
        }

//Algorithm for randomize char set and create the secret code
        for (int i = 0; i < size; i++) {
            Collections.shuffle(chosenChars);
            shuffledList.add(i, chosenChars.get(0));
            chosenChars.remove(chosenChars.get(0));
        }

        StringBuilder secretNumber = new StringBuilder();
        for (char num : shuffledList) {
            secretNumber.append(num);
        }

// Printing code length and possible symbols
        System.out.print("The secret is prepared: " );
        for (int i = 0; i < size; i++) {
            System.out.print("*");
        }
        if (possibleSymbols < 10) {
            System.out.printf(" (0-%c).", signSet.charAt(possibleSymbols-1));
        } else {
            System.out.printf(" (0-9, a-%c).", signSet.charAt(possibleSymbols-1));
        }


        System.out.println("Okay, let's start a game!");

        int bulls = 0;
        int cows = 0;


//Core of the game
        List<Character> answerList;
        do {
            answerList = generateAnswerList(size);

            for (int i = 0; i < shuffledList.size(); i++) {
                if (shuffledList.get(i).equals(answerList.get(i))) {
                    bulls++;
                } else if (shuffledList.contains(answerList.get(i)) && !shuffledList.get(i).equals(answerList.get(i))) {
                    cows++;
                }
            }

            printResult(turns, cows, bulls, secretNumber);

            bulls = 0;
            cows = 0;
            turns++;

        } while (!answerList.equals(shuffledList));

    }

    public static int setSize() {
        System.out.println("Input the length of the secret code:");
        Scanner scanner = new Scanner(System.in);
        int size = 0;
//        do {
        String sizeString = scanner.nextLine();
            try {
                size = Integer.parseInt(sizeString);
                if (size > 36) {
                    System.out.println("Error: can't generate a secret number with a length of " + size + " because there aren't enough unique digits.");
                } else if (size <= 0) {
                    System.out.println("Error: secret code is too short!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: \"" + size + "\" isn't a valid number.");
                exit(0);
            }
            if (size > 36 || size < 1) {
                exit(0);
            }

//        } while (size > 36 || size < 1);

        return size;
    }


    public static List<Character> generateAnswerList(int size) {
        Scanner scanner = new Scanner(System.in);
        int temp = 0;
        String answer = "";
        List<Character> answerList = new ArrayList<>();

        do {
            answer = scanner.nextLine();
            if (answer.length() != size) {
                System.out.println("Your answer should be at least " + size + " character long");
            }
        } while (answer.length() != size);


        for (int i = 0; i < size; i++) {
                answerList.add(i, answer.charAt(i));
            }
        if (!answerList.isEmpty()){
            for (int i = 0; i < size; i++) {
                answerList.set(i, answer.charAt(i));
            }
        }
        return answerList;
    }


    public static void printResult(int turns, int cows, int bulls, StringBuilder secretNumber) {
        System.out.println("Turn " + turns + ":");

        if (bulls == 0 && cows != 0) {
            System.out.println("Grade: " + cows + " cow(s). The secret code is " + secretNumber);
        } else if (cows == 0 && bulls != 0) {
            System.out.println("Grade: " + bulls + " bull(s). The secret code is " + secretNumber);
        } else if (cows == 0 && bulls == 0) {
            System.out.println("Grade: None. The secret code is " + secretNumber);
        } else {
            System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s). The secret code is " + secretNumber);
        }

        if (bulls == secretNumber.length()) {
            System.out.println("Congratulations! You guessed the secret code.");
        }
    }
}
