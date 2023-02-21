package primitives;

public class Ray {
    private Point p0;
    private Vector dir;

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ray other)
            return this.p0.equals(other.p0) && this.dir.equals(other.dir);
        return false;
    }
    @Override
    public String toString(){
        return "";
    }
    public Ray(Point myPoint, Vector myVector){
        this.dir = myVector.normalize();
        this.p0 = myPoint;
    }

}
