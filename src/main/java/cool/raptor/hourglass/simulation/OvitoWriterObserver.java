package cool.raptor.hourglass.simulation;

import cool.raptor.hourglass.HourglassSimulation;
import cool.raptor.hourglass.models.Hourglass;
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
        writeHourglassFrame(s.getHourglass());
        System.out.println("[OvitoWriterObserver] Started...");
    }

    @Override
    public void notify(Simulation simulation) {
        HourglassSimulation s = (HourglassSimulation) simulation;

        timeAnimation -= HourglassSimulation.getSimulationDt();

       if (timeAnimation <= 0) {
            System.out.println("[OvitoWriterObserver] Animation saved at: " + s.getTimeSimulation());
            Hourglass h = s.getHourglass();
            System.out.println(h);
            writeHourglassFrame(h);
            timeAnimation += ANIMATION_DT;
       }
    }

    @Override
    public void simulationDidFinish(Simulation simulation) {
        ovitoFile.closeFile();
        System.out.println("[OvitoWriterObserver] Finished...");
    }

    private void writeHourglassFrame(Hourglass h) {
        List<Particle> frame = new ArrayList<>(h.getStructure().size() + h.getParticles().size());
        frame.addAll(h.getParticles());
        frame.addAll(h.getStructure());
        ovitoFile.writeFrame(frame);
    }
}
