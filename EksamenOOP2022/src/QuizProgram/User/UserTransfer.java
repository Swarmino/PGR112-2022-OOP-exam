package QuizProgram.User;

public class UserTransfer {
    int userID;
    String userName;
    int scoreGame;
    int scoreMovie;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScoreGame() {
        return scoreGame;
    }

    public void setScoreGame(int scoreGame) {
        this.scoreGame = scoreGame;
    }

    public int getScoreMovie() {
        return scoreMovie;
    }

    public void setScoreMovie(int scoreMovie) {
        this.scoreMovie = scoreMovie;
    }
}
