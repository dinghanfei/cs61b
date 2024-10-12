public class NBody {
    /**return a double corresponding to the radius of the universe*/
    public static double readRadius(String filePath){
        In in = new In(filePath);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    /**return an array of Planets corresponding to the planets in the file*/
    public static Planet[] readPlanets(String filePath){
        In in = new In(filePath);
        int n = in.readInt();
        double radius = in.readDouble();
        Planet[] allPlanets = new Planet[n];
        for(int i = 0; i < n; i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m  = in.readDouble();
            String img = in.readString();
            allPlanets[i] =  new Planet(xP, yP, xV, yV, m, img);
        }
        return allPlanets;
    }

    public static void main(String[] args) {
        /**Collecting All Needed Input*/
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);
        Planet[] allPlanets = readPlanets(fileName);

        /**Drawing the Background*/
        StdDraw.enableDoubleBuffering();
        String BackgroundToDraw = "images/starfield.jpg";
        /** Sets up the universe so it goes from
         * -radius, -radius up to radius, radius */
        StdDraw.setScale(-radius, radius);
        double time = 0;
        int n = allPlanets.length;
        while(time < T){
            /**Calculate the net x and y forces for each planet,
             * storing these in the xForces and yForces arrays respectively.*/
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            for(int i = 0; i < n; i++){
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            /**Call update on each of the planets. */
            for(int j = 0; j < n; j++){
                allPlanets[j].update(dt, xForces[j], yForces[j]);
            }
            /** Clears the drawing window. */
            StdDraw.clear();
            /**draw Background picture*/
            StdDraw.picture(0, 0, BackgroundToDraw);
            /**draw allPlanets picture*/
            for(Planet p : allPlanets){
                p.draw();
            }
            /** Shows the drawing to the screen, and waits 10 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }
}
