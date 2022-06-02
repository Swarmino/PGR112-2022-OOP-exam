package QuizProgram;

import QuizProgram.Question.BinarychoiceQuestion;
import QuizProgram.Question.MultiplechoiceQuestion;
import QuizProgram.Question.Topic;
import QuizProgram.User.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Program {
    JDBCOps ops = new JDBCOps();
    Scanner input = new Scanner(System.in);

    //Function that runs preparations before quiz
    public void init(){
        prepareQuestions();
        prepareQuestions();
        handleUserInteraction();
    }
    // Handles userinteraction in the menu
    public void handleUserInteraction(){
        int choice = 0;


        while (choice != 5){
            printMenu();
            choice = validateUserInput(input,1, 4);
            switch (choice){
                case 1 -> startQuiz();
                case 2 -> showScore();
                case 3 -> showAllScore();
                case 4 -> exitApplication();
            }

        }
    }
    //Prints the text for the menu
    private void printMenu(){
        System.out.printf("Welcome to the quiz show" +
                "\nChoose from the menu below by typing in the corresponding number" +
                "\n=========================\n");
        System.out.println("1. Start game");
        System.out.println("2. Show the score by user");
        System.out.println("3. Show top record holder");
        System.out.println("4. Exit");
        System.out.println("=========================");
    }
    //Runs the quiz part of the program
    public void startQuiz(){
        boolean nameInput = false;
        String inputName = null;
        User p1 = null;
        
        while (!nameInput){
            System.out.println("Please write your name:\n");
            p1 = new User();
            inputName = input.nextLine();
            inputName = input.nextLine();
            p1.setUserName(inputName.toLowerCase());

            nameInput = true;
        }

        topicPicker(p1);

        System.out.println("Starting quiz...");

    }
    //Picks the topic of the quiz randomly
    private void topicPicker(User name){
        boolean topicInput = false;
        int topicIndex;
        Topic topicChoice;
        QuizRunner qr;

        while (!topicInput){
            System.out.println("Which topic do you want?");
            System.out.printf("1. Games\n2. Movies\n");
            topicIndex = input.nextInt();
            if (topicIndex == 1){
                topicChoice = Topic.GAMES;
            } else {
                topicChoice = Topic.MOVIES;
            }
            qr = new QuizRunner(topicChoice, name);
            topicInput = true;
            qr.start();
        }
    }
    //Used to send user back after trying one round of quiz
    public void qr(Topic topicChoice, User name){
        QuizRunner qr = new QuizRunner(topicChoice, name);
        qr.start();
    }
    //Used to grab and show the score by the user.
    private void showScore(){
        Boolean inputUserName = false;
        String name = null;
        while (!inputUserName){
            System.out.println("Input the username:");
            input.nextLine();
            name = input.nextLine();
            inputUserName = true;
        }

        ArrayList<User> resultByUserList = ops.showScoreByUser(name);
        for (User user :
                resultByUserList) {
            System.out.println("Showing all users with that name...");
            System.out.println("User: " + user.getUserName() + " Movie Score: " + user.getScoreMovie() + " Game Score: " + user.getScoreGame());
            System.out.println("End of result...");
        }
    }

    private void showAllScore(){
        ArrayList<User> userList = ops.showScore();
        for (User user :
                userList) {
            int userScore = user.getScoreGame();
            String userName = user.getUserName();
            System.out.println("Showing all records");
            System.out.println("User: " + userName + " Score: " + userScore);
            System.out.println("End of records");
        }
    }

    private void exitApplication(){
        System.out.println("Thanks for playing! Goodbye...");
        System.exit(0);
    }

    public int validateUserInput(Scanner input, int minValue, int maxValue){
        int choice = minValue -1;
        String inputErrorMessage = "The value is not between " + minValue + " or " + maxValue;
        while (choice < minValue || choice > maxValue){
            System.out.println("Please input your choice:");
            try {
                choice = input.nextInt();
            }
            catch(InputMismatchException inputMismatchException){
                System.out.println(inputErrorMessage);
            }
            if (choice < minValue || choice > maxValue){
                System.out.println(inputErrorMessage);
            }
        }
        return choice;
    }

    private boolean prepareQuestions(){

        ArrayList<BinarychoiceQuestion> bcqList;
        ArrayList<MultiplechoiceQuestion> mpqList;

        try {
           bcqList = readInBinaryQuestionsFromFile("TextFiles/BQS.txt");
        } catch (FileNotFoundException fileNotFound) {
            throw new RuntimeException(fileNotFound.getMessage());
        }


        try {
            mpqList = readInMultiplechoiceQuestions("TextFiles/MQS.txt");
        } catch (FileNotFoundException fileNotFound) {
            throw new RuntimeException(fileNotFound.getMessage());
        }

        for (MultiplechoiceQuestion list:
             mpqList) {
            ops.addMultiplechoiceQuestionToDb(list);
        }

        for (BinarychoiceQuestion list :
                bcqList) {
            ops.addBinaryQuestionToDb(list);
        }
        return true;
    }

    public ArrayList<BinarychoiceQuestion> readInBinaryQuestionsFromFile(String file) throws FileNotFoundException {
        File binaryQuestionSource = new File(file);
        Scanner fileInput = new Scanner(binaryQuestionSource);
        ArrayList<BinarychoiceQuestion> content = new ArrayList<>();

        while (fileInput.hasNext()){
            Topic topic;
            int isAnswerCorrect;
            String question = fileInput.nextLine();
            isAnswerCorrect = fileInput.nextInt();
            fileInput.nextLine();
            String topicString = fileInput.nextLine();
            fileInput.nextLine(); // Reading --- in file

            if (Objects.equals(topicString, "GAME")){
                topic = Topic.GAMES;
            } else {
                topic = Topic.MOVIES;
            }

            BinarychoiceQuestion newBQ = new BinarychoiceQuestion(question, isAnswerCorrect, topic);
            content.add(newBQ);
        }
        fileInput.close();
        return content;

    }

    private ArrayList<MultiplechoiceQuestion> readInMultiplechoiceQuestions(String file) throws FileNotFoundException {
        File multipleQuestionSource = new File(file);
        Scanner input = new Scanner(multipleQuestionSource);
        ArrayList<MultiplechoiceQuestion> content = new ArrayList<>();

        while (input.hasNextLine()){
            String question, choice1, choice2, choice3, choice4, topicString;
            int isAnswerCorrect;
            Topic topic;

            question = input.nextLine();
            choice1 = input.nextLine();
            choice2 = input.nextLine();
            choice3 = input.nextLine();
            choice4 = input.nextLine();
            input.nextLine(); //Reading "Answer: " in file
            isAnswerCorrect = input.nextInt();
            input.nextLine();
            topicString = input.nextLine();
            input.nextLine(); //Reading --- in file

            if (Objects.equals(topicString, "GAME")){
                topic = Topic.GAMES;
            } else {
                topic = Topic.MOVIES;
            }

            MultiplechoiceQuestion newMQ = new MultiplechoiceQuestion(question, choice1, choice2, choice3,
                    choice4, isAnswerCorrect, topic);
            content.add(newMQ);
        }
        return content;

    }

}
