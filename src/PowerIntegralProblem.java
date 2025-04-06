package src;

import java.util.Collections;

public class PowerIntegralProblem {
    private int[][] polynomial;
    private CalculusProblem calculusProblem;

    //Constructors
    public PowerIntegralProblem() {
    }

    public PowerIntegralProblem(CalculusProblem calculusProblem, int[][] polynomial) {
        this.polynomial = polynomial;
        this.calculusProblem = calculusProblem;
        verifyBounds();
        this.calculusProblem.setProblem("Find the integral of \u222B(" + CalculusProblem.turnPolynomialIntoString(this.polynomial) + 
            ")dx on [" + this.calculusProblem.getEvaluationPoints().get(0) + "," + this.calculusProblem.getEvaluationPoints().get(1) + "].");
        solvePowerIntegral();
    }

    //Getters
    public int[][] getPolynomial() {
        return this.polynomial;
    }

    public CalculusProblem getCalculusProblem() {
        return this.calculusProblem;
    }

    //Setters
    public void setPolynomial(int[][] polynomial) {
        this.polynomial = polynomial;
        this.calculusProblem.setProblem("Find the integral of \u222B(" + CalculusProblem.turnPolynomialIntoString(this.polynomial) + 
            ")dx on [" + this.calculusProblem.getEvaluationPoints().get(0) + "," + this.calculusProblem.getEvaluationPoints().get(1) + "].");
        solvePowerIntegral();
    }

    //Finds the integral
    public void solvePowerIntegral() {
        double[][] integralPolynomial = new double[polynomial.length][2];
        this.calculusProblem.setAnswerFields(new String[] {"\u222B = "});

        //If the polynomial is just a zero with no other terms, it sets the answer to 0
        if (this.polynomial[polynomial.length - 1][1] == 0) {
            this.calculusProblem.setGeneralEquation("0");
            this.calculusProblem.setAnswer(new double[] {0});
            return;
        }

        for (int i = 0; i < polynomial.length; i++) {
            integralPolynomial[i][0] = polynomial[i][0] + 1;
            integralPolynomial[i][1] =  polynomial[i][1] / integralPolynomial[i][0];
        }

        this.calculusProblem.setGeneralEquation(CalculusProblem.turnIntegralPolynomialIntoString(integralPolynomial));

        double answer1 = 0;
        double answer2 = 0;
        for (int i = 0; i < integralPolynomial.length; i++) {
            answer1 += integralPolynomial[i][1] * Math.pow(this.calculusProblem.getEvaluationPoints().get(0), integralPolynomial[i][0]);
            answer2 += integralPolynomial[i][1] * Math.pow(this.calculusProblem.getEvaluationPoints().get(1), integralPolynomial[i][0]);
        }

        calculusProblem.setAnswer(new double[] {answer2 - answer1});
    }
    
    //Makes sure the higher bounds is higher and the bounds aren't equal
    public void verifyBounds() {
        this.calculusProblem.setEvaluationPoints(2);

        while (true) {
            if (this.calculusProblem.getEvaluationPoints().get(0) != this.calculusProblem.getEvaluationPoints().get(1)) {
                break;
            }

            this.calculusProblem.setNewEvaluationPoints(2);
        }

        Collections.sort(this.calculusProblem.getEvaluationPoints());
    }
}
