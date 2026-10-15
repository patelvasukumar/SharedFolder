package Model.Design;

import java.util.Collection;
import java.util.Hashtable;

public class QuestionStore {

    private static Hashtable<Integer, Question> questions = DefaultQuestionBank.loadQuestions();

    public static synchronized void reloadQuestions() {
        questions = DefaultQuestionBank.loadQuestions();
    }

    public static synchronized void addQuestion(Question q) {
        questions.put(q.getLevel(), q);
    }

    public static synchronized Question getQuestion(int level) {
        return questions.get(level);
    }

    public static synchronized void removeQuestion(int level) {
        questions.remove(level);
    }

    public static synchronized Collection<Question> getAllQuestions() {
        return questions.values();
    }
}