package src;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Problem> p = createProblems(new int[] {10, 5, 0});

        for (int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i).getProblem());
            System.out.println(p.get(i).getSimpleAnswer());
        }
    }

    public static void controlTutoringProgram() {

    }

    //Determines the number of numQuestions to answer
    public static int[] getNumQuestions() {
        Scanner in = new Scanner(System.in);
        int[] numQuestions = new int[3];

        //TODO randomly determine the number of numQuestions based on the type selected and the total number of numQuestions they want
        // System.out.println("How many numQuestions would you like to do?: ");
        System.out.print("How many algebra numQuestions would you like to do?:  ");
        numQuestions[0] = checkIntInput(in);

        System.out.print("How many geometry numQuestions would you like to do?: ");
        numQuestions[1] = checkIntInput(in);

        System.out.print("How many calculus numQuestions would you like to do?: ");
        numQuestions[2] = checkIntInput(in);

        return numQuestions;
    }

    //Checks that the user puts in an int input
    public static int checkIntInput(Scanner in) {
        while (true) {
            if (in.hasNextInt()) {
                int input = in.nextInt();
                if (input >= 0) {
                    return in.nextInt();
                }
            }

            in.nextLine();
            System.out.print("Please provide a valid integer: ");
        }
    }

    //Creates each question
    public static ArrayList<Problem> createProblems(int[] numQuestions) {
        ArrayList<Problem> questions = new ArrayList<>();

        for (int i = 0; i < numQuestions[0]; i++) {
            questions.add(new AlgebraProblem());
        }

        for (int i = 0; i < numQuestions[1]; i++) {
            questions.add(new GeometryProblem());
        }

        for (int i = 0; i < numQuestions[2]; i++) {
            questions.add(new CalculusProblem());
        }

        return questions;
    }
}
