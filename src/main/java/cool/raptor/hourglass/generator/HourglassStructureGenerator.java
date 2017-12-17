package cool.raptor.hourglass.generator;

import cool.raptor.hourglass.models.Hourglass;
import cool.raptor.hourglass.models.Particle;
import cool.raptor.hourglass.models.Vector;

import java.util.ArrayList;
import java.util.List;

public class HourglassStructureGenerator {

    private static final double MASS = 1.0;

    public static List<Particle> generate(Hourglass hourglass) {
        List<Particle> structure = new ArrayList<>();
        double h = hourglass.getHeight();
        double r = hourglass.getRadius();
        double sr = hourglass.getConfiguration().getRadius() / 50;
        double percentage = 50.0;
        Particle p;
        for (double i = Math.sqrt(Math.pow(r, 2) - Math.pow(h, 2)); i < r; i += (r / percentage)) {
            double z1 = -1 * (Math.sqrt(Math.pow(r, 2) - Math.pow(i, 2))) + h;
            double z2 = -1 * z1;
            for (double j = -i; j <= i; j += (i / percentage)) {
                double y = Math.sqrt(Math.pow(i, 2) - Math.pow(j, 2));
                p = new Particle(new Vector(j, y, z1), Vector.zero(), sr, MASS, true);
                structure.add(p);
                p = new Particle(new Vector(j, y, z2), Vector.zero(), sr, MASS, true);
                structure.add(p);
                p = new Particle(new Vector(j, -y, z1), Vector.zero(), sr, MASS, true);
                structure.add(p);
                p = new Particle(new Vector(j, -y, z2), Vector.zero(), sr, MASS, true);
                structure.add(p);
            }
        }
        for (double x = -r; x <= r; x += (r / percentage)) {
            double y = Math.sqrt(Math.pow(r, 2) - Math.pow(x, 2));
            p = new Particle(new Vector(x, y, h), Vector.zero(), sr, MASS, true);
            structure.add(p);
            p = new Particle(new Vector(x, y, -h), Vector.zero(), sr, MASS, true);
            structure.add(p);
            p = new Particle(new Vector(x, -y, h), Vector.zero(), sr, MASS, true);
            structure.add(p);
            p = new Particle(new Vector(x, -y, -h), Vector.zero(), sr, MASS, true);
            structure.add(p);
        }
        return structure;
    }
}
