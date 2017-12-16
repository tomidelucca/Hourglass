package cool.raptor.hourglass.models;

public class Particle {

    private Integer id;
    private Vector position;
    private Vector velocity;
    private Double radius;
    private Double mass;

    public Particle(Vector position) {
        this.position = position;
    }

    public Particle(int id, Vector position, Vector velocity, Double radius, Double mass) {
        this.id = id;
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.mass = mass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Double distanceToParticle(Particle otherParticle) {
        return getPosition().distanceToVector(otherParticle.getPosition()) - getRadius() - otherParticle.getRadius();
    }

    @Override
    public String toString() {
        return "Particle{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", radius=" + radius +
                ", mass=" + mass +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Particle particle = (Particle) o;

        if (radius != null ? !radius.equals(particle.radius) : particle.radius != null) return false;
        if (mass != null ? !mass.equals(particle.mass) : particle.mass != null) return false;
        return id != null ? id.equals(particle.id) : particle.id == null;
    }

    @Override
    public int hashCode() {
        int result = radius != null ? radius.hashCode() : 0;
        result = 31 * result + (mass != null ? mass.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    public String print() {
        return position.getX() + " " + position.getY() + " " + position.getZ() + " " + velocity.getX() + " " + velocity.getY() + " " + velocity.getZ() + " " + radius;
    }
}