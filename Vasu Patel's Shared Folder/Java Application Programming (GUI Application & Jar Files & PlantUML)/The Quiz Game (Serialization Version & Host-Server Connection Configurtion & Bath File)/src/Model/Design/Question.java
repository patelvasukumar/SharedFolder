package Model.Design;

/**
 * Represents a question used in the game.
 * Each question contains two versions (e.g., India/Canada),
 * along with their respective options and prize value.
 */
public class Question {

	/** 
	 * Level number of the question
	 */
    private int level;

    /** 
     * First version of the question
     */
    private String question1;

    /** 
     * Second version of the question 
     */
    private String question2;

    /** 
     * Prize amount for this question 
     */
    private long price;

    /** 
     * Options for question 1 
     */
    private String q1op1;
    private String q1op2;
    private String q1op3;
    private String q1op4;

    /** 
     * Options for question 2 
     */
    private String q2op1;
    private String q2op2;
    private String q2op3;
    private String q2op4;

    /**
     * Constructs a Question object with all details.
     *
     * @param level question level
     * @param question1 first version of the question
     * @param question2 second version of the question
     * @param price prize value
     * @param q1op1 option 1 for question 1
     * @param q1op2 option 2 for question 1
     * @param q1op3 option 3 for question 1
     * @param q1op4 option 4 for question 1
     * @param q2op1 option 1 for question 2
     * @param q2op2 option 2 for question 2
     * @param q2op3 option 3 for question 2
     * @param q2op4 option 4 for question 2
     */
    public Question(int level,
                    String question1,
                    String question2,
                    long price,

                    String q1op1, String q1op2, String q1op3, String q1op4,
                    String q2op1, String q2op2, String q2op3, String q2op4) {

        this.level = level;
        this.question1 = question1;
        this.question2 = question2;
        this.price = price;

        this.q1op1 = q1op1;
        this.q1op2 = q1op2;
        this.q1op3 = q1op3;
        this.q1op4 = q1op4;

        this.q2op1 = q2op1;
        this.q2op2 = q2op2;
        this.q2op3 = q2op3;
        this.q2op4 = q2op4;
    }

    /**
     * Returns the question level.
     * @return question level
     */
    public int getLevel() { 
    	return level; 
    	}

    /**
     * Returns the first question text.
     * @return first question text
     */
    public String getQuestion1() { 
    	return question1; 
    	}

    /**
     * Returns the second question text.
     * @return second question text
     */
    public String getQuestion2() { 
    	return question2; 
    	}

    /**
     * Returns the prize amount.
     * @return prize amount
     */
    public long getPrice() {
    	return price; 
    	}

    /**
     * Returns option 1 for question 1.
     * @return option 1 for question 1
     */
    public String getQ1op1() { 
    	return q1op1; 
    	}

    /**
     * Returns option 2 for question 1.
     * @return option 2 for question 1
     */
    public String getQ1op2() { 
    	return q1op2; 
    	}

    /**
     * Returns option 3 for question 1.
     * @return option 3 for question 1
     */
    public String getQ1op3() { 
    	return q1op3; 
    	}

    /**
     * Returns option 4 for question 1.
     * @return option 4 for question 1
     */
    public String getQ1op4() { 
    	return q1op4; 
    	}

    /**
     * Returns option 1 for question 2.
     * @return option 1 for question 2
     */
    public String getQ2op1() { 
    	return q2op1; 
    	}

    /**
     * Returns option 2 for question 2.
     * @return option 2 for question 2
     */
    public String getQ2op2() { 
    	return q2op2; 
    	}

    /**
     * Returns option 3 for question 2.
     * @return option 3 for question 2
     */
    public String getQ2op3() { 
    	return q2op3; 
    	}

    /**
     * Returns option 4 for question 2.
     * @return option 4 for question 2
     */
    public String getQ2op4() { return q2op4; }

    /**
     * Updates the first question text.
     * @param question1 new question text
     */
    public void setQuestion1(String question1) { 
    	this.question1 = question1; 
    	}

    /**
     * Updates the second question text.
     * @param question2 new question text
     */
    public void setQuestion2(String question2) { 
    	this.question2 = question2; 
    	}
}