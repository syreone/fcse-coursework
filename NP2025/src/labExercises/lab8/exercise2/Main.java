package labExercises.lab8.exercise2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TriviaQuestion {

    public static final int TRUE_FALSE = 0;
    public static final int FREE_FORM = 1;

    private String question;
    private String answer;
    private int points;
    private int type;

    public TriviaQuestion(String question, String answer, int points, int type) {
        this.question = question;
        this.answer = answer;
        this.points = points;
        this.type = type;
    }

    public void print(int index) {
        System.out.println("Question " + (index + 1) + ".  " + points + " points.");
        System.out.println(question);
        if (type == TRUE_FALSE) {
            System.out.println("Enter 'T' for true or 'F' for false.");
        }
    }

    public boolean isCorrect(String input) {
        if (type == TRUE_FALSE) {
            return input.toUpperCase().charAt(0) == answer.charAt(0);
        }
        return input.equalsIgnoreCase(answer);
    }

    public int getPoints() {
        return points;
    }

    public String getAnswer() {
        return answer;
    }
}

class TriviaData {

    private List<TriviaQuestion> questions = new ArrayList<>();

    public void add(TriviaQuestion q) {
        questions.add(q);
    }

    public TriviaQuestion get(int index) {
        return questions.get(index);
    }

    public int size() {
        return questions.size();
    }
}

public class Main {

    private TriviaData data = new TriviaData();

    public Main() {
        data.add(new TriviaQuestion(
                "The possession of more than two sets of chromosomes is termed?",
                "polyploidy", 3, TriviaQuestion.FREE_FORM));

        data.add(new TriviaQuestion(
                "Erling Kagge skiied into the north pole alone on January 7, 1993.",
                "F", 1, TriviaQuestion.TRUE_FALSE));

        data.add(new TriviaQuestion(
                "1997 British band that produced 'Tub Thumper'",
                "Chumbawumba", 2, TriviaQuestion.FREE_FORM));

        data.add(new TriviaQuestion(
                "I am the geometric figure most like a lost parrot",
                "polygon", 2, TriviaQuestion.FREE_FORM));

        data.add(new TriviaQuestion(
                "Generics were introducted to Java starting at version 5.0.",
                "T", 1, TriviaQuestion.TRUE_FALSE));
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (int i = 0; i < data.size(); i++) {
            TriviaQuestion q = data.get(i);
            q.print(i);

            String input = scanner.nextLine();

            if (q.isCorrect(input)) {
                System.out.println("That is correct!  You get " + q.getPoints() + " points.");
                score += q.getPoints();
            } else {
                System.out.println("Wrong, the correct answer is " + q.getAnswer());
            }

            System.out.println("Your score is " + score);
        }

        System.out.println("Game over!  Thanks for playing!");
    }

    public static void main(String[] args) {
        new Main().play();
    }
}

