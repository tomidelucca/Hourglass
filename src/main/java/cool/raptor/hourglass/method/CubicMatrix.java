package cool.raptor.hourglass.method;

import cool.raptor.hourglass.models.Particle;

import java.util.ArrayList;
import java.util.List;

public class CubicMatrix {

    protected List<List<List<List<Particle>>>> matrix;
    protected int size;

    public CubicMatrix(int size) {
        this.size = size;
        matrix = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            matrix.add(new ArrayList<List<List<Particle>>>(size));
            for (int j = 0; j < size; j++) {
                matrix.get(i).add(j, new ArrayList<List<Particle>>());
                for (int k = 0; k < size; k++) {
                    matrix.get(i).get(j).add(k, new ArrayList<Particle>());
                }
            }
        }
    }

    public List<Particle> getElement(int x, int y, int z) {
        if(x>=size || y>=size || z>=size || y<0 || x<0 || z<0) return null;
        return matrix.get(x).get(y).get(z);
    }

    @Override
    public String toString() {
        return "CubicMatrix{" +
                "matrix=" + matrix +
                ", size=" + size +
                '}';
    }
}
