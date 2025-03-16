package data;

public class Coordinates {
    private float x;
    private Long y; // Максимальное значение поля: 332, Поле не может быть null

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
