package cool.raptor.hourglass.method;

import cool.raptor.hourglass.models.Particle;
import java.util.*;


public class CellIndexMethod3D {

    public static Map<Particle, Set<Particle>> neighbours(List<Particle> particles, double L, int M, double Rc) {

        /*CubicMatrix matrix = CellIndexMethod3D.createAndPopulateMatrix(particles, M, L);

        Map<Particle, Set<Particle>> result = CellIndexMethod3D.createAndPopulateEmptyMap(particles);

        double cellSize = L/M;

        for(int i = 0; i<particles.size(); i++) {
            Particle p = particles.get(i);
            int x = (int) (p.getPosition().getX() / cellSize);
            int y = (int) (p.getPosition().getY() / cellSize);
            int z = (int) (p.getPosition().getZ() / cellSize);

            List<Particle> particlesAtPoint;

            for (int dX = -1; dX <= 1; dX++) {
                for (int dY = -1; dY <= 1; dY++) {
                    for (int dZ = -1; dZ <= 1; dZ++) {
                        particlesAtPoint = matrix.getElement(x + dX, y + dY, z + dZ);
                        try {
                            for (Particle otherParticle : particlesAtPoint) {
                                if (p.distanceToParticle(otherParticle) <= Rc && !p.equals(otherParticle)) {
                                    result.get(p).add(otherParticle);
                                    result.get(otherParticle).add(p);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }*/

        Map<Particle, Set<Particle>> ret = new HashMap<>();
        for (Particle p1 : particles) {
            if (!ret.containsKey(p1))
                ret.put(p1, new HashSet<>());
            for (Particle p2 : particles) {
                if (p1.getId() != p2.getId() && p1.isTouching(p2)) {
                    if (!ret.get(p1).contains(p2)) {
                        ret.get(p1).add(p2);
                        if (!ret.containsKey(p2))
                            ret.put(p2, new HashSet<>());
                        ret.get(p2).add(p1);
                    }
                }
            }
        }
        return ret;
    }

    private static Map<Particle, Set<Particle>> createAndPopulateEmptyMap(List<Particle> particles) {

        Map<Particle, Set<Particle>> map = new HashMap<>();

        for(Particle p: particles) {
            map.put(p, new HashSet<Particle>());
        }

        return map;
    }

    private static CubicMatrix createAndPopulateMatrix(List<Particle> particles, int M, double L) {

        CubicMatrix matrix = new CubicMatrix(M);

        double cellSize = L/M;

        for(Particle particle: particles) {
            Particle p = particle;
            int x = (int)(p.getPosition().getX()/cellSize);
            int y = (int)(p.getPosition().getY()/cellSize);
            int z = (int)(p.getPosition().getZ()/cellSize);
            try {
                matrix.getElement(x,y,z).add(p);
            } catch (Exception e) {
                System.out.println("catch");
            }
        }
        return matrix;
    }
}
