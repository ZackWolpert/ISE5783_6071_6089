package primitives;

public class Vector extends Point {
    public Vector(double firstCoordinate, double secondCoordinate, double thirdCoordinate)
    {
        super( firstCoordinate,  secondCoordinate,  thirdCoordinate);
        if(this.xyz.equals(this.xyz.ZERO))
            throw new IllegalArgumentException("Vector cant be 0 Vector");
    }
    public Vector(Double3 myDouble3){
        super(myDouble3);
        if(this.xyz.equals(this.xyz.ZERO))
        {
            throw new IllegalArgumentException("Vector cant be 0 Vector");
        }
    }


    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj instanceof Vector other)
            return this.xyz.equals(other.xyz) ;
        return false;
    }
    @Override
    public String toString(){
        return "";
    }
    public Vector add(Vector myVector){
        return new Vector(this.xyz.add(myVector.xyz));
    }
    public Vector scale(double myDouble){
        return new Vector(this.xyz.scale(myDouble));
    }
    public Vector crossProduct(Vector myVector){
        double a1 = this.xyz.d2 * myVector.xyz.d3 - this.xyz.d3 * myVector.xyz.d2;
        double a2 = this.xyz.d3 * myVector.xyz.d1 - this.xyz.d1 * myVector.xyz.d3;
        double a3 = this.xyz.d1 * myVector.xyz.d2 - this.xyz.d2 * myVector.xyz.d1;
        return new Vector(a1,a2,a3);

    }
    public double lengthSquared(){
        return (xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3);
    }
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }
    public Vector normalize(){

        return new Vector (1/this.length()*this.xyz.d1,1/this.length()*this.xyz.d2,1/this.length()*this.xyz.d3);
    }
    public double dotProduct(Vector myvVector){
      return (this.xyz.d1*myvVector.xyz.d1+ this.xyz.d2*myvVector.xyz.d2 + this.xyz.d3*myvVector.xyz.d3);
    }
}
