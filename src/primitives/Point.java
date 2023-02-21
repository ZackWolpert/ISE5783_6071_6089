package primitives;

public class Point {
    protected Double3 xyz;


    public Point(double firstCoordinate,double secondCoordinate,double thirdCoordinate){
        this.xyz = new Double3(firstCoordinate,secondCoordinate,thirdCoordinate);
    }
    public Point(Double3 myDouble3){
        this.xyz = myDouble3;
    }

    public Vector subtract(Point myPoint){
        return new Vector(this.xyz.subtract(myPoint.xyz));

    }
    public double distanceSquared(Point myPoint)
    {
        return (this.xyz.d1-myPoint.xyz.d1) * (this.xyz.d1-myPoint.xyz.d1) + (this.xyz.d2-myPoint.xyz.d2) * (this.xyz.d2-myPoint.xyz.d2) + (this.xyz.d3-myPoint.xyz.d3) * (this.xyz.d3-myPoint.xyz.d3);
    }
    public double distance(Point myPoint){
        return Math.sqrt(distanceSquared(myPoint));
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj instanceof Point other)
            return this.xyz.equals(other.xyz) ;
        return false;

    }
    @Override
    public String toString(){
        return this.xyz.toString();
    }
    public Point add(Vector myVector){

        return new Point(this.xyz.add(myVector.xyz));
    }
}
