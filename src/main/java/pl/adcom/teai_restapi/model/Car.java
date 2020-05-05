package pl.adcom.teai_restapi.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class Car {

    @NotNull(message = "id cannot be null")
    private long id;
    @NotNull(message = "mark cannot be null")
    private String mark;
    @NotNull(message = "model cannot be null")
    private String model;
    @NotNull(message = "color cannot be null")
    private Color color;

    public Car() {
    }

    public Car(long id, String mark, String model, Color color) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color=" + color +
                '}';
    }
}
