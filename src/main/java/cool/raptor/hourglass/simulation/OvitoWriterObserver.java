package cool.raptor.hourglass.simulation;

import cool.raptor.hourglass.Hourglass;
import cool.raptor.hourglass.ovito.OvitoFile;

import java.io.File;

public class OvitoWriterObserver extends Observer {

    private static double ANIMATION_DT = 1E-1;
    private double timeAnimation = ANIMATION_DT;
    private OvitoFile ovitoFile = null;
    private String path = "output/animation/";

    public OvitoWriterObserver(String name) {
        new File(path).mkdirs();
        this.ovitoFile = new OvitoFile(path + name);
    }

    @Override
    public void simulationDidStart(Simulation simulation) {
        Hourglass s = (Hourglass) simulation;
        ovitoFile.write(s.getParticles());
        System.out.println("[OvitoWriterObserver] Started...");
    }

    @Override
    public void notify(Simulation simulation) {
        Hourglass s = (Hourglass) simulation;

        timeAnimation -= Hourglass.getSimulationDt();

        if (timeAnimation <= 0) {
            System.out.println("[OvitoWriterObserver] Animation saved at: " + s.getTimeSimulation());
            ovitoFile.write(s.getParticles());
            timeAnimation += ANIMATION_DT;
        }
    }

    @Override
    public void simulationDidFinish(Simulation simulation) {
        ovitoFile.closeFile();
        System.out.println("[OvitoWriterObserver] Finished...");
    }
}
