package Utils;
import xjtlu.cpt111.assignment.quiz.model.Question;
import xjtlu.cpt111.assignment.quiz.util.IOUtilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionUtil {
    public void shuffleQuestions() {
        Collections.shuffle(questionsList);
    }

    public List<Question> getQuestionsList() {
        return questionsList;
    }


    private List<Question> questionsList = new ArrayList<>();
    public void loadQuestions(String QUESTIONS_BANK_PATH) {
        try {
            System.out.println("===\n=== read questions - started\n===");
            Question[] questions = IOUtilities.readQuestions(QUESTIONS_BANK_PATH);
            if (questions == null || questions.length == 0) {
                System.out.println("Questions is empty!");
            } else {
                for (Question question : questions) {
                    questionsList.add(question);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shuffleQuestions();
            System.out.println("===\n=== read questions - complete\n===");
        }
    }

}