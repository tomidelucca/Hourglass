package cool.raptor.hourglass.models;

import cool.raptor.hourglass.generator.HourglassStructureGenerator;

import java.util.List;

public class Hourglass {

    private List<Particle> structure;
    private Double height;
    private Double holeRadius;
    private Double radius;

    public Hourglass(Double radius, Double holeRadius) {
        this.radius = radius;
        this.holeRadius = holeRadius;
        this.height = Math.sqrt(Math.pow(radius, 2) - Math.pow(holeRadius, 2));
    }

    public List<Particle> getStructure() {
        if (structure == null) {
            structure = HourglassStructureGenerator.generate(this);
        }
        return structure;
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

    public Boolean isParticleInside(Particle p) {
        return distanceToCenterTop(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ()) < radius - p.getRadius();
    }

    private double distanceToCenterTop(double x, double y, double z) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z - height, 2));
    }
}
