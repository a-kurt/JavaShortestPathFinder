import java.util.Objects;

/**
 * This class represents y (row), x (column) index of the tile in the grid.
 */
public class Coordinate {
    /**
     * Column index.
     */
    private int x;

    /**
     * Row index.
     */
    private int y;

    /**
     * Constructs coordinate object.
     * @param y Row index to be initialized.
     * @param x Column index to be initialized.
     */
    public Coordinate(int y, int x) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("ERROR! INVALID COORDINATES!");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Returns x index of the coordinate.
     * @return x index of the coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns y index of the coordinate.
     * @return y index of the coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Compares another coordinate.
     * @param obj Another coordinate to be compared.
     * @return true if they have equal indexes, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        return x == other.x && y == other.y;
    }

    /**
     * Calculates hash code based on their x and y values.
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Returns string in the proper format.
     * @return string in the proper format.
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder("Coordinate [");
        sb.append("y=").append(y);
        sb.append(", x=").append(x);
        sb.append(']');
        return sb.toString();
    }
}
