package src;
public class AlgebraProblem extends Problem {

    public AlgebraProblem() {
        super();

        createAlgebraProblem();
        calculateAnswer();
    }

    //Setters
    @Override
    public void setCoefficients(int[] coefficients) {
        super.setCoefficients(coefficients);

        calculateAnswer();
    }

    //Helper method to create a random problem from a set of problems
    public void createAlgebraProblem() {

        //Template for all the possible questions
        String[] possibleQuestions = {
            this.getCoefficients()[0] + "x = " + this.getCoefficients()[1],
            this.getCoefficients()[0] + "x + " + this.getCoefficients()[1] + " = " + this.getCoefficients()[2],
            "Linear Equation",
            this.getCoefficients()[0] + "/(" + this.getCoefficients()[1] + "x + " + this.getCoefficients()[2] + ") = " + this.getCoefficients()[3],
            "Quadratic Equation"
        };


        String problem = possibleQuestions[(int)(Math.random() * possibleQuestions.length)];
        this.setProblem(problem);
    }

    //Helper method to set the solution to the problem (currently hardcoded maybe not later?)
    public void calculateAnswer() {
        String question = this.getProblem();

        if (question.equals(this.getCoefficients()[0] + "x = " + this.getCoefficients()[1])) {
            this.setAnswer(new double[] {(double)this.getCoefficients()[1] / this.getCoefficients()[0]});

            // System.out.println((double)this.getCoefficients()[1] / this.getCoefficients()[0]);

        } else if (question.equals(this.getCoefficients()[0] + "x + " + this.getCoefficients()[1] + " = " + this.getCoefficients()[2])) {
            this.setAnswer(new double[] {(double)(this.getCoefficients()[2] - this.getCoefficients()[1]) / this.getCoefficients()[0]});

            // System.out.println((double)(this.getCoefficients()[2] - this.getCoefficients()[1]) / this.getCoefficients()[0]);

        } else if (question.equals("Linear Equation")) {
            createLinearEquation();

            double m = (double)(this.getCoefficients()[3] - this.getCoefficients()[1]) / (this.getCoefficients()[2] - this.getCoefficients()[0]);
            double b = (double)(this.getCoefficients()[3] - (m * this.getCoefficients()[2]));
            this.setAnswer(new double[] {m, b});

            // System.out.println(m + " " + b);

        } else if (question.equals(this.getCoefficients()[0] + "/(" + this.getCoefficients()[1] + "x + " + this.getCoefficients()[2] + ") = " + this.getCoefficients()[3])) {
            this.setAnswer(new double[] {(this.getCoefficients()[0] - (double)(this.getCoefficients()[3] * this.getCoefficients()[2])) / (this.getCoefficients()[3] * this.getCoefficients()[1])});

            // System.out.println((this.getCoefficients()[0] - (double)(this.getCoefficients()[3] * this.getCoefficients()[2])) / (this.getCoefficients()[3] * this.getCoefficients()[1]));

        } else if (question.equals("Quadratic Equation")) {
            this.setProblem(createQuadraticEquation());
            calculateQuadraticAnswer();
        }
    }

    //Creates a solvable y = mx + b problem such that x2 - x1 isn't a division by 0
    public void createLinearEquation() {
        while (true) {
            if (getCoefficients()[0] == getCoefficients()[2]) {
                getCoefficients()[0] = (int)Math.random() * 10 + 1;
                getCoefficients()[3] = (int)Math.random() * 10 + 1;
                continue;
            }

            this.setProblem("Find the equation y = mx + b for the line that runs through (" + this.getCoefficients()[0] + "," + this.getCoefficients()[1] + ") and (" + this.getCoefficients()[2] + "," + this.getCoefficients()[3] + ").");
            break;
        }
    }

    //Creates a real quadratic equation
    public String createQuadraticEquation() {

        while (!checkQuadraticEquation()) {
            for (int i = 0; i < this.getCoefficients().length; i++) {
                this.getCoefficients()[i] = (int)(Math.random() * 10) + 1;
            }
        }


        return this.getCoefficients()[0] + "x\u00b2 + " + this.getCoefficients()[1] + "x + " + this.getCoefficients()[2] + " = " + this.getCoefficients()[3];
    }

    //Checks that the quadratic equation is valid
    public boolean checkQuadraticEquation() {
        double determinant = (double)this.getCoefficients()[1] * this.getCoefficients()[1] - 4.0 * this.getCoefficients()[0] * (this.getCoefficients()[2] - this.getCoefficients()[3]);

        if (determinant < 0) {
            System.out.println("invalid");
            return false;
        }

        return true;
    }

    //Calculates the answer to the quadratic equation
    public void calculateQuadraticAnswer() {
        double determinant = Math.sqrt((double)this.getCoefficients()[1] * this.getCoefficients()[1] - 4.0 * this.getCoefficients()[0] * (this.getCoefficients()[2] - this.getCoefficients()[3]));

        if (determinant == 0) {
            this.setAnswer(new double[] {(-this.getCoefficients()[1]) / (2.0 * this.getCoefficients()[0])});
        } else {
            double answer1 = ((-this.getCoefficients()[1] - determinant) / (2.0 * this.getCoefficients()[0]));
            double answer2 = ((-this.getCoefficients()[1] + determinant) / (2.0 * this.getCoefficients()[0]));

            if (answer1 < answer2) {
                this.setAnswer(new double[] {answer1, answer2});
            } else {
                this.setAnswer(new double[] {answer2, answer1});
            }
        }
    }
}
