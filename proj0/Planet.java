public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double distance = Math.sqrt(Math.pow((p.xxPos - this.xxPos), 2) + Math.pow((p.yyPos - this.yyPos), 2));
        return distance;
    }

    public double calcForceExertedBy(Planet p){
        double force = Planet.G * this.mass * p.mass / Math.pow(this.calcDistance(p), 2);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double forceX = this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
        return forceX;
    }

    public double calcForceExertedByY(Planet p) {
        double forceY = this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0.0;
        for (Planet p: planets) {
            if (this.equals(p)) {
                continue;
            }
            else {
                netForceX = netForceX + this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0.0;
        for (Planet p: planets) {
            if (this.equals(p)) {
                continue;
            }
            else {
                netForceY = netForceY + this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
       double accelerationX = fX / this.mass;
       double accelerationY = fY / this.mass;
       
       double newXXVel = this.xxVel + dt * accelerationX;
       this.xxVel = newXXVel;
       double newYYVel = this.yyVel + dt * accelerationY;
       this.yyVel = newYYVel;

       double newXXPos = this.xxPos + dt * this.xxVel;
       this.xxPos = newXXPos;
       double newYYPos = this.yyPos + dt * this.yyVel;
       this.yyPos = newYYPos;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }
}