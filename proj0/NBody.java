public class NBody{
    
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int numberOfPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int numberOfPlanets = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[numberOfPlanets];
        for (int i = 0; i < numberOfPlanets; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = NBody.readPlanets(filename);
        double radius = NBody.readRadius(filename);

        StdDraw.enableDoubleBuffering();
        double time = 0.0;

        while (time <= T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /* Draws the backgroud. */
            String backgroundImage = "images/starfield.jpg";
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0, 0, backgroundImage);
            
            /* Draws all of the planets. */
            for (int x = 0; x < planets.length; x++) {
                planets[x].update(dt, xForces[x], yForces[x]);
                planets[x].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            time = time + dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}