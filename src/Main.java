package src;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // ArrayList<Problem> p = createProblems(new int[] {5, 5, 4});

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

        //Compares the answers to each other and returns the wrong ones
        ArrayList<Problem> wrongProblems = compareWrongAnswers(problems);

        //Creates a String from the wrong problems to pass to the AI
        String geminiPrompt = convertWrongAnswersToString(wrongProblems);

        //Passes the String to the AI and gets the response for the user
        JavaToPython.userPrompt = geminiPrompt;
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
            System.out.print(problem.getAnswerFields()[i]);
            problem.setUserAnswer(i, checkDoubleInput(in));
        }
        System.out.println();
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

    //Compares the answers to each other and adds the ones with wrong answers into an ArrayList to eventually pass to the AI
    public static ArrayList<Problem> compareWrongAnswers(ArrayList<Problem> problems) {
        ArrayList<Problem> wrongProblems = new ArrayList<>();

        for (int i = 0; i < problems.size(); i++) {
            if (!problems.get(i).compareAnswers()) {
                wrongProblems.add(problems.get(i));
            }
        }

        return wrongProblems;
    }

    //Creates a String to pass to the AI
    public static String convertWrongAnswersToString(ArrayList<Problem> wrongProblems) {
        String geminiPrompt = "A student is trying to review algebra, geometry, and calculus problems. These are the problems they struggled with." +
        "Please provide an explanation of how to solve these questions and where they might've gone wrong in their solution.\n";

        for (int i = 0; i < wrongProblems.size(); i++) {
            geminiPrompt += wrongProblems.get(i).toString();
        }

        return geminiPrompt;
    }

}
