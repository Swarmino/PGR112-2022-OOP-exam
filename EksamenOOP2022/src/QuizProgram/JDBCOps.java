package QuizProgram;

import QuizProgram.Question.BinarychoiceQuestion;
import QuizProgram.Question.MultiplechoiceQuestion;
import QuizProgram.Question.Topic;
import QuizProgram.User.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class JDBCOps {

    public JDBCOps(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException exception){
            exception.printStackTrace();
        }
    }

    public ArrayList<User> showScoreByUser(String userName){
        ArrayList<User> result = new ArrayList<>();
        try(Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/quizDb", "root", "admin123")) {


            Statement stmt = con.createStatement();

            String insertSql = "SELECT * FROM score WHERE user = '" + userName.toLowerCase() + "'" + "ORDER BY score desc";

            ResultSet resultSet = stmt.executeQuery(insertSql);
            while (resultSet.next()) {
                User u1 = new User();
                u1.setUserId(resultSet.getInt("id"));
                u1.setUserName(resultSet.getString("user"));
                String topic = resultSet.getString("topic");
                if (topic == "GAME"){
                    u1.setScoreGame(resultSet.getInt("score"));
                } else {
                    u1.setScoreMovie(resultSet.getInt("score"));
                }
                result.add(u1);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    public ArrayList<User> showScore(){
        ArrayList<User> result = new ArrayList<>();
        try(Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/quizDb", "root", "admin123")) {


            Statement stmt = con.createStatement();

            String insertSql = "SELECT * FROM score ORDER BY score desc";

            ResultSet resultSet = stmt.executeQuery(insertSql);
            while (resultSet.next()) {
                User u1 = new User();
                String topic = resultSet.getString("topic");
                u1.setUserName(resultSet.getString("user"));
                if (Objects.equals(topic, "MOVIES")){
                    u1.setScoreMovie(resultSet.getInt("score"));
                } else {
                    u1.setScoreGame(resultSet.getInt("score"));
                }
                result.add(u1);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    public boolean addScore(int score, User user, Topic topic){
        try(Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/quizDb", "root", "admin123")) {

            Statement stmt = con.createStatement();

            String insertSql = "INSERT INTO score(user, score, topic) VALUE (" + score + ", '" + user.getUserName() +
                    "', '" + topic.toString() + "')";

            stmt.executeUpdate(insertSql);
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }

    public void addBinaryQuestionToDb(BinarychoiceQuestion bcq){
        try(Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/quizDb", "root", "admin123")) {

            Statement stmt = con.createStatement();

            String insertSql = "INSERT INTO binaryQuiz(question, correctanswer, topic) VALUES ('" +
                    bcq.getQuestionText() + "', " + bcq.getCorrectAnswer() + ", '" + bcq.getTopic() + "')";

            stmt.executeUpdate(insertSql);
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        //System.out.println("Binary questions added");
    }

    public void addMultiplechoiceQuestionToDb(MultiplechoiceQuestion mcq){
        try(Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/quizDb", "root", "admin123")) {

            Statement stmt = con.createStatement();

            String insertSql = "INSERT INTO multichoiceQuiz(question, answerA, answerB, answerC, answerD, " +
                    "correctAnswer, topic) " +
                    "VALUES ('" + mcq.getQuestionText() + "', '" + mcq.getChoice1() + "', '" + mcq.getChoice2() + "', '" +
                    mcq.getChoice3() + "', '" + mcq.getChoice4() + "', " + mcq.getCorrectAnswer() + ", '" +
                    mcq.getTopic() + "')";

            stmt.executeUpdate(insertSql);
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        //System.out.println("Multiple choice questions added");
    }

    public ArrayList<MultiplechoiceQuestion> getMultiplechoiceQuestionsCollection(){
        ArrayList<MultiplechoiceQuestion> results = new ArrayList<>();
        try(Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/quizDb", "root", "admin123")) {

            Statement stmt = con.createStatement();

            String selectQuestion = "SELECT * FROM multichoiceQuiz";

            ResultSet resultSet = stmt.executeQuery(selectQuestion);
            while (resultSet.next()){
                int mcqId = resultSet.getInt("id");
                MultiplechoiceQuestion mcq1 = new MultiplechoiceQuestion(mcqId);
                mcq1.setQuestionText(resultSet.getString("question"));
                String topicString = resultSet.getString("topic");
                if (topicString == "GAME"){
                    mcq1.setTopic(Topic.GAMES);
                } else {
                    mcq1.setTopic(Topic.MOVIES);
                }
                mcq1.setChoice1(resultSet.getString("answerA"));
                mcq1.setChoice2(resultSet.getString("answerB"));
                mcq1.setChoice3(resultSet.getString("answerC"));
                mcq1.setChoice4(resultSet.getString("answerD"));

                mcq1.setCorrectAnswer(resultSet.getInt("correctAnswer"));
                results.add(mcq1);
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return results;
    }

    public ArrayList<BinarychoiceQuestion> getBinarychoiceQuestionCollection(){
        ArrayList<BinarychoiceQuestion> results = new ArrayList<>();
        try(Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/quizDb", "root", "admin123")) {

            Statement stmt = con.createStatement();

            String selectQuestion = "SELECT * FROM binaryQuiz";

            ResultSet resultSet = stmt.executeQuery(selectQuestion);
            while (resultSet.next()){
                int bcId = resultSet.getInt("id");
                BinarychoiceQuestion bc1 = new BinarychoiceQuestion(bcId);
                bc1.setQuestionText(resultSet.getString("question"));
                String topicString = resultSet.getString("topic");
                if (topicString == "GAME"){
                    bc1.setTopic(Topic.GAMES);
                } else {
                    bc1.setTopic(Topic.MOVIES);
                }
                bc1.setCorrectAnswer(resultSet.getInt("correctAnswer"));
                results.add(bc1);
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();

        }
        return results;
    }

    public ArrayList<BinarychoiceQuestion> getBinarychoiceQuestionCollectionByTopic(Topic topic){
        ArrayList<BinarychoiceQuestion> resultsByTopic = new ArrayList<>();
        try(Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/quizDb", "root", "admin123")) {

            Statement stmt = con.createStatement();

            String selectQuestion = "SELECT * FROM binaryQuiz WHERE topic = '" + topic.toString() + "'";

            ResultSet resultSet = stmt.executeQuery(selectQuestion);
            while (resultSet.next()){
                int bcId = resultSet.getInt("id");
                BinarychoiceQuestion bc1 = new BinarychoiceQuestion(bcId);
                bc1.setQuestionText(resultSet.getString("question"));
                String topicString = resultSet.getString("topic");
                if (topicString == "GAME"){
                    bc1.setTopic(Topic.GAMES);
                } else {
                    bc1.setTopic(Topic.MOVIES);
                }
                bc1.setCorrectAnswer(resultSet.getInt("correctAnswer"));
                resultsByTopic.add(bc1);
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();

        }
        return resultsByTopic;
    }

    public ArrayList<MultiplechoiceQuestion> getMultiplechoiceQuestionsCollectionByTopic(Topic topic){
        ArrayList<MultiplechoiceQuestion> results = new ArrayList<>();
        try(Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/quizDb", "root", "admin123")) {

            Statement stmt = con.createStatement();

            String selectQuestion = "SELECT * FROM multichoiceQuiz WHERE topic = '" + topic + "'";

            ResultSet resultSet = stmt.executeQuery(selectQuestion);
            while (resultSet.next()){
                int mcqId = resultSet.getInt("id");
                MultiplechoiceQuestion mcq1 = new MultiplechoiceQuestion(mcqId);
                mcq1.setQuestionText(resultSet.getString("question"));
                String topicString = resultSet.getString("topic");
                if (topicString == "GAME"){
                    mcq1.setTopic(Topic.GAMES);
                } else {
                    mcq1.setTopic(Topic.MOVIES);
                }
                mcq1.setChoice1(resultSet.getString("answerA"));
                mcq1.setChoice2(resultSet.getString("answerB"));
                mcq1.setChoice3(resultSet.getString("answerC"));
                mcq1.setChoice4(resultSet.getString("answerD"));

                mcq1.setCorrectAnswer(resultSet.getInt("correctAnswer"));
                results.add(mcq1);
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return results;
    }

    public boolean updateMovieScore(User user){
        try(Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/quizDb", "root", "admin123")) {

            Statement stmt = con.createStatement();

            String insertSql = "INSERT INTO score(user, score, topic) VALUES ('" + user.getUserName() + "', "
                    + user.getScoreMovie() + ", 'MOVIE')" ;

            stmt.executeUpdate(insertSql);
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateGameScore(User user){
        try(Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/quizDb", "root", "admin123")) {

            Statement stmt = con.createStatement();

            String insertSql = "INSERT INTO score(user, score, topic) VALUES ('" + user.getUserName() + "', "
                    + user.getScoreGame() + ", 'GAME')" ;

            stmt.executeUpdate(insertSql);
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }

}
