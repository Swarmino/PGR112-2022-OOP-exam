-- Only used for testing purposes. Not meant to be used to actually create the database.

-- Movie Questions --
INSERT INTO binaryQuiz(question, correctAnswer, topic)
VALUES ('Did the actor Chris Pratt voice the character of Garfield in the hit movie Garfield from 2004?',
        'NO',
        'MOVIE');

INSERT INTO binaryQuiz(question, correctAnswer, topic)
VALUES ('Does the actor Nick Offerman own a woodworking shop?',
        'YES',
        'MOVIE');

INSERT INTO binaryQuiz(question, correctAnswer, topic)
VALUES ('Is the movie Uncharted based on a video game?',
        'YES',
        'MOVIE');

INSERT INTO binaryQuiz(question, correctAnswer, topic)
VALUES ('Did the character of Iceman die in the movie Top Gun?',
        'No',
        'MOVIE');

INSERT INTO binaryQuiz(question, correctAnswer, topic)
VALUES ('Was the line “I am your father” ever said on the set of the empire strikes back?',
        'No',
        'MOVIE');


-- Games questions --
INSERT INTO multichoiceQuiz(question, answerA, answerB, answerC, answerD, correctAnswer, topic)
VALUES ('Who created the character of Super Mario?', 'A. Shigeru Miyamoto', 'B. Bob Hoskins', 'C. Eiji Aonuma',
        'D. Chris Pratt', 'A. Shigeru Miyamoto', 'GAMES');

INSERT INTO multichoiceQuiz(question, answerA, answerB, answerC, answerD, correctAnswer, topic)
VALUES ('What is the best-selling video game of all time?', 'A. Tetris', 'B. Wii Sports', 'C. Minecraft',
        'D. Grand Theft Auto V', 'C. Minecraft', 'GAMES');

INSERT INTO multichoiceQuiz(question, answerA, answerB, answerC, answerD, correctAnswer, topic)
VALUES ('Which of these games were released in the year 2007?', 'A. Bioshock', 'B. Minecraft', 'C. Terraria',
        'D. New Super Mario bros', 'A. Bioshock', 'GAMES');

INSERT INTO multichoiceQuiz(question, answerA, answerB, answerC, answerD, correctAnswer, topic)
VALUES ('What color is Mario´s hat?', 'A. Green', 'B. Red', 'C. Yellow', 'D. Pink', 'B. Red', 'GAMES');

INSERT INTO multichoiceQuiz(question, answerA, answerB, answerC, answerD, correctAnswer, topic)
VALUES ('What is currently the most powerful console on the market as of 2022?', 'A. Xbox Series X', 'B. Playstation 5',
        'C. Nintendo Switch', 'D. Valve Steam Deck', 'A. Xbox Series X', 'GAMES');