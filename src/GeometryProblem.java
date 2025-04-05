package src;
public class GeometryProblem extends Problem {
    private String shape;
    private int[] lengths;
    private int theta;

    //Constructors
    public GeometryProblem() {
        super();

        determineQuestionType();
    }

    //Getters
    public String getShape() {
        return shape;
    }

    public int[] getLengths() {
        return lengths;
    }

    public int getTheta() {
        return theta;
    }

    //Setters
    public void setShape(String shape) {
        this.shape = shape;
    }

    public void setLengths(int[] lengths) {
        this.lengths = lengths;
    }

    public void setTheta(int theta) {
        this.theta = theta;
    }

    //Helper method to create geometry problems
    public void determineQuestionType() {
        
        //Templates for all the possible questions
        String[] possibleQuestions = {
            "Straight Angle",
            "Transversal",
            "Interior Angles",
            "Rectangle Perimeter",
            "Triangle Perimeter",
            "Rectangle Area",
            "Square Area",
            "Triangle Area",
            "Circle Circumference",
            "Circle Area",
            "Sine",
            "Cosine",
        };
        //TODO tangent, arcsine etc., triangle similarity, scaling, rotating, transforms

        String problem = possibleQuestions[(int)(Math.random() * possibleQuestions.length)];
        this.setProblem(problem);
    }

    public void createGeometryQuestion() {
        switch (this.getProblem()) {
            case "Straight Angle" -> {

            }

        }
    }

    
}