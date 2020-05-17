package hu.elte.szakdolgozat.coordinates;

import java.util.Objects;

public class PointVo {
    private Double x;
    private Double y;

    //generated
    public PointVo(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointVo pointVo = (PointVo) o;
        return Objects.equals(x, pointVo.x) &&
                Objects.equals(y, pointVo.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
