package cool.raptor.hourglass.simulation;

import cool.raptor.hourglass.Hourglass;
import cool.raptor.hourglass.models.Particle;

import java.util.List;

public class NoParticlesStopper implements SimulationObserver {

    @Override
    public void simulationDidStart(Simulation simulation) {
        System.out.println("[NoParticlesStopper] Started...");
    }

    @Override
    public void notify(Simulation simulation) {
    }

    @Override
    public void simulationDidFinish(Simulation simulation) {
        Hourglass s = (Hourglass) simulation;
        System.out.println("[NoParticlesStopper] Finished in " + s.getTimeSimulation() + " s.");
    }

    @Override
    public Boolean shouldStopSimulation(Simulation simulation) {
        Hourglass s = (Hourglass) simulation;

        List<Particle> particles = s.getParticles();

        return particles.size() <= 0;
    }
}