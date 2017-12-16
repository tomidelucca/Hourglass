package cool.raptor.hourglass.ovito;

import cool.raptor.hourglass.models.Particle;

import java.io.PrintWriter;
import java.util.List;

public class OvitoFile {

    private PrintWriter writer = null;
    private String path;
    private int frame = 0;
    
    public OvitoFile(String path) {
        try {
        	this.path = path;
            this.writer = new PrintWriter(path, "UTF-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void write(List<Particle> listParticle) {
        this.writer.println(listParticle.size());
        this.writer.println(frame++);

        for(Particle p: listParticle) {
            this.writer.println(p.print());
        }
    }

    public void closeFile() {
        this.writer.close();
    }

}