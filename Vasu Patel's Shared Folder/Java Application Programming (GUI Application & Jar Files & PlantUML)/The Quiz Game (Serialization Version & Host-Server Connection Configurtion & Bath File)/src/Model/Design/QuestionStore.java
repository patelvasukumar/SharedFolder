package Model.Design;

import java.util.Collection;
import java.util.Hashtable;

/**
 * Manages storage and access of Question objects.
 * Provides methods to add, remove, retrieve, and reload questions.
 */
public class QuestionStore {

	/** 
	 * Stores all questions mapped by their level 
	 */
    private static Hashtable<Integer, Question> questions = DefaultQuestionBank.loadQuestions();

    /**
     * Private constructor to prevent instantiation.
     */
    private QuestionStore() {}
    
    /**
     * Reloads all questions from the default question bank.
     */
    public static synchronized void reloadQuestions() {
        questions = DefaultQuestionBank.loadQuestions();
    }

    /**
     * Adds a new question to the store.
     *
     * @param q question to be added
     */
    public static synchronized void addQuestion(Question q) {
        questions.put(q.getLevel(), q);
    }

    /**
     * Retrieves a question based on its level.
     *
     * @param level question level
     * @return the corresponding question or null if not found
     */
    public static synchronized Question getQuestion(int level) {
        return questions.get(level);
    }

    /**
     * Removes a question from the store.
     *
     * @param level level of the question to remove
     */
    public static synchronized void removeQuestion(int level) {
        questions.remove(level);
    }

    /**
     * Returns all available questions.
     *
     * @return collection of all questions
     */
    public static synchronized Collection<Question> getAllQuestions() {
        return questions.values();
    }
}