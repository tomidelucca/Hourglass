package cool.raptor.hourglass.simulation;

import cool.raptor.hourglass.HourglassSimulation;
import cool.raptor.hourglass.models.Particle;

import java.util.List;

public class TimesUpStopper implements SimulationObserver {

    @Override
    public void simulationDidStart(Simulation simulation) {
        System.out.println("[TimesUpStopper] Started...");
    }

    @Override
    public void notify(Simulation simulation) {
    }

    @Override
    public void simulationDidFinish(Simulation simulation) {
        HourglassSimulation s = (HourglassSimulation) simulation;
        System.out.println("[TimesUpStopper] Finished in " + s.getTimeSimulation() + " s.");
    }

    @Override
    public Boolean shouldStopSimulation(Simulation simulation) {
        HourglassSimulation s = (HourglassSimulation) simulation;
        return s.getTimeSimulation() <= 0;
    }
}
