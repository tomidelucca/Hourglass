package cool.raptor.hourglass.simulation;

import cool.raptor.hourglass.HourglassSimulation;
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
        HourglassSimulation s = (HourglassSimulation) simulation;
        System.out.println("[NoParticlesStopper] Finished in " + s.getTimeSimulation() + " s.");
    }

    @Override
    public Boolean shouldStopSimulation(Simulation simulation) {
        HourglassSimulation s = (HourglassSimulation) simulation;

        List<Particle> particles = s.getParticles();

        return particles.size() <= 0;
    }
}
