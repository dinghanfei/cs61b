import java.awt.*;

public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    /** initialize an instance of the Planet class*/
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    /**take in a Planet object and initialize an identical Planet object
     * i.e. a copy
     */
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /**calculates the distance between two Planets*/
    public double calcDistance(Planet p){
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    /**takes in a planet, and returns a double describing 
     * the force exerted on this planet by the given planet
     */
    public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        double f = G * mass * p.mass / (r * r);
        return f;
    }

    /**describe the force exerted in the X and Y directions*/
    public double calcForceExertedByX(Planet p){
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        double fx = f * (p.xxPos - xxPos) / r;
        return fx;
    }
    public double calcForceExertedByY(Planet p){
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        double fy = f * (p.yyPos - yyPos) / r;
        return fy;
    }

    /** take in an array of Planets
     * and calculate the net X and net Y force
     * exerted by all planets in that array upon the current Planet
     */
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double f_net_x = 0;
        for(Planet p : allPlanets){
            if(!this.equals(p)){
                f_net_x += calcForceExertedByX(p);
            }
        }
        return f_net_x;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double f_net_y = 0;
        for(Planet p : allPlanets){
            if(!this.equals(p)){
                f_net_y += calcForceExertedByY(p);
            }
        }
        return f_net_y;
    }
    /**determines how much the forces exerted on the planet will cause that planet to accelerate
     * and the resulting change in the planet’s velocity and position in a small period of time
     */
    public void update(double dt, double f_net_x, double f_net_y){
        /**Calculate the acceleration*/
        double a_net_x = f_net_x / mass;
        double a_net_y = f_net_y / mass;

        /**Calculate the new velocity*/
        xxVel += dt * a_net_x;
        yyVel += dt * a_net_y;

        /**Calculate the new position*/
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
    /**draw the Planet’s image at the Planet’s position*/
    public void draw(){
        StdDraw.picture(xxPos, yyPos,"images/"+imgFileName);
    }
}