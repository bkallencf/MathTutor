package src;
public class GeometryProblem extends Problem {
    private int theta; //Random angle

    //Constructors
    public GeometryProblem() {
        this.theta = (int)(Math.random() * 89) + 1;
        
        createGeometryProblem(determineGeometryProblemType());
    }

    public GeometryProblem(String problemType) {
        this.theta = (int)(Math.random() * 89) + 1;

        createGeometryProblem(problemType);
    }

    //Getters
    public int getTheta() {
        return theta;
    }

    //Setters
    public void setTheta(int theta) {
        this.theta = theta;
    }

    //Helper method with all the potential types of geometry questions
    public String determineGeometryProblemType() {
        
        //Templates for all the possible questions
        String[] possibleQuestions = {
            "Straight Angles",
            "Vertical Angles",
            // "Interior Angles/Transversals",
            "Rectangle Perimeter",
            // "Triangle Perimeter",
            "Rectangle Area",
            // "Square Area",
            "Triangle Area",
            "Circle Circumference",
            "Circle Area",
            // "Irregular Shape Perimeter",
            // "Irregular Shape Area",
            // "Sine",
            // "Cosine",
            // "Pythagorean Theorem",
        };
        //TODO tangent, arcsine etc., triangle similarity, scaling, rotating, transforms, 3D shapes

        String problem = possibleQuestions[(int)(Math.random() * possibleQuestions.length)];
        return problem;
    }

    //Creates templates for each problem and calculates answers at the same time
    public void createGeometryProblem(String problemType) {
        String question = "";
        double[] answer = new double[1];

        switch (problemType) {
            case "Straight Angles" : {
                this.setCoefficients(3);
                question = "An angle of 180\u00B0 is split into 2 angles of " + getCoefficients()[0] + "x + " + getCoefficients()[1] + " and " + getCoefficients()[2] + "x. Find the measure of each angle.\n" +
                        "         /    \n" +
                        "        /     \n" +
                        "       /      \n" +
                        "------/-------- ";
                double angleVariable = (180.0 - getCoefficients()[1]) / (getCoefficients()[0] + getCoefficients()[2]);
                answer = new double[] {(getCoefficients()[0] * angleVariable) + getCoefficients()[1], (getCoefficients()[2] * angleVariable)};
                break;
            }
            case "Vertical Angles" : {
                question = "Two lines intersect at an angle of x = " + this.theta + "\u00B0. Find the value of A, B, and C.\n" +
                        "          /    \n" +
                        "         /     \n" +
                        "   A    /  x   \n" +
                        "-------/-------\n" +
                        "   B  /    C   \n" +
                        "     /         \n" +
                        "    /            ";
                answer = new double[] {180 - this.theta, this.theta, 180 - this.theta};
                break;
            }
            case "Rectangle Perimeter" : {
                this.setCoefficients(2);
                question = "Find the perimeter of a rectangle with a width of " + getCoefficients()[0] + " and a length of " + getCoefficients()[1] + ".\n" +
                        "+-------------+\n" +
                        "|             |\n" +
                        "|             |\n" +
                        "+-------------+  ";
                answer = new double[] {(2.0 * getCoefficients()[0]) + (2.0 * getCoefficients()[1])};
                break;
            }
            case "Rectangle Area" : {
                this.setCoefficients(2);
                question = "Find the area of a rectangle with a width of " + getCoefficients()[0] + " and a length of " + getCoefficients()[1] + ".\n" +
                        "+-------------+\n" +
                        "|             |\n" +
                        "|             |\n" +
                        "+-------------+  ";
                answer = new double[] {(double)getCoefficients()[0] * getCoefficients()[1]};
                break;
            }
            case "Triangle Area" : {
                this.setCoefficients(2);
                question = "Find the area of a triangle with a base of " + getCoefficients()[0] + " and a height of " + getCoefficients()[1] + ".\n" +
                        "     ^      \n" +
                        "    / \\     \n" +
                        "   /   \\    \n" +
                        "  /     \\   \n" +
                        " /       \\  \n" +
                        "+---------+    ";
                answer = new double[] {(0.5 * getCoefficients()[0] * getCoefficients()[1])};
                break;
            }
            case "Circle Circumference" : {
                this.setCoefficients(1);
                question = "Find the circumference of a circle with a radius of " + getCoefficients()[0] + ".\n" +
                        "    *  *     \n" +
                        " *        *  \n" +
                        "*          * \n" +
                        "*          * \n" +
                        " *        *  \n" +
                        "    *  *       ";
                answer = new double[] {(Math.PI * 2 * getCoefficients()[0])};
                break;
            }
            case "Circle Area" : {
                this.setCoefficients(1);
                question = "Find the area of a circle with a diameter of " + getCoefficients()[0] + ".\n" +
                        "    *  *     \n" +
                        " *        *  \n" +
                        "*          * \n" +
                        "*          * \n" +
                        " *        *  \n" +
                        "    *  *       ";
                answer = new double[] {(Math.PI * (0.5 * getCoefficients()[0]) * (0.5 * getCoefficients()[0]))};
                break;
            }
        }

        this.setProblem(question);
        this.setAnswer(answer);
    }
}