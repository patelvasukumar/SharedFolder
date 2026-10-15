package Controller.Design;

import java.util.Collection;
import Model.Design.Question;
import Model.Design.QuestionStore;
import Model.Exception.GameException;

/**
 * The QuestionController class manages all operations related to
 * Question objects. It acts as a bridge between the UI and the
 * QuestionStore, handling creation, retrieval, validation, and deletion
 * of questions.
 */
public class QuestionController {

	/**
     * Default constructor for QuestionController.
     */
    public QuestionController() {
        super();
    }
    
	 /**
     * Array storing prize amounts for each level.
     */
    private final long[] amounts = {
            100,200,300,500,1000,2000,4000,8000,
            16000,32000,64000,125000,250000,500000,1000000
    };
  
    /**
     * Retrieves all questions from the QuestionStore.
     *
     * @return a collection of Question objects
     */
    public Collection<Question> getQuestions() {
        return QuestionStore.getAllQuestions();
    }

    /**
     * Retrieves a specific question by level.
     *
     * @param level the level of the question (1–15)
     * @return the Question object for the given level
     */
    public Question getQuestion(int level) {
        return QuestionStore.getQuestion(level);
    }

    /**
     * Creates and adds a new question for a specific level.
     *
     * @param level the level of the question
     * @param q1 the first question text
     * @param q2 the second question text
     * @param q1op1 option 1 for question 1
     * @param q1op2 option 2 for question 1
     * @param q1op3 option 3 for question 1
     * @param q1op4 option 4 for question 1
     * @param q2op1 option 1 for question 2
     * @param q2op2 option 2 for question 2
     * @param q2op3 option 3 for question 2
     * @param q2op4 option 4 for question 2
     */
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

    /**
     * Updates an existing question in the QuestionStore.
     *
     * @param q the Question object with updated values
     */
    public void updateQuestion(Question q) {
        QuestionStore.addQuestion(q);
    }

    /**
     * Deletes a question based on its level.
     *
     * @param level the level of the question to remove
     */
    public void deleteQuestion(int level) {
        QuestionStore.removeQuestion(level);
    }

    /**
     * Returns the prize amount for a given level.
     *
     * @param level the level number (1–15)
     * @return the prize amount associated with the level
     */
    public long getPrice(int level) {
        return amounts[level - 1];
    }

    /**
     * Validates that all questions and their options are filled
     * for all levels (1–15). Throws a GameException if any
     * question or option is missing.
     *
     * @throws GameException if validation fails
     */
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