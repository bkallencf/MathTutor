package src;
public class AlgebraProblem extends Problem {

    public AlgebraProblem() {
        createAlgebraProblem(determineAlgebraProblemType());
    }

    public AlgebraProblem(String problemType) {
        createAlgebraProblem(problemType);
    }

    //Helper method with all the potential types of algebra questions
    public String determineAlgebraProblemType() {

        //Template for all the possible questions
        String[] possibleQuestions = {
            "Intro Algebra 1",
            "Intro Algebra 2",
            "Linear Equation",
            "Rational Equation 1",
            "Quadratic Equation 1",
            // "Exponential Equation",
            // "Logarithmic Equation",
            // "Graphing Form",
            // "Difference of Squares",
            // "Quadratic in Form Equations"
            // "Square Root",
        };


        String problem = possibleQuestions[(int)(Math.random() * possibleQuestions.length)];
        return problem;
    }

    //Helper method to set the solution to the problem (currently hardcoded maybe not later?)
    public void createAlgebraProblem(String problemType) {

        switch(problemType) {
            case "Intro Algebra 1" : {
                this.setCoefficients(2);
                this.setProblem(this.getCoefficients()[0] + "x = " + this.getCoefficients()[1]);
                this.setAnswer(new double[] {(double)this.getCoefficients()[1] / this.getCoefficients()[0]});
                this.setAnswerFields(new String[] {"x = "});
                break;
            }
            case "Intro Algebra 2" : {
                this.setCoefficients(3);
                this.setProblem(this.getCoefficients()[0] + "x + " + this.getCoefficients()[1] + " = " + this.getCoefficients()[2]);
                this.setAnswer(new double[] {(double)(this.getCoefficients()[2] - this.getCoefficients()[1]) / this.getCoefficients()[0]});
                this.setAnswerFields(new String[] {"x = "});
                break;
            }
            case "Linear Equation" : {
                this.setCoefficients(4);
                createLinearEquation();

                double m = (double)(this.getCoefficients()[3] - this.getCoefficients()[1]) / (this.getCoefficients()[2] - this.getCoefficients()[0]);
                double b = (double)(this.getCoefficients()[3] - (m * this.getCoefficients()[2]));
                this.setAnswer(new double[] {m, b});
                this.setAnswerFields(new String[] {"m = ", "b = "});
                break;
            }
            case "Rational Equation 1" : {
                this.setCoefficients(4);
                this.setProblem((this.getCoefficients()[0] + "/(" + this.getCoefficients()[1] + "x + " + this.getCoefficients()[2] + ") = " + this.getCoefficients()[3]));
                this.setAnswer(new double[] {(this.getCoefficients()[0] - (double)(this.getCoefficients()[3] * this.getCoefficients()[2])) / (this.getCoefficients()[3] * this.getCoefficients()[1])});
                this.setAnswerFields(new String[] {"x = "});
                break;
            }
            case "Quadratic Equation 1" : {
                this.setCoefficients(4);
                this.setProblem(createQuadraticEquation());
                calculateQuadraticAnswer();
                break;
            }
        }
    }

    //Creates a solvable y = mx + b problem such that x2 - x1 isn't a division by 0
    public void createLinearEquation() {
        while (true) {
            if (getCoefficients()[0] == getCoefficients()[2]) {
                setCoefficient(0);
                setCoefficient(2);
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
                setCoefficient(i);
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
            this.setAnswerFields(new String[] {"x = "});
        } else {
            double answer1 = ((-this.getCoefficients()[1] - determinant) / (2.0 * this.getCoefficients()[0]));
            double answer2 = ((-this.getCoefficients()[1] + determinant) / (2.0 * this.getCoefficients()[0]));
            this.setAnswerFields(new String[] {"x = ", "x = "});

            if (answer1 < answer2) {
                this.setAnswer(new double[] {answer1, answer2});
            } else {
                this.setAnswer(new double[] {answer2, answer1});
            }
        }
    }
}
