package cool.raptor.hourglass.generator;

import cool.raptor.hourglass.models.Hourglass;
import cool.raptor.hourglass.models.Particle;
import cool.raptor.hourglass.models.Vector;

import java.util.ArrayList;
import java.util.List;

public class ParticleGenerator {

	public static List<Particle> generate(double radius, double mass, Hourglass hourglass, int maxParticles) {
		List<Particle> listParticle = new ArrayList<Particle>();
		double maxRadius = (radius/5)/2;
		double minRadius = (radius/7)/2;
		double tries = 1E2;
		double counterTries = tries;
		double hourglassRadius = hourglass.getRadius();
		Particle particle;
		Vector position;
		Vector velocity;
		double exactRadius;

		while(counterTries > 0 && listParticle.size() <= maxParticles) {
			position = new Vector(Math.random() * 2 * hourglassRadius - hourglassRadius,
					Math.random() * 2 * hourglassRadius - hourglassRadius,
					Math.random() * hourglassRadius);
			velocity = Vector.zero();
			exactRadius = Math.random() * (minRadius - maxRadius) + minRadius;
			particle = new Particle(position, velocity, exactRadius, mass, Boolean.FALSE);

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
