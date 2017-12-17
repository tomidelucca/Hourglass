package cool.raptor.hourglass.method;

import cool.raptor.hourglass.models.Particle;
import java.util.*;


public class CellIndexMethod3D {

    public static Map<Particle, Set<Particle>> neighbours(List<Particle> particles, double L, int M, double Rc) {

        CubicMatrix matrix = CellIndexMethod3D.createAndPopulateMatrix(particles, M, L);

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
        }

        return result;
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
