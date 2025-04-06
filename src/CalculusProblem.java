package src;

import java.util.ArrayList;
import java.util.Arrays;

public class CalculusProblem extends Problem {
    private ArrayList<Integer> evaluationPoints;
    private String generalEquation;
    
    //Constructors
    public CalculusProblem() {
        this.setAnswer(new double[1]);
        evaluationPoints = new ArrayList<>();

        createCalculusProblem(determineCalculusProblemType());
    }

    //Getters
    public ArrayList<Integer> getEvaluationPoints() {
        return this.evaluationPoints;
    }

    public String getGeneralEquation() {
        return this.generalEquation;
    }

    //Setters
    //Adds n random coefficients to this.evaluationPoints
    public void setEvaluationPoints(int evaluationPoints) {
        for (int i = 0; i < evaluationPoints; i++) {
            this.evaluationPoints.add((int)(Math.random() * 5) + 1);
        }
    }

    public void setNewEvaluationPoints(int evaluationPoints) {
        this.evaluationPoints = new ArrayList<>(evaluationPoints);
        for (int i = 0; i < evaluationPoints; i++) {
            this.evaluationPoints.add((int)(Math.random() * 5) + 1);
        }
    }

    public void setGeneralEquation(String generalEquation) {
        this.generalEquation = generalEquation;
    }

    //Helper method with all the potential types of calculus questions
    public String determineCalculusProblemType() {

        //Templates for all the possible questions
        String[] possibleQuestions = {
            // "Limits",
            "Power Rule",
            // "Product Rule",
            // "Quotient Rule",
            // "Chain Rule",
            // "Logarithm Derivative",
            // "Exponential Derivative",
            // "Base e Derivative",
            // "Trig Derivatives",
            // "Maxima/Minima",
            // "Higher Derivatives",
            "Integral Power Rule",
            // "U-Substitution",
            // "Integration By Parts",
            // "Partial Fraction Decomposition",
            // "Trig Integrals",
            // "Area Between Curves",
            // "Length of Curves",
            // "Infinite Integrals",
            // "Convergence",
        };

        String problem = possibleQuestions[(int)(Math.random() * possibleQuestions.length)];
        return problem;
    }

    //Creates 
    public void createCalculusProblem(String problemType) {

        switch(problemType) {
            case "Power Rule" : {
                new PowerRuleProblem(this, createPolynomial());
                break;
            }
            case "Integral Power Rule" : {
                new PowerIntegralProblem(this, createPolynomial());
                break;
            }
        }
    }

    //Creates a polynomial with terms between 1-5 where polynomial[i][0] = power and polynomial[i][1] = coefficient
    public int[][] createPolynomial() {
        int polynomialLength = (int)(Math.random() * 5) + 1;
        this.setCoefficients(polynomialLength);
        int[][] polynomial = new int[polynomialLength][2];

        ArrayList<Integer> potentialExponents = new ArrayList<>(6);
        for (int i = 0; i <= 5; i++) {
            potentialExponents.add(i);
        }

        int[] randomExponents = new int[polynomialLength];
        for (int i = 0; i < polynomialLength; i++) {
            randomExponents[i] = potentialExponents.remove((int)(Math.random() * (6 - i)));
        }
        Arrays.sort(randomExponents);

        for (int i = 0; i < polynomialLength; i++) {
            polynomial[i][0] = randomExponents[i];
            polynomial[i][1] = this.getCoefficients()[i];
        }

        return polynomial;
    }

    //Turns a polynomial into a String
    public static String turnPolynomialIntoString(int[][] polynomial) {
        String stringPolynomial = "";

        int count = polynomial.length - 1;
        while (polynomial[count][1] == 0 && count >= 0) {
            count--;
        }
        stringPolynomial += polynomial[count][1];
        if (polynomial[count][0] != 0) {
            stringPolynomial += "x" + convertToSuperscript(polynomial[count][0]);
        }

        for (int i = count - 1; i >= 0; i--) {
            if (polynomial[i][1] == 0) {
                continue;
            }
            stringPolynomial += " + " + polynomial[i][1];

            if (polynomial[i][0] != 0) {
                stringPolynomial += "x" + convertToSuperscript(polynomial[i][0]);
            }
        }

        return stringPolynomial;
    }

    //Turns a polynomial into a String
    public static String turnIntegralPolynomialIntoString(double[][] polynomial) {
        String stringPolynomial = "";

        //Returns 0 if the polynomial is just a zero
        if (polynomial[polynomial.length - 1][0] == 0) {
            stringPolynomial += polynomial[polynomial.length - 1][1];
            return stringPolynomial;
        }

        for (int i = polynomial.length - 1; i >= 0; i--) {
            stringPolynomial += polynomial[i][1];

            if (polynomial[i][0] != 0) {
                stringPolynomial += "x" + convertToSuperscript((int)polynomial[i][0]) + " + ";
            }
        }
        stringPolynomial += "C";

        return stringPolynomial;
    }

    //Converts an integer power into a superscript power
    public static String convertToSuperscript(int power) {
        switch(power) {
            case 0 : return "";
            case 1 : return "";
            case 2 : return "\u00b2";
            case 3 : return "^3"; //"\u00b3";
            case 4 : return "^4"; //"\u2074";
            case 5 : return "^5"; //"\u2075";
            case 6 : return "^6"; //"\u2076";
            default : {
                System.out.println("Error, power is out of range");
                return "";
            }
        }
    }

    @Override
    public String toString() {
        return "Question #" + this.getProblemNumber() + "\nProblem: " + this.getProblem() + "\nGeneral Equation: " + this.generalEquation +
            "\nAnswer: " + Problem.getSimpleAnswer(this.getAnswer()) + "\nUser Answer: " + Problem.getSimpleAnswer(this.getUserAnswer()) + "\n";
    }
}
