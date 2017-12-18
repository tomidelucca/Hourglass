package cool.raptor.hourglass.simulation;

import cool.raptor.hourglass.HourglassSimulation;
import cool.raptor.hourglass.models.Particle;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Tomi on 12/17/17.
 */
public class EmptyingObserver extends Observer {

    private PrintWriter writer = null;
    private static double SAMPLE_DT = 1E-1;
    private double timeFlow = SAMPLE_DT;
    private String path = "output/number/";

    private int numberOfParticles = 0;

    public EmptyingObserver(String name) {
        new File(path).mkdirs();
        try {
            this.path = this.path + name;
            this.writer = new PrintWriter(path, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void simulationDidStart(Simulation simulation) {
        System.out.println("[EmptyingObserver] Started...");
        writer.println("TIME,PARTICLES");
    }

    @Override
    public void notify(Simulation simulation) {

        HourglassSimulation s = (HourglassSimulation) simulation;

        List<Particle> particles = s.getHourglass().getParticles();

        timeFlow -= HourglassSimulation.getSimulationDt();

        if (timeFlow <= 0) {
            writer.println(s.getTimeSimulation() + "," + (double)numberOfParticles);
            timeFlow += SAMPLE_DT;
        }

        for(Particle p : particles) {
            if(!p.isFixed() && p.isVisible() && (p.getPrevPosition().getY() > 0 && p.getPosition().getY() <= 0)) {
                numberOfParticles++;
            }
        }
    }

    @Override
    public void simulationDidFinish(Simulation simulation) {
        writer.close();
        System.out.println("[EmptyingObserver] Finished.");
    }

}
