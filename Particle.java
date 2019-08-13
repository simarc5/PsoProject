/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author saheb
 */

// Fitness = Probability that the location the particle is on has Event

public class Particle {

    private int xPos;
    private int yPos;
    private double xVel;
    private double yVel;
    private Double crowdSize;
    private Particle localBest;

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public double getxVel() {
        return xVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public Double getCrowd() {
        return crowdSize;
    }

    public void setCrowd(Double crowd) {
        this.crowdSize = crowd;
    }

    public Particle getLocalBest() {
        return localBest;
    }

    public void setLocalBest(Particle localBest) {
        this.localBest = localBest;
    }
    
    public double getFitness(){
        return (crowdSize/Constants.MAX_CROWD)*100.0/100.0;
    }
    
}