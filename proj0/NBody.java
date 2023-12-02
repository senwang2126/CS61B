public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        double scale = in.readDouble();
        in.close();
        return scale;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        in.readDouble();
        Planet[] p = new Planet[N];
        for (int i = 0; i < N; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String name = in.readString();
            p[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, name);
        }
        in.close();
        return p;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double scale = readRadius(filename);
        Planet[] p = readPlanets(filename);
        int N = p.length;
        String imageToDraw = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-scale, scale);
        for (double t = 0; t < T; t += dt) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for (int i = 0; i < N; i++) {
                xForces[i] = p[i].calcNetForceExertedByX(p);
                yForces[i] = p[i].calcNetForceExertedByY(p);
            }
            for (int i = 0; i < N; i++) {
                p[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, imageToDraw);
            for (Planet i : p) {
                i.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", N);
        StdOut.printf("%.2e\n", scale);
        for (int i = 0; i < N; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }
    }
}
