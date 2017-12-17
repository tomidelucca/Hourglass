package cool.raptor.hourglass.models;

public class Vector {

    private Double x;
    private Double y;
    private Double z;

    public Vector(Double x, Double y) {
        this(x,y, 0.0);
    }

    public Vector(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public Double distanceToVector(Vector otherVector) {
        return Math.sqrt(Math.pow(getX()-otherVector.getX(), 2) +
                Math.pow(getY()-otherVector.getY(), 2) +
                Math.pow(getZ()-otherVector.getZ(), 2));
    }

    public double mod() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public static Vector zero() {
        return new Vector(0.0, 0.0, 0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;

        if (!x.equals(vector.x)) return false;
        if (!y.equals(vector.y)) return false;
        return z != null ? z.equals(vector.z) : vector.z == null;

    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        result = 31 * result + (z != null ? z.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(" + getX() + "; " + getY() + "; " + getZ() + ")";
    }

    public static Vector rest(Vector vector1, Vector vector2) {
        return new Vector(vector1.getX() - vector2.getX(), vector1.getY() - vector2.getY(), vector1.getZ() - vector2.getZ());
    }
}
