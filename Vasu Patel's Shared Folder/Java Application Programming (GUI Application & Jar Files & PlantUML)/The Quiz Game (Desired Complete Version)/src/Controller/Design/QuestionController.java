package Controller.Design;

import java.util.Collection;
import Model.Design.Question;
import Model.Design.QuestionStore;
import Model.Exception.GameException;

public class QuestionController {

    private final long[] amounts = {
            100,200,300,500,1000,2000,4000,8000,
            16000,32000,64000,125000,250000,500000,1000000
    };
  
    public Collection<Question> getQuestions() {
        return QuestionStore.getAllQuestions();
    }

    public Question getQuestion(int level) {
        return QuestionStore.getQuestion(level);
    }

    public void addQuestion(
            int level,
            String q1,
            String q2,

            String q1op1, String q1op2, String q1op3, String q1op4,
            String q2op1, String q2op2, String q2op3, String q2op4
    ) {

        long price = amounts[level - 1];

        Question q = new Question(
                level,
                q1,
                q2,
                price,

                q1op1, q1op2, q1op3, q1op4,
                q2op1, q2op2, q2op3, q2op4
        );

        QuestionStore.addQuestion(q);
    }

    public void updateQuestion(Question q) {
        QuestionStore.addQuestion(q);
    }

    public void deleteQuestion(int level) {
        QuestionStore.removeQuestion(level);
    }

    public long getPrice(int level) {
        return amounts[level - 1];
    }

    public void validateAllQuestions() throws GameException {

        for (int level = 1; level <= 15; level++) {

            Question q = QuestionStore.getQuestion(level);

            if (q == null ||
                q.getQuestion1() == null || q.getQuestion1().isEmpty() ||
                q.getQuestion2() == null || q.getQuestion2().isEmpty() ||

                q.getQ1op1() == null || q.getQ1op1().isEmpty() ||
                q.getQ1op2() == null || q.getQ1op2().isEmpty() ||
                q.getQ1op3() == null || q.getQ1op3().isEmpty() ||
                q.getQ1op4() == null || q.getQ1op4().isEmpty() ||

                q.getQ2op1() == null || q.getQ2op1().isEmpty() ||
                q.getQ2op2() == null || q.getQ2op2().isEmpty() ||
                q.getQ2op3() == null || q.getQ2op3().isEmpty() ||
                q.getQ2op4() == null || q.getQ2op4().isEmpty()
            ) {

                GameException.raise(
                        "You must complete ALL questions and options for levels (1 - 15) before starting Play Mode."
                );
            }
        }
    }
}