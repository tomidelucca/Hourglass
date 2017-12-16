package cool.raptor.hourglass.simulation;

public interface SimulationObserver {
    void simulationDidStart(Simulation simulation);
    void notify(Simulation simulation);
    void simulationDidFinish(Simulation simulation);
    Boolean shouldStopSimulation(Simulation simulation);
}