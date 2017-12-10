package simulation;

public interface SimulationObserver {
    void notify(Simulation simulation);
    void simulationDidFinish(Simulation simulation);
}