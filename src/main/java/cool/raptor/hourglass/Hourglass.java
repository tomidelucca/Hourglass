package cool.raptor.hourglass;

import cool.raptor.hourglass.generator.ParticleGenerator;
import cool.raptor.hourglass.models.Particle;
import cool.raptor.hourglass.simulation.Simulation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class Hourglass extends Simulation {

    private static double SIMULATION_TIME = 1000;
    private static double SIMULATION_DT = 1E-4;
    private static double ANIMATION_DT = 1E-1;

    private double HG_HEIGHT = 5;
    private double HG_RADIUS = 1;
    private double D = 1;
    private double MASS = 1E-2;

    private List<Particle> particles = null;

    private double timeSimulation = SIMULATION_TIME;
    private double timeAnimation = ANIMATION_DT;

    @Override
    public Boolean simulate() {
        initialize();

        startSimulation();
        while (!shouldStopSimulation()) {

            //TODO simulate something

        }

        finishSimulation();

        return Boolean.TRUE;
    }

    @Override
    public void reset() {
        throw new NotImplementedException();
    }

    @Override
    public void initialize() {
        //Genero las particulas
        List<Particle> particles = ParticleGenerator.generate(HG_HEIGHT, HG_RADIUS, D, MASS);

        //TODO generar el hourglass


        setParticles(particles);
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

    public double getTimeSimulation() {
        return timeSimulation;
    }

}
