package src;
public class Problem {
    private int problemNumber;
    private static int numProblems = 0;
    private int[] coefficients;
    private String problem;
    private double[] answer;
    private double[] userAnswer;

    //Constructors
    public Problem() {
        this.problem = null;
        this.answer = null;

        //Creates a random amount of getCoefficients() for the question
        //TODO set an actual number for the number of getCoefficients() rather than just a placeholder
        this.coefficients = new int[10];
        for (int i = 0; i < this.coefficients.length; i++) {
            this.coefficients[i] = (int)(Math.random() * 10) + 1;
        }
    }

    public Problem(String problem, double[] answer) {
        this.problemNumber = ++numProblems;
        this.problem = problem;
        this.answer = answer;
    }

    //Getters
    public static int getNumProblems() {
        return numProblems;
    }

    public int getProblemNumber() {
        return this.problemNumber;
    }

    public int[] getCoefficients() {
        return coefficients;
    }

    public String getProblem() {
        return this.problem;
    }

    public double[] getAnswer() {
        return this.answer;
    }

    public double[] getUserAnswer() {
        return userAnswer;
    }

    //Setters
    public void setCoefficients(int[] coefficients) {
        this.coefficients = coefficients;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setAnswer(double[] answer) {
        this.answer = answer;
    }

    public void setUserAnswer(double[] userAnswer) {
        this.userAnswer = userAnswer;
    }

    //Other methods
    public String getSimpleAnswer() {
        String simpleAnswer = "[";
        for (int i = 0; i < this.answer.length - 1; i++) {
            simpleAnswer += this.answer[i] + ", ";
        }
        simpleAnswer += this.answer[this.answer.length - 1] + "]";

        return simpleAnswer;
    }

    //TODO compare answer with some tolerance on the doubles to 3 decimal places (rounded)
}