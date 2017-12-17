package cool.raptor.hourglass.simulation;

import cool.raptor.hourglass.HourglassSimulation;
import cool.raptor.hourglass.models.Particle;
import cool.raptor.hourglass.ovito.OvitoFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        HourglassSimulation s = (HourglassSimulation) simulation;
        ovitoFile.writeFrame(s.getParticles());
        System.out.println("[OvitoWriterObserver] Started...");
    }

    @Override
    public void notify(Simulation simulation) {
        HourglassSimulation s = (HourglassSimulation) simulation;

        timeAnimation -= HourglassSimulation.getSimulationDt();

        //if (timeAnimation <= 0) {
            System.out.println("[OvitoWriterObserver] Animation saved at: " + s.getTimeSimulation());
            List<Particle> frame = new ArrayList<>(s.getParticles().size() + s.getHourglass().getStructure().size());
            frame.addAll(s.getParticles());
            frame.addAll(s.getHourglass().getStructure());
            ovitoFile.writeFrame(frame);
            timeAnimation += ANIMATION_DT;
        //}
    }

    @Override
    public void simulationDidFinish(Simulation simulation) {
        ovitoFile.closeFile();
        System.out.println("[OvitoWriterObserver] Finished...");
    }
}
