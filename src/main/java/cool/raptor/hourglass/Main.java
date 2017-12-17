package cool.raptor.hourglass;

import cool.raptor.hourglass.simulation.*;

public class Main {

    public static final int SIMULATIONS = 1;

    public static void main(String[] args) {

        for (int i = 1; i <= SIMULATIONS; i++) {
            String name = "sim_" + i;

            //Create simulation
            Simulation simulation = new HourglassSimulation();

            //Create observers
            SimulationObserver ovitoObserver = new OvitoWriterObserver(name + ".xyz");
            SimulationObserver noParticlesObserver = new NoParticlesStopper();
            SimulationObserver timeObserver = new GlobalTimeObserver();

            //Register observers
            simulation.addObserver(ovitoObserver);
            simulation.addObserver(noParticlesObserver);
            simulation.addObserver(timeObserver);

            //Start simulation
            simulation.simulate();
        }
    }

}
