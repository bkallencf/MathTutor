public class AlgebraProblem extends Problem {
    private int[] variables;

    public AlgebraProblem() {
        super();

        createAlgebraProblem();
        calculateAnswer();
    }

    //Getters
    public int[] getVariables() {
        return variables;
    }

    //Setters
    public void setVariables(int[] variables) {
        this.variables = variables;

        calculateAnswer();
    }

    //Helper method to create a random problem from a set of problems
    public void createAlgebraProblem() {

        //Creates a random amount of variables for the question
        this.variables = new int[10];
        for (int i = 0; i < this.variables.length; i++) {
            this.variables[i] = (int)(Math.random() * 10) + 1;
        }

        //Template for all the possible questions
        String[] possibleQuestions = {
                this.variables[0] + "x = " + this.variables[1],
                this.variables[0] + "x + " + this.variables[1] + " = " + this.variables[2],
                "Linear Equation",
                this.variables[0] + "/(" + this.variables[1] + "x + " + this.variables[2] + ") = " + this.variables[3],
                "Quadratic Equation"
        };


        String problem = possibleQuestions[(int)(Math.random() * possibleQuestions.length)];
        this.setProblem(problem);
    }

    //Helper method to set the solution to the problem (currently hardcoded maybe not later?)
    public void calculateAnswer() {
        String question = this.getProblem();

        if (question.equals(this.variables[0] + "x = " + this.variables[1])) {
            this.setAnswer(new double[] {(double)this.variables[1] / this.variables[0]});

            // System.out.println((double)this.variables[1] / this.variables[0]);

        } else if (question.equals(this.variables[0] + "x + " + this.variables[1] + " = " + this.variables[2])) {
            this.setAnswer(new double[] {(double)(this.variables[2] - this.variables[1]) / this.variables[0]});

            // System.out.println((double)(this.variables[2] - this.variables[1]) / this.variables[0]);

        } else if (question.equals("Linear Equation")) {
            createLinearEquation();

            double m = (double)(this.variables[3] - this.variables[1]) / (this.variables[2] - this.variables[0]);
            double b = (double)(this.variables[3] - (m * this.variables[2]));
            this.setAnswer(new double[] {m, b});

            // System.out.println(m + " " + b);

        } else if (question.equals(this.variables[0] + "/(" + this.variables[1] + "x + " + this.variables[2] + ") = " + this.variables[3])) {
            this.setAnswer(new double[] {(this.variables[0] - (double)(this.variables[3] * this.variables[2])) / (this.variables[3] * this.variables[1])});

            // System.out.println((this.variables[0] - (double)(this.variables[3] * this.variables[2])) / (this.variables[3] * this.variables[1]));

        } else if (question.equals("Quadratic Equation")) {
            this.setProblem(createQuadraticEquation());
            calculateQuadraticAnswer();
        }
    }

    //Creates a solvable y = mx + b problem such that x2 - x1 isn't a division by 0
    public void createLinearEquation() {
        while (true) {
            if (variables[0] == variables[2]) {
                variables[0] = (int)Math.random() * 10 + 1;
                variables[3] = (int)Math.random() * 10 + 1;
                continue;
            }

            this.setProblem("Find the equation y = mx + b for the line that runs through (" + this.variables[0] + "," + this.variables[1] + ") and (" + this.variables[2] + "," + this.variables[3] + ").");
            break;
        }
    }

    //Creates a real quadratic equation
    public String createQuadraticEquation() {

        while (!checkQuadraticEquation()) {
            for (int i = 0; i < this.variables.length; i++) {
                this.variables[i] = (int)(Math.random() * 10) + 1;
            }
        }


        return this.variables[0] + "x\u00b2 + " + this.variables[1] + "x + " + this.variables[2] + " = " + this.variables[3];
    }

    //Checks that the quadratic equation is valid
    public boolean checkQuadraticEquation() {
        double determinant = (double)this.variables[1] * this.variables[1] - 4.0 * this.variables[0] * (this.variables[2] - this.variables[3]);

        if (determinant < 0) {
            System.out.println("invalid");
            return false;
        }

        return true;
    }

    //Calculates the answer to the quadratic equation
    public void calculateQuadraticAnswer() {
        double determinant = Math.sqrt((double)this.variables[1] * this.variables[1] - 4.0 * this.variables[0] * (this.variables[2] - this.variables[3]));

        if (determinant == 0) {
            this.setAnswer(new double[] {(-this.variables[1]) / (2.0 * this.variables[0])});
        } else {
            double answer1 = ((-this.variables[1] - determinant) / (2.0 * this.variables[0]));
            double answer2 = ((-this.variables[1] + determinant) / (2.0 * this.variables[0]));

            if (answer1 < answer2) {
                this.setAnswer(new double[] {answer1, answer2});
            } else {
                this.setAnswer(new double[] {answer2, answer1});
            }
        }
    }
}
