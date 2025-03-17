package data;

import exceptions.ValidationException;
import utils.Validator;

import java.time.LocalDate;

public class Ticket {
    private int id; // Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private LocalDate creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; // Значение поля должно быть больше 0
    private TicketType type; // Поле не может быть null
    private Person person; // Поле может быть null

    public Ticket(int id, String name, Coordinates coordinates, LocalDate creationDate, float price, TicketType type, Person person) {
        this.id = id;
        this.creationDate = creationDate;
        this.person = person;
        try {
            this.name = Validator.validateName(name);
            this.coordinates = Validator.validateCoordinates(coordinates);
            this.price = Validator.validatePrice(price);
            this.type = Validator.validateType(type);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
