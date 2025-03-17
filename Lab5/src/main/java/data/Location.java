package data;

import exceptions.ValidationException;
import utils.Validator;

public class Location {
    private Long x; // Поле не может быть null
    private Long y; // Поле не может быть null
    private Integer z; // Поле не может быть null

    public Location(Long x, Long y, Integer z) {
        try {
            this.x = Validator.validateLocationX(x);
            this.y = Validator.validateLocationY(y);
            this.z = Validator.validateLocationZ(z);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }
}
