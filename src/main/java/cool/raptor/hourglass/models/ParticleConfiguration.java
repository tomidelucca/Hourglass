package cool.raptor.hourglass.models;

public class ParticleConfiguration {

    private Double radius;
    private Double mass;
    private Integer maxParticles;

    public ParticleConfiguration(Double radius, Double mass, Integer maxParticles) {
        this.radius = radius;
        this.mass = mass;
        this.maxParticles = maxParticles;
    }

    public Double getRadius() {
        return radius;
    }

    public Double getMass() {
        return mass;
    }

    public Integer getMaxParticles() {
        return maxParticles;
    }
}
