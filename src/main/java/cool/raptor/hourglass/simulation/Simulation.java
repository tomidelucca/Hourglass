package cool.raptor.hourglass.simulation;

import java.util.ArrayList;
import java.util.List;

public abstract class Simulation {
    private List<SimulationObserver> observers = new ArrayList<SimulationObserver>();

    public void addObserver(SimulationObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(SimulationObserver observer) {
        this.observers.remove(observer);
    }

    protected void updateObservers() {
        for(SimulationObserver obs: observers) {
            obs.notify(this);
        }
    }

    protected void startSimulation() {
        for(SimulationObserver obs: observers) {
            obs.simulationDidStart(this);
        }
    }

    protected void finishSimulation() {
        for(SimulationObserver obs: observers) {
            obs.simulationDidFinish(this);
        }
    }

    protected Boolean shouldStopSimulation() {
        for(SimulationObserver obs: observers) {
            if(obs.shouldStopSimulation(this)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public abstract Boolean simulate();
    public abstract void reset();
    public abstract void initialize();
}