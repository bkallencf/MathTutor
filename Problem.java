public class Problem {
    private int problemNumber;
    private static int numProblems = 0;
    private String problem;
    private double[] answer;
    private double[] userAnswer;

    //Constructors
    public Problem() {
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