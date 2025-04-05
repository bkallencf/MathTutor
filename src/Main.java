package src;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // ArrayList<Problem> p = createProblems(new int[] {10, 10, 0});

        // for (int i = 0; i < p.size(); i++) {
        //     for (int j = 0; j < p.get(i).getAnswer().length; j++) {
        //         p.get(i).setUserAnswer(j, 5);
        //     }
        //     System.out.println(p.get(i));
        // }

        controlTutoringProgram();
    }

    public static void controlTutoringProgram() {
        //Asks the user for how many and what kinds of questions they want to answer and creates the questions
        int[] numQuestions = getNumQuestions();
        ArrayList<Problem> problems = createProblems(numQuestions);

        //Displays each problem and gets the user's answers
        for (int i = 0; i < problems.size(); i++) {
            getAnswerInputs(problems.get(i));
        }
        System.out.println();
    }

    //Determines the number of numQuestions to answer
    public static int[] getNumQuestions() {
        Scanner in = new Scanner(System.in);
        int[] numQuestions = new int[3];

        //TODO randomly determine the number of numQuestions based on the type selected and the total number of numQuestions they want
        // System.out.println("How many numQuestions would you like to do?: ");
        System.out.print("How many algebra problems would you like to do?:  ");
        numQuestions[0] = checkIntInput(in);

        System.out.print("How many geometry problems would you like to do?: ");
        numQuestions[1] = checkIntInput(in);

        System.out.print("How many calculus problems would you like to do?: ");
        numQuestions[2] = checkIntInput(in);

        return numQuestions;
    }

    //Checks that the user puts in an int input
    public static int checkIntInput(Scanner in) {
        while (true) {
            if (in.hasNextInt()) {
                int input = in.nextInt();
                in.nextLine();
                if (input >= 0) {
                    return input;
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

    //TODO heavily improve how answers are gotten, maybe create an Answer class?
    //Displays the problem to the user and gets their answer to set to the Problem
    public static void getAnswerInputs(Problem problem) {
        Scanner in = new Scanner(System.in);

        problem.displayProblem();
        for (int i = 0; i < problem.getAnswer().length; i++) {
            System.out.print("Input your answer for this problem (if multiple put them 1 at a time): ");
            problem.setUserAnswer(i, checkDoubleInput(in));
        }
    }

    //Checks that the user puts in a double input
    public static double checkDoubleInput(Scanner in) {
        while (true) {
            if (in.hasNextDouble()) {
                double input = in.nextDouble();
                in.nextLine();
                return input;
            }

            in.nextLine();
            System.out.print("Please provide a valid double: ");
        }
    }
}
