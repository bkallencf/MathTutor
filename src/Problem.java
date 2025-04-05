package src;
public class Problem {
    private int problemNumber; //The number of the problem
    private static int numProblems = 0; //The number of problems creater
    private int[] coefficients; //List of coefficients that the problem uses
    private String problem; //The problem that the user is given
    private double[] answer; //The set of answers that a question requires
    private double[] userAnswer; //The set of answers that a user gives

    //Constructors
    public Problem() {
        this.problemNumber = ++numProblems;
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
    public void setCoefficients(int numCoefficients) {

        //Creates a random amount of getCoefficients() for the question depending on the amount it requires
        this.coefficients = new int[numCoefficients];
        for (int i = 0; i < this.coefficients.length; i++) {
            this.coefficients[i] = (int)(Math.random() * 10) + 1;
        };
    }

    //Sets an individual coefficient to change it
    public void setCoefficient(int coefficientIndex) {
        this.coefficients[coefficientIndex] = (int)(Math.random() * 10) + 1;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setAnswer(double[] answer) {
        this.answer = answer;

        this.userAnswer = new double[answer.length];
    }

    public void setUserAnswer(int answerIndex, double userAnswer) {
        this.userAnswer[answerIndex] = userAnswer;
    }

    //Other methods
    //Returns the answer array as a String; mostly for debugging purposes
    public static String getSimpleAnswer(double[] answerArr) {
        String simpleAnswer = "[";
        for (int i = 0; i < answerArr.length - 1; i++) {
            simpleAnswer += answerArr[i] + ", ";
        }
        simpleAnswer += answerArr[answerArr.length - 1] + "]";

        return simpleAnswer;
    }

    //Compares the user's answer with the actual answer
    public boolean compareAnswers() {
        for (int i = 0; i < this.answer.length; i++) {
            if (Math.round(this.answer[i] * 1000) != Math.round(this.userAnswer[i] * 1000)) {
                return false;
            }
        }

        return true;
    }

    public void displayProblem() {
        System.out.println("Question #" + this.problemNumber + "\n" + this.problem);
    }

    @Override
    public String toString() {
        return "Question #" + this.problemNumber + "\nProblem: " + this.problem + "\nAnswer: " + getSimpleAnswer(this.answer) + 
            "\nUser Answer: " + getSimpleAnswer(userAnswer) + "\n";
    }
}