package QuizProgram.User;

public class User {

    private int userId;
    private String userName;
    private int scoreMovie;
    private int scoreGame;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, int scoreMovie, int scoreGame) {
        this.userName = userName;
        this.scoreMovie = scoreMovie;
        this.scoreGame = scoreGame;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScoreMovie() {
        return scoreMovie;
    }

    public void setScoreMovie(int scoreMovie) {
        this.scoreMovie = scoreMovie;
    }

    public int getScoreGame() {
        return scoreGame;
    }

    public void setScoreGame(int scoreGame) {
        this.scoreGame = scoreGame;
    }

    public void increaseScoreMovie(){
        scoreMovie++;
    }

    public void increaseScoreGame(){
        scoreGame++;
    }
}
