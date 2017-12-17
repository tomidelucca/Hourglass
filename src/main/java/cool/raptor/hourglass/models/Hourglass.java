package cool.raptor.hourglass.models;

import cool.raptor.hourglass.generator.HourglassStructureGenerator;
import cool.raptor.hourglass.generator.ParticleGenerator;
import cool.raptor.hourglass.method.CellIndexMethod3D;

import java.util.*;

public class Hourglass {

    private List<Particle> structure;
    private List<Particle> particles;
    private Map<Particle, Set<Particle>> neighbours;

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

    public Map<Particle, Set<Particle>> getNeighbours() {
        neighbours = CellIndexMethod3D.neighbours(getParticles(), height, 19, 0.2);
        return neighbours;
    }

    public void updateWalls() {
        for (Particle p : getParticles()) {
            List<Particle> walls = getWallPartciles(p);
            neighbours.get(p).addAll(walls);
        }
    }

    private List<Particle> getWallPartciles(Particle particle) {
        List<Particle> ret = new ArrayList<>();
        Particle p = null;

        int sign = particle.getPosition().getZ() >= 0 ? 1 : -1;
        double bound = particle.getPosition().getZ() >= 0 ? height : -height;

        if (sign * (particle.getPosition().getZ() + sign * particle.getRadius()) > sign * bound) {

            p = new Particle(new Vector(particle.getPosition().getX(), particle.getPosition().getY(), bound + sign * particle.getRadius()),
                             Vector.zero(),particle.getRadius(), particle.getMass(),true);

            ret.add(p);
        }

        double levelZeroRadius = Math.sqrt(Math.pow(getRadius(), 2) - Math.pow(bound, 2));
        double distanceToVerticalLine = Math.sqrt(Math.pow(particle.getPosition().getX(), 2) + Math.pow(particle.getPosition().getY(), 2));
        double d = sign == 1 ? distanceToCenterTop(particle) : distanceToCenterBottom(particle);
        if (distanceToVerticalLine > levelZeroRadius) {
            if (d > (getRadius() - particle.getRadius()) && d < (getRadius() + particle.getRadius())) {
                double vecX = particle.getPosition().getX();
                double vecY = particle.getPosition().getY();
                double vecZ = particle.getPosition().getZ() - bound;

                double magnitude = new Vector(vecX, vecY, vecZ).mod();

                double normVecX = vecX / magnitude;
                double normVecY = vecY / magnitude;
                double normVecZ = vecZ / magnitude;

                double x = normVecX * (getRadius() + particle.getRadius());
                double y = normVecY * (getRadius() + particle.getRadius());
                double z = normVecZ * (getRadius() + particle.getRadius());

                p = new Particle(new Vector(x, y, z + bound),
                        Vector.zero(),particle.getRadius(), particle.getMass(),true);
                ret.add(p);
            }
        } else if (distanceToVerticalLine + particle.getRadius() > levelZeroRadius) {
            double vecX = particle.getPosition().getX();
            double vecY = particle.getPosition().getZ();
            double vecZ = 0;

            double magnitude = new Vector(vecX, vecY, vecZ).mod();

            double normVecX = vecX / magnitude;
            double normVecY = vecY / magnitude;

            double x = normVecX * levelZeroRadius;
            double y = normVecY * levelZeroRadius;

            p = new Particle(new Vector(x, y, 0.0), Vector.zero(), 0.0, Double.MAX_VALUE,true);

            // Check if point inside particle
            if (p.distanceToParticle(particle) < particle.getRadius()) {
                ret.add(p);
            }

        }
        return ret;
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

    private double distanceToCenterTop(Particle p) {
        return distanceToCenterTop(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ());
    }

    private double distanceToCenterBottom(Particle p) {
        return distanceToCenterBottom(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ());
    }

    private double distanceToCenterTop(double x, double y, double z) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z - height, 2));
    }

    private double distanceToCenterBottom(double x, double y, double z) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z + height, 2));
    }

}
