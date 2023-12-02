public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yY, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yY;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dy = p.yyPos - this.yyPos;
        double dx = p.xxPos - this.xxPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return G * this.mass * p.mass / r / r;
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double r = this.calcDistance(p);
        double F = calcForceExertedBy(p);
        return F * dx / r;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        double r = this.calcDistance(p);
        double F = calcForceExertedBy(p);
        return F * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double netFx = 0.0;
        for (Planet i : p) {
            if (!this.equals(i))
                netFx += this.calcForceExertedByX(i);
        }
        return netFx;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double netFy = 0.0;
        for (Planet i : p) {
            if (!this.equals(i))
                netFy += this.calcForceExertedByY(i);
        }
        return netFy;
    }

    public void update(double time, double Fx, double Fy) {
        double xxAcc = Fx / this.mass;
        double yyAcc = Fy / this.mass;
        this.xxVel += time * xxAcc;
        this.yyVel += time * yyAcc;
        this.xxPos += time * this.xxVel;
        this.yyPos += time * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}