package src;

import java.util.concurrent.CountDownLatch;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class GUI {
    private Problem currentProblem;
    private int[] values;

    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel;
    private JLabel[] answerFieldLabels;
    private JTextField[] answerFields;
    private JButton submitButton;
    private JTextArea geminiFeedback;
    private JLabel generalFeedback;
    private JButton nextButton;
    private JTextField userResponse;

    //Default constructor used in numQuestions
    public GUI(CountDownLatch latch) {
        this.frame = new JFrame("Math Tutor Program");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1200, 800);

        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.frame.add(this.panel);

        String[] questions = {"How many algebra problems would you like to do?:", "How many geometry problems would you like to do?:", "How many calculus problems would you like to do?:"};
        this.answerFieldLabels = new JLabel[3];
        for (int i = 0; i < this.answerFieldLabels.length; i++) {
            this.answerFieldLabels[i] = new JLabel(questions[i]);
            this.answerFieldLabels[i].setFont(new Font("Sans-Serif", Font.PLAIN, 18));
            this.answerFieldLabels[i].setMaximumSize(new Dimension(400, 30));
            this.answerFieldLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        this.answerFields = new JTextField[this.answerFieldLabels.length];
        for (int i = 0; i < this.answerFields.length; i++) {
            this.answerFields[i] = new JTextField(20);
            this.answerFields[i].setMaximumSize(new Dimension(400, 30));
            this.answerFields[i].setFont(new Font("Sans-Serif", Font.PLAIN, 18));
            this.answerFields[i].setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        for (int i = 0; i < this.answerFields.length; i++) {
            this.panel.add(this.answerFieldLabels[i]);
            this.panel.add(this.answerFields[i]);
            panel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        this.submitButton = new JButton("Submit Answer");
        this.submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel.add(submitButton);

        this.generalFeedback = new JLabel(" ");
        this.generalFeedback.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        this.generalFeedback.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel.add(generalFeedback);

        this.frame.setVisible(true);

        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getNumQuestions();
                latch.countDown();
                frame.dispose();
            }
        });
    }
    
    //Constructor used for creating a GUI for all problems
    public GUI(Problem problem, CountDownLatch latch) {
        this.currentProblem = problem;

        this.frame = new JFrame("Math Tutor Program");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1200, 800);
        this.frame.setLayout(new BorderLayout());

        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.frame.add(this.panel, BorderLayout.CENTER);

        this.questionLabel = new JLabel("Q" + problem.getProblemNumber() + ": " + problem.getProblem());
        this.questionLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        this.questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel.add(this.questionLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] answerFieldStrings = problem.getAnswerFields();
        this.answerFieldLabels = new JLabel[answerFieldStrings.length];
        for (int i = 0; i < this.answerFieldLabels.length; i++) {
            this.answerFieldLabels[i] = new JLabel(answerFieldStrings[i]);
            this.answerFieldLabels[i].setFont(new Font("Sans-Serif", Font.PLAIN, 18));
            this.answerFieldLabels[i].setMaximumSize(new Dimension(400, 30));
            this.answerFieldLabels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        this.answerFields = new JTextField[this.answerFieldLabels.length];
        for (int i = 0; i < this.answerFields.length; i++) {
            this.answerFields[i] = new JTextField(20);
            this.answerFields[i].setFont(new Font("Sans-Serif", Font.PLAIN, 18));
            this.answerFields[i].setMaximumSize(new Dimension(400, 30));
            this.answerFields[i].setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        for (int i = 0; i < this.answerFields.length; i++) {
            this.panel.add(this.answerFieldLabels[i]);
            this.panel.add(this.answerFields[i]);
            this.panel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        this.submitButton = new JButton("Submit Answer");
        this.submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.panel.add(submitButton);

        this.panel.add(Box.createRigidArea(new Dimension(0, 20)));

        this.geminiFeedback = new JTextArea(10, 50);
        this.geminiFeedback.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        this.geminiFeedback.setLineWrap(true);
        this.geminiFeedback.setWrapStyleWord(true);
        this.geminiFeedback.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(geminiFeedback);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        this.panel.add(scrollPane);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30)); // padding to match
        this.nextButton = new JButton("Next");
        this.nextButton.setEnabled(false);
        bottomPanel.add(this.nextButton);
        this.frame.add(bottomPanel, BorderLayout.SOUTH);
        
        this.frame.setVisible(true);

        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getGeminiFeedback();
                talkToGemini();
            }
        });

        this.nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                latch.countDown();
                frame.dispose();
            }
        });
    }

    //Getters
    public int[] getValues() {
        return values;
    }

    //Gets feedback on the question from Gemini
    private void getGeminiFeedback() {
        try {
            for (int i = 0; i < this.currentProblem.getAnswer().length; i++) {
                this.currentProblem.getUserAnswer()[i] = Double.parseDouble(this.answerFields[i].getText());
            }

            this.submitButton.setEnabled(false);
            this.nextButton.setEnabled(true);
            for (int i = 0; i < this.answerFields.length; i++) {
                this.answerFields[i].setEditable(false);
            }

            String geminiPrompt = convertWrongAnswerToString(this.currentProblem);
            geminiFeedback.setText(JavaToPython.getGeminiResponse(geminiPrompt));
        }
        catch (NumberFormatException e) {
            geminiFeedback.setText("Please enter a valid number.");
        }
    }

    //Determines which response to give to Gemini based on whether the user gave a correct or incorrect answer to the problem
    public String convertWrongAnswerToString(Problem problem) {
        if (!problem.compareAnswers()) {
            return "Write your answer in plain text. You are tutoring a student who is trying to review algebra, geometry, and calculus problems. They got this question wrong." +
            "Please keep your responses short. Please provide an explanation of how to solve this question and where they might've gone wrong in their solution." +
            "Be gentle but firm in your explanations.\n" + problem.toString();
        }

        return "Write your answer in plain text. You are tutoring a student who is trying to review algebra, geometry, and calculus. Please keep your responses short" +
        "The user succesfuly answered the question correctly. Give them a warm congratulations.\n" + problem.toString();
    }

    //Creates an int[] for the types of questions and number of them to create
    private void getNumQuestions() {
        try {
            int[] numQuestions = new int[3];
            for (int i = 0; i < this.answerFields.length; i++) {
                if (answerFields[i].getText().isEmpty()) {
                    numQuestions[i] = 0;
                    continue;
                }

                numQuestions[i] = Integer.parseInt(this.answerFields[i].getText());
            }

            this.generalFeedback.setText("Inputs received.");
            this.values = numQuestions;
        }
        catch (NumberFormatException e) {
            this.generalFeedback.setText("Please enter a valid number.");
        }
    }

    //Allows the user to talk to gemini
    private void talkToGemini() {
        if (this.userResponse == null) {
            this.userResponse = new JTextField(20);
            this.userResponse.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
            this.userResponse.setMaximumSize(new Dimension(400, 30));
            this.userResponse.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.panel.add(Box.createRigidArea(new Dimension(400, 30)));
            this.panel.add(this.userResponse);

            //Updates the panel
            this.panel.revalidate();
            this.panel.repaint();
        }
        this.geminiFeedback.append("\n\nUse the box below and click the submit button to ask Gemini questions.");
        this.submitButton.setEnabled(true);

        //Removes the old event listener for submitButton
        for (ActionListener al : this.submitButton.getActionListeners()) {
            this.submitButton.removeActionListener(al);
        }

        //Replaces the event listener with one to talk to Gemini
        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passUserPrompt();
            }
        });
    }

    //Passes the user's prompt to Gemini and gets one back
    private void passUserPrompt() {
        try {
            String userPrompt = this.userResponse.getText();

            this.submitButton.setEnabled(false);
            this.userResponse.setText("");
            
            geminiFeedback.setText(JavaToPython.getGeminiResponse(userPrompt));
            this.submitButton.setEnabled(true);

        }
        catch (Exception e) {
            geminiFeedback.setText("Invalid prompt.");
        }
    }
}
