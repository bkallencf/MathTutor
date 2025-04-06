package src;
public class PowerRuleProblem {
    private int[][] polynomial;
    private CalculusProblem calculusProblem;

    //Constructors
    public PowerRuleProblem() {
    }

    public PowerRuleProblem(CalculusProblem calculusProblem, int[][] polynomial) {
        this.polynomial = polynomial;
        this.calculusProblem = calculusProblem;
        this.calculusProblem.setEvaluationPoints(1);
        this.calculusProblem.setProblem("Find the derivative of " + CalculusProblem.turnPolynomialIntoString(this.polynomial) + " when x = " + this.calculusProblem.getEvaluationPoints().get(0) + ".");
        solvePowerRule();
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
        this.calculusProblem.setProblem("Find the derivative of " + CalculusProblem.turnPolynomialIntoString(this.polynomial) + " when x = " + this.calculusProblem.getEvaluationPoints().get(0) + ".");
        solvePowerRule();
    }

    //Finds the derivative
    public void solvePowerRule() {
        int[][] derivativePolynomial = new int[polynomial.length][2];
        this.calculusProblem.setAnswerFields(new String[] {"d/dx = "});

        //If the polynomial is just a constant with no other terms, it sets the answer to 0
        if (this.polynomial[polynomial.length - 1][0] == 0) {
            this.calculusProblem.setGeneralEquation("0");
            this.calculusProblem.setAnswer(new double[] {0});
            return;
        }

        for (int i = 0; i < polynomial.length; i++) {
            if (this.polynomial[i][0] == 0) {
                derivativePolynomial[i][0] = 0;
                derivativePolynomial[i][1] = 0;
                continue;
            }
            derivativePolynomial[i][0] = polynomial[i][0] - 1;
            derivativePolynomial[i][1] = polynomial[i][0] * polynomial[i][1];
        }

        this.calculusProblem.setGeneralEquation(CalculusProblem.turnPolynomialIntoString(derivativePolynomial));

        int answer = 0;
        for (int i = 0; i < derivativePolynomial.length; i++) {
            answer += derivativePolynomial[i][1] * Math.pow(this.calculusProblem.getEvaluationPoints().get(0), derivativePolynomial[i][0]);
        }

        calculusProblem.setAnswer(new double[] {answer});
    }
    
}
