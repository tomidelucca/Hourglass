package cool.raptor.hourglass;

import cool.raptor.hourglass.algorithm.LeapFrog;
import cool.raptor.hourglass.force.ForceParticles;
import cool.raptor.hourglass.method.CellIndexMethod3D;
import cool.raptor.hourglass.models.Hourglass;
import cool.raptor.hourglass.models.Particle;
import cool.raptor.hourglass.models.ParticleConfiguration;
import cool.raptor.hourglass.simulation.Simulation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class HourglassSimulation extends Simulation {

    double SIMULATION_TIME = 5;
    double HG_RADIUS = 1;
    double P_RADIUS = 1;
    double HG_H_RADIUS = 1/5;
    int MAX_PARTICLES = 200;
    double MASS = 1E-2;

    private static double SIMULATION_DT = 5E-5;
    private static double GRAVITY = -10;
    private static double KN = 1E5;
    private static double GAMMA = 10;

    private Hourglass hourglass = null;

    private double timeSimulation;

    public static double getSimulationDt() {
        return SIMULATION_DT;
    }

    @Override
    public Boolean simulate() {
        initialize();
        timeSimulation = SIMULATION_TIME;

        // Particle's neighbour
        Map<Particle, Set<Particle>> neighbours;

        startSimulation();
        while (!shouldStopSimulation()) {

            neighbours = hourglass.getNeighbours();
            hourglass.updateWalls();

            for (Particle p : hourglass.getParticles()) {
                Set<Particle> pneigh = neighbours.get(p);

                p.setPrevPosition(p.getPosition());
                p.setPrevVelocity(p.getVelocity());

                p.setPosition(p.getNextPosition());
                p.setPrevVelocity(p.getNextVelocity());

                p.setVelocity(p.getNextVelocity());

                if (!p.isFixed() && p.isVisible()) {
                    double[] force = ForceParticles.total(p, pneigh, KN, GAMMA);
                    double[] sumForce = {0, 0, 0};

                    // Forces from other particles
                    sumForce[0] += force[0]; //x
                    sumForce[1] += force[1]; //y
                    sumForce[2] += force[2]; //z

                    // Gravity
                    sumForce[2] += p.getMass() * GRAVITY; //z

                    // Update position and velocity
                    p.setNextVelocity(LeapFrog.velocity(p, sumForce, SIMULATION_DT));
                    p.setNextPosition(LeapFrog.position(p, SIMULATION_DT));
                }
            }

            timeSimulation -= SIMULATION_DT;

            updateObservers();
        }

        updateObservers();

        finishSimulation();

        return Boolean.TRUE;
    }

    @Override
    public void reset() {
        throw new NotImplementedException();
    }

    @Override
    public void initialize() {
        ParticleConfiguration config = new ParticleConfiguration(P_RADIUS, MASS, MAX_PARTICLES);
        hourglass = new Hourglass(HG_RADIUS, HG_H_RADIUS, config);
    }

    public Hourglass getHourglass() {
        return hourglass;
    }

    public double getTimeSimulation() {
        return timeSimulation;
    }

}
