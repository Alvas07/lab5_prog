package data;

import exceptions.ValidationException;
import utils.Validator;

public class Coordinates {
    private float x;
    private Long y; // Максимальное значение поля: 332, Поле не может быть null

    public Coordinates(float x, Long y) {
        this.x = x;
        try {
            this.y = Validator.validateCoordinatesY(y);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
