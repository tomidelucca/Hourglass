package cool.raptor.hourglass;

import cool.raptor.hourglass.generator.HourglassStructureGenerator;
import cool.raptor.hourglass.generator.ParticleGenerator;
import cool.raptor.hourglass.models.Hourglass;
import cool.raptor.hourglass.models.Particle;
import cool.raptor.hourglass.simulation.Simulation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HourglassSimulation extends Simulation {

    private static double SIMULATION_TIME = 1000;
    private static double SIMULATION_DT = 1E-6;
    private static double ANIMATION_DT = 2 * 1E-2;

    private static double HG_HEIGHT = 5;
    private static double HG_RADIUS = 1;
    private static int M = 19;
    private static double RC = 0.2;
    private static double D = 1;
    private static double MASS = 1E-2;
    private static double GRAVITY = -10;
    private static double KN = 1E-4;
    private static double GAMMA = 100;

    private List<Particle> particles = null;
    private Hourglass hourglass = null;

    private double timeSimulation = SIMULATION_TIME;
    private double timeAnimation = ANIMATION_DT;

    @Override
    public Boolean simulate() {
        initialize();

        //Particula y sus vecinas
        Map<Particle, Set<Particle>> map;

        Particle p;
        Set<Particle> setp;
        double[] force;
        double[] sumForce = {0, 0, 0};

        /*startSimulation();
        while (!shouldStopSimulation()) {

            //TODO fixed particles stuff

            map = CellIndexMethod3D.neighbours(particles, HG_HEIGHT, M, RC);

            for(Map.Entry<Particle, Set<Particle>> entry : map.entrySet()) {

                p = entry.getKey();
                setp = entry.getValue();

                //Leap Frog Algorithm part-1
                p.setPrevPosition(p.getPosition());
                p.setPrevVelocity(p.getVelocity());

                p.setPosition(p.getNextPosition());
                p.setPrevVelocity(p.getNextVelocity());

                p.setVelocity(p.getNextVelocity());

                if (!p.isFixed() && p.isVisible()) {
                    force = ForceParticles.total(p, setp, KN, GAMMA);
                    sumForce[0] += force[0]; //x
                    sumForce[1] += force[1]; //y
                    sumForce[2] += force[2]; //z

                    /*
                    force = ForceWall.total(p, walls, KN, KT);      //TODO fix to add wall forces
                    sumForce[0] += force[0]; //x
                    sumForce[1] += force[1]; //y
                    sumForce[2] += force[2]; //z


                    sumForce[2] += p.getMass() * GRAVITY; //z

                    //Leap Frog Algorithm part-2
                    p.setNextVelocity(LeapFrog.velocity(p, sumForce, SIMULATION_DT));
                    p.setNextPosition(LeapFrog.position(p, SIMULATION_DT));
                }

                sumForce[0] = 0;
                sumForce[1] = 0;
                sumForce[2] = 0;
            }

            timeSimulation -= SIMULATION_DT;

            updateObservers();
        }*/

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
        hourglass = new Hourglass(HG_RADIUS, HG_RADIUS/5);
        particles = ParticleGenerator.generate(HG_RADIUS/2, MASS, hourglass, 300);
    }

    public static double getSimulationDt() {
        return SIMULATION_DT;
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public void setParticles(List<Particle> particles) {
        this.particles = particles;
    }

    public Hourglass getHourglass() {
        return hourglass;
    }

    public double getTimeSimulation() {
        return timeSimulation;
    }

}
