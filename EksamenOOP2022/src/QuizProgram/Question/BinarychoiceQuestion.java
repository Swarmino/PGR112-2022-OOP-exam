package QuizProgram.Question;

public class BinarychoiceQuestion implements Quiz {

    String questionText;
    int correctAnswer, id;

    Topic topic;

    public BinarychoiceQuestion(int id) {
        this.id = id;
    }

    public BinarychoiceQuestion(String questionText, int correctAnswer, Topic topic) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.topic = topic;
    }

    @Override
    public int correctAnswer() {
        return correctAnswer;
    }

    @Override
    public String question() {
        return questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
