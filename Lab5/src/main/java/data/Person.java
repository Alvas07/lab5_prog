package data;

public class Person {
    private Float height; // Поле не может быть null, Значение поля должно быть больше 0
    private int weight; // Значение поля должно быть больше 0
    private String passportID; // Длина строки не должна быть больше 28, Поле может быть null
    private Location location; // Поле может быть null

    public Person(Float height, int weight, String passportID, Location location) {
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
        this.location = location;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
