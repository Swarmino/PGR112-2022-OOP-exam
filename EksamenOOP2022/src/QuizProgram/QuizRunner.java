package QuizProgram;


import QuizProgram.Question.BinarychoiceQuestion;
import QuizProgram.Question.MultiplechoiceQuestion;
import QuizProgram.Question.Topic;
import QuizProgram.User.User;

import java.beans.BeanInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class QuizRunner {

    Topic topic;
    User user;

    Program p = new Program();
    JDBCOps ops = new JDBCOps();

    int scoreMovie;
    int scoreGame;
    Scanner input = new Scanner(System.in);


    public QuizRunner(Topic topic, User user) {
        this.topic = topic;
        this.user = user;
    }

    public void start(){

        int typeOfQuestionInt = getRandomNumber(1, 5);

        if (typeOfQuestionInt == 1){
            int questionIndex = getRandomNumber(1, 3);

            ArrayList<MultiplechoiceQuestion> mcqMovieList = ops.getMultiplechoiceQuestionsCollectionByTopic(Topic.MOVIES);

            MultiplechoiceQuestion mcqRandomQuestion = mcqMovieList.get(questionIndex);

            printMultiplechoiceQuestion(mcqRandomQuestion, user, topic);
        } else if(typeOfQuestionInt == 2) {
            int questionIndex = getRandomNumber(1, 3);

            ArrayList<BinarychoiceQuestion> bqList = ops.getBinarychoiceQuestionCollectionByTopic(Topic.MOVIES);

            BinarychoiceQuestion bqRandomQuestion = bqList.get(questionIndex);

            printBinaryquestion(bqRandomQuestion, user, Topic.MOVIES);
        } else if (typeOfQuestionInt == 3){

            int questionIndex = getRandomNumber(1, 3);

            ArrayList<BinarychoiceQuestion> bqGAMESList = ops.getBinarychoiceQuestionCollectionByTopic(Topic.GAMES);

            BinarychoiceQuestion bqRandomQuestion = bqGAMESList.get(questionIndex);

            printBinaryquestion(bqRandomQuestion, user, Topic.GAMES);

        } else {

            int questionIndex = getRandomNumber(1, 3);

            ArrayList<MultiplechoiceQuestion> mcqGAMESList = ops.getMultiplechoiceQuestionsCollectionByTopic(Topic.GAMES);

            MultiplechoiceQuestion mcqRandomQuestion = mcqGAMESList.get(questionIndex);

            printMultiplechoiceQuestion(mcqRandomQuestion, user, Topic.GAMES);
        }


    }

    private int getRandomNumber(int min, int max){
        int range = max - min +1;
        int i = (int)Math.floor(Math.random()* range + min);
        return i;
    }


    private void printMultiplechoiceQuestion(MultiplechoiceQuestion q, User user, Topic topic){
        boolean qAnswered = false;
        Scanner input = new Scanner(System.in);

        while (!qAnswered){
            System.out.println(q.getQuestionText());
            System.out.printf(q.getChoice1() + q.getChoice2() + q.getChoice3() + q.getChoice4());
            System.out.println("\nType in either 1, 2, 3, or 4 to answer the question.");
            int choice = p.validateUserInput(input, 1, 4);
            if (choice == q.getCorrectAnswer()){

                if (topic == Topic.GAMES){
                    user.increaseScoreGame();
                } else {
                    user.increaseScoreMovie();
                }

                System.out.println("Correct!");
                qAnswered = true;
            } else {
                System.out.println("Wrong!");
                qAnswered =true;
            }
        }
        resultScreen();
    }

    private void printBinaryquestion(BinarychoiceQuestion q, User user, Topic topic){
        boolean qAnswered = false;


        while (!qAnswered){
            System.out.println(q.getQuestionText() );
            System.out.println("Type in either 1 for Yes or 0 for No.");
            int choice = p.validateUserInput(input, 0, 1);
            if (choice == q.getCorrectAnswer()){

                if (q.getTopic() == Topic.GAMES){
                    user.increaseScoreGame();
                } else {
                    user.increaseScoreMovie();
                }

                System.out.println("Correct!");
                qAnswered = true;
            } else {
                System.out.println("Wrong!");
                qAnswered =true;
            }
        }
        resultScreen();
    }

    private void resultScreen(){

        boolean rAnswered = false;
        while (!rAnswered){
            scoreGame = user.getScoreGame();
            scoreMovie = user.getScoreMovie();
            System.out.println("Your score is (based on topic):");
            System.out.println("GAMES: " + scoreGame);
            System.out.println("MOVIES: " + scoreMovie);

            System.out.println("Do you want to try again?");
            System.out.println("1. Try again");
            System.out.println("2. Back to the menu");
            int rInput = p.validateUserInput(input, 1, 2);
            if (rInput == 1){
                Program p = new Program();
                p.qr(topic, user);
            } else {
                int scoreGame = user.getScoreGame();
                int scoreMovie = user.getScoreMovie();
                ops.updateMovieScore(user);
                ops.updateGameScore(user);
                p.handleUserInteraction();
            }
        }
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void question(){

    }

}
