package Model.Design;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String gender;
    private String nationality;
    private transient int currentPoint;
    private int historicPoint;

    public Player(int id, String name, String gender, String nationality) {
        this(id, name, gender, nationality, 0, 0);
    }

    public Player(int id, String name, String gender, String nationality,
                  int currentPoint, int historicPoint) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.nationality = nationality;
        this.currentPoint = currentPoint;
        this.historicPoint = historicPoint;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getNationality() { return nationality; }
    public int getCurrentPoint() { return currentPoint; }
    public int getHistoricPoint() { return historicPoint; }

    public void setName(String name) { this.name = name; }
    public void setGender(String gender) { this.gender = gender; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setCurrentPoint(int currentPoint) { this.currentPoint = currentPoint; }
    public void setHistoricPoint(int historicPoint) { this.historicPoint = historicPoint; }
}