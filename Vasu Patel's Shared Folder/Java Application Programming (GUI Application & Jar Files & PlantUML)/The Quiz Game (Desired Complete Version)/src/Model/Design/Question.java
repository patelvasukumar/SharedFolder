package Model.Design;

public class Question {

    private int level;

    private String question1;
    private String question2;

    private long price;

    private String q1op1; 
    private String q1op2;
    private String q1op3;
    private String q1op4;

    private String q2op1; 
    private String q2op2;
    private String q2op3;
    private String q2op4;

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

    public int getLevel() { return level; }

    public String getQuestion1() { return question1; }

    public String getQuestion2() { return question2; }

    public long getPrice() { return price; }

    public String getQ1op1() { return q1op1; }
    public String getQ1op2() { return q1op2; }
    public String getQ1op3() { return q1op3; }
    public String getQ1op4() { return q1op4; }

    public String getQ2op1() { return q2op1; }
    public String getQ2op2() { return q2op2; }
    public String getQ2op3() { return q2op3; }
    public String getQ2op4() { return q2op4; }

    public void setQuestion1(String question1) { this.question1 = question1; }

    public void setQuestion2(String question2) { this.question2 = question2; }
}