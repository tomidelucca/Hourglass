package cool.raptor.hourglass.models;

import cool.raptor.hourglass.generator.HourglassStructureGenerator;
import cool.raptor.hourglass.generator.ParticleGenerator;

import java.util.List;

public class Hourglass {

    private List<Particle> structure;
    private List<Particle> particles;

    private Double height;
    private Double holeRadius;
    private Double radius;
    private ParticleConfiguration configuration;

    public Hourglass(Double radius, Double holeRadius, ParticleConfiguration configuration) {
        this.radius = radius;
        this.holeRadius = holeRadius;
        this.height = Math.sqrt(Math.pow(radius, 2) - Math.pow(holeRadius, 2));
        this.configuration = configuration;
    }

    public List<Particle> getStructure() {
        if (structure == null) {
            structure = HourglassStructureGenerator.generate(this);
        }
        return structure;
    }

    public List<Particle> getParticles() {
        if (particles == null) {
            particles = ParticleGenerator.generate(configuration, this);
        }
        return particles;
    }

    public Double getHeight() {
        return height;
    }

    public Double getRadius() {
        return radius;
    }

    public Double getHoleRadius() {
        return holeRadius;
    }

    public ParticleConfiguration getConfiguration() {
        return configuration;
    }

    public Boolean isParticleInside(Particle p) {
        return distanceToCenterTop(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ()) < radius - p.getRadius();
    }

    private double distanceToCenterTop(double x, double y, double z) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z - height, 2));
    }
}
