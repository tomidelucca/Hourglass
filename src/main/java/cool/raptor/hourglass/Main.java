package cool.raptor.hourglass;

import cool.raptor.hourglass.simulation.*;

import java.util.HashMap;
import java.util.Map;

public class Main {

    static int SIMULATIONS = 1;

    public static void main(String[] args) {

        Map<String, Object> arguments = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if ((i + 1) < args.length) {
                    if (args[i + 1].startsWith("-")) {
                        arguments.put(args[i].substring(1), Boolean.TRUE);
                    } else {
                        arguments.put(args[i].substring(1), args[++i]);
                    }
                } else {
                    arguments.put(args[i].substring(1), Boolean.TRUE);
                }
            } else {
                throw new IllegalArgumentException("Input error");
            }
        }

        if (arguments.containsKey("i")) {
            SIMULATIONS = Integer.valueOf((String)arguments.get("i"));
        }

        for (int i = 1; i <= SIMULATIONS; i++) {
            String name = "2sim_" + i;

            //Create simulation
            HourglassSimulation simulation = new HourglassSimulation();
            configureSimulation(simulation, arguments);

            //Create observers
            SimulationObserver ovitoObserver = new OvitoWriterObserver(name + ".xyz");
            SimulationObserver flowObserver = new FlowObserver(name + "_flow" + ".csv");
            SimulationObserver emptyingObserver = new EmptyingObserver(name + "_empty" + ".csv");
            SimulationObserver timesUpStopper = new TimesUpStopper();
            SimulationObserver timeObserver = new GlobalTimeObserver();

            //Register observers
            simulation.addObserver(ovitoObserver);
            simulation.addObserver(flowObserver);
            simulation.addObserver(emptyingObserver);
            simulation.addObserver(timesUpStopper);
            simulation.addObserver(timeObserver);

            //Start simulation
            simulation.simulate();
        }
    }

    private static void configureSimulation(HourglassSimulation simulation, Map<String, Object> arguments) {
        if (arguments.containsKey("R")) {
            simulation.HG_RADIUS = Double.valueOf((String)arguments.get("R"));
        }
        if (arguments.containsKey("r")) {
            simulation.P_RADIUS = Double.valueOf((String)arguments.get("r"));
        }
        if (arguments.containsKey("m")) {
            simulation.MASS = Double.valueOf((String)arguments.get("m"));
        }
        if (arguments.containsKey("a")) {
            simulation.HG_H_RADIUS = Double.valueOf((String)arguments.get("a"));
        }
        if (arguments.containsKey("n")) {
            simulation.MAX_PARTICLES = Integer.valueOf((String)arguments.get("n"));
        }
        if (arguments.containsKey("t")) {
            simulation.SIMULATION_TIME = Double.valueOf((String)arguments.get("t"));
        }
    }

}
