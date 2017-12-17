package cool.raptor.hourglass.generator;

import cool.raptor.hourglass.models.Hourglass;
import cool.raptor.hourglass.models.Particle;
import cool.raptor.hourglass.models.ParticleConfiguration;
import cool.raptor.hourglass.models.Vector;

import java.util.ArrayList;
import java.util.List;

public class ParticleGenerator {

	public static List<Particle> generate(ParticleConfiguration configuration, Hourglass hourglass) {
		List<Particle> listParticle = new ArrayList<Particle>();
		double maxRadius = (configuration.getRadius()/5)/2;
		double minRadius = (configuration.getRadius()/7)/2;
		double tries = 1E2;
		double counterTries = tries;
		double hourglassRadius = hourglass.getRadius();
		Particle particle;
		Vector position;
		Vector velocity;
		double exactRadius;

		while(counterTries > 0 && listParticle.size() <= configuration.getMaxParticles()) {
            exactRadius = Math.random() * (minRadius - maxRadius) + minRadius;
			position = new Vector(Math.random() * 2 * (hourglassRadius - exactRadius) - hourglassRadius + exactRadius,
					Math.random() * 2 * (hourglassRadius - exactRadius) - hourglassRadius + exactRadius,
					Math.random() * (hourglass.getHeight() - 2 * exactRadius) + exactRadius);
			velocity = Vector.zero();
			particle = new Particle(position, velocity, exactRadius, configuration.getMass(), Boolean.FALSE);

			if (hourglass.isParticleInside(particle) && validParticle(particle, listParticle)) {
				listParticle.add(particle);
				counterTries = tries;
			} else {
				counterTries--;
			}
		}
		return listParticle;
	}

	private static boolean validParticle(Particle particle, List<Particle> listParticle) {
		for(Particle p: listParticle) {
			if (isOverlap(particle, p))
				return false;
		}
		return true;
	}

	private static boolean isOverlap(Particle p, Particle other) {
		if(overlap(p, other) > 0)
			return true;
		
		return false;
	}
	
	private static double overlap(Particle p, Particle other) {
		return p.getRadius() + other.getRadius() - Vector.rest(p.getPosition(), other.getPosition()).mod();
	}

}
