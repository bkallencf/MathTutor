package src;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {

        controlTutoringProgramGUI();

    }

    public static void controlTutoringProgramGUI() {
        CountDownLatch latch = new CountDownLatch(1);

        //Asks the user for how many and what kinds of questions they want to answer and creates the questions
        GUI questionGUI = new GUI(latch);

        //Waits for the user to submit input to the GUI
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] numQuestions = questionGUI.getValues();
        ArrayList<Problem> problems = createProblems(numQuestions);

        //Displays each problem and gets the user's answers
        GUI[] guiProblems = new GUI[problems.size()];
        JavaToPython.clearGeminiLog();
        for (int i = 0; i < problems.size(); i++) {
            latch = new CountDownLatch(1);
            guiProblems[i] = new GUI(problems.get(i), latch);
            

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
