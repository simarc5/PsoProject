/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author kahma
 */
public class TStations {

    private int mX = 0;
    private int mY = 0;
    //private int foodDemand = 0;
    
//    public Buildings(){
//        foodDemand = new Random().nextInt(5+1-2)+2; //max = 5packets min=2packets
//    }
    //getter and setters for X coordinates

    public int x() {
        return mX;
    }

    public void x(final int xCoordinate) {
        mX = xCoordinate;
        return;
    }
    //getters and setters for Y coordinates

    public int y() {
        return mY;
    }

    public void y(final int yCoordinate) {
        mY = yCoordinate;
        return;
    }
}
