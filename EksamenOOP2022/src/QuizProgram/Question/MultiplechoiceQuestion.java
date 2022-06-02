package QuizProgram.Question;

public class MultiplechoiceQuestion implements Quiz {

    String questionText, choice1, choice2, choice3, choice4;
    int correctAnswer, id;

    Topic topic;

    public MultiplechoiceQuestion(int id) {
        this.id = id;
    }

    public MultiplechoiceQuestion(String questionText, String choice1, String choice2, String choice3, String choice4, int correctAnswer, Topic topic) {
        this.questionText = questionText;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
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

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
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
