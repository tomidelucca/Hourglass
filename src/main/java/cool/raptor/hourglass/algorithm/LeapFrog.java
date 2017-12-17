package cool.raptor.hourglass.algorithm;

import cool.raptor.hourglass.models.Particle;
import cool.raptor.hourglass.models.Vector;

public class LeapFrog {

    private static double velocityX(Particle p, double sumForce, double dt) {
        double velocity = p.getPrevVelocity().getX() + (dt/p.getMass())*sumForce;
        return (Math.abs(velocity) > 10)?p.getPrevVelocity().getX():velocity;
    }
    private static double velocityY(Particle p, double sumForce, double dt) {
        double velocity = p.getPrevVelocity().getY() + (dt/p.getMass())*sumForce;
        return (Math.abs(velocity) > 10)?p.getPrevVelocity().getY():velocity;
    }
    private static double velocityZ(Particle p, double sumForce, double dt) {
        double velocity = p.getPrevVelocity().getZ() + (dt/p.getMass())*sumForce;
        return (Math.abs(velocity) > 10)?p.getPrevVelocity().getZ():velocity;
    }

    public static Vector velocity(Particle p, double[] force, double dt) {
        return new Vector(velocityX(p, force[0], dt), velocityY(p, force[1], dt), velocityZ(p, force[2], dt));
    }

    private static double positionX(Particle p, double dt) {
        return p.getPrevPosition().getX() + dt*p.getNextVelocity().getX();
    }

    private static double positionY(Particle p, double dt) {
        return p.getPrevPosition().getY() + dt*p.getNextVelocity().getY();
    }

    private static double positionZ(Particle p, double dt) {
        return p.getPrevPosition().getZ() + dt*p.getNextVelocity().getZ();
    }

    public static Vector position(Particle p, double dt) {
        return new Vector(positionX(p, dt), positionY(p, dt), positionZ(p, dt));
    }
}
