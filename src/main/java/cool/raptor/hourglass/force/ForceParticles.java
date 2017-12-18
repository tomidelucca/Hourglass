package cool.raptor.hourglass.force;

import cool.raptor.hourglass.models.Particle;
import cool.raptor.hourglass.models.Vector;

import java.util.Set;

public class ForceParticles {

    public static double[] total(Particle p, Set<Particle> others, double kn, double gamma) {
        double forceX = 0;
        double forceY = 0;
        double forceZ = 0;
        double enx;
        double eny;
        double enz;
        double normalForceValue;
        double overlapValue;
        Vector speedDelta;
        Vector positionDelta;

        for(Particle particle: others) {
            if(particle.isVisible()) {
                positionDelta = Vector.rest(particle.getPrevPosition(),p.getPrevPosition());
                enx = (positionDelta.getX()) / positionDelta.mod();
                eny = (positionDelta.getY()) / positionDelta.mod();
                enz = (positionDelta.getZ()) / positionDelta.mod();

                overlapValue = overlap(p, particle);
                speedDelta = Vector.rest(p.getPrevVelocity(),particle.getPrevVelocity());

                normalForceValue = (- kn * overlapValue) - gamma * (speedDelta.getX() * enx + speedDelta.getY() * eny + speedDelta.getZ() * enz);

                forceX += normalForceValue * enx;
                forceY += normalForceValue * eny;
                forceZ += normalForceValue * enz;
            }
        }

        return new double[]{forceX, forceY, forceZ};
    }

    private static double overlap(Particle p, Particle other) {
        double ol = p.getRadius() + other.getRadius() - Vector.rest(other.getPrevPosition(),p.getPrevPosition()).mod();
        return (ol < 0)? 0 : ol;
    }
}
