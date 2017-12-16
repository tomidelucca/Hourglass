package cool.raptor.hourglass.generator;

import cool.raptor.hourglass.models.Particle;
import cool.raptor.hourglass.models.Vector;

import java.util.ArrayList;
import java.util.List;

public class ParticleGenerator {

	public static List<Particle> generate(double hourglassHeight, double hourglassRadius, double D, double mass) {
		List<Particle> listParticle = new ArrayList<Particle>();
		double maxRadius = (D/5)/2;
		double minRadius = (D/7)/2;
		double tries = 1E6;
		double counterTries = tries;
		int id = 0;
		Particle particle;
		Vector position;
		Vector velocity;
		double radius;

//		int particulas = 300; //cantidad fija de particulas
//		while(counterTries > 0 && listParticle.size() < particulas) { //cantidad fija de particulas

		while(counterTries > 0) { // maxima cantidad de particulas. descomentar y comentar las otras dos.
			position = new Vector(Math.random() * 2 * hourglassRadius - hourglassRadius,
					Math.random() * 2 * hourglassRadius - hourglassRadius,
					Math.random() * hourglassHeight);
			velocity = new Vector(0.0, 0.0, 0.0);
			radius = Math.random() * (minRadius - maxRadius) + minRadius;
//			radius = maxRadius;
//			radius = 0.1;
			particle = new Particle(id, position, velocity, radius, mass);

			if (!outOfBound(particle, hourglassHeight, hourglassRadius) && validParticle(particle, listParticle)) {
				listParticle.add(particle);
				counterTries = tries;
				id++;
			} else
				counterTries--;
		}
		
		System.out.println("[LOG] Number of particles: " + listParticle.size());
		return listParticle;
	}
	
	private static boolean outOfBound(Particle particle, double hourglassHeight, double hourglassRadius) {
		if(particle.getPosition().getX() - particle.getRadius() > -hourglassRadius && particle.getPosition().getX() + particle.getRadius() < hourglassRadius &&
				particle.getPosition().getY() - particle.getRadius() > -hourglassRadius && particle.getPosition().getY() + particle.getRadius() < hourglassRadius &&
				particle.getPosition().getZ() - particle.getRadius() > 0 && particle.getPosition().getZ() + particle.getRadius() < hourglassHeight)
			return false;
		
		return true;
	}

	private static boolean validParticle(Particle particle, List<Particle> listParticle) {
		for(Particle p: listParticle) {
			if(isOverlap(particle, p))
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
