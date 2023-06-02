package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * Camera class represents a camera in 3D Cartesian coordinate system
 */
public class Camera {
    private final Point p0;
    private final Vector vRight;
    private final Vector vUp;
    private final Vector vTo;
    private double height;
    private double width;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    /**
     * Constructor of Camera using p0, up-vector and to-vector
     *
     * @param p0  the point that the camera is on
     * @param vUp the vector of the direction that the is "looking up" from the camera
     * @param vTo the vector of the direction that the camera is looking at
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo))) {
            throw new IllegalArgumentException("vUp and vTo are not orthogonal!");
        }
        this.p0 = p0;
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }


    /**
     * Updates the size of the view plane
     *
     * @param width  the new width of the view plane
     * @param height the new width of the view plane
     * @return the updated camera object
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Updates the distance between the camera and the view plane
     *
     * @param distance the new distance
     * @return the updated camera object
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Updates the cameras image writer
     * @param imageWriter responsible for holding image related parameters of View Plane
     * @return The updated camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Updates the cameras ray tracer
     * @param rayTracer calculates the color of the point
     * @return The updated camera
     */
    public Camera setRayTracer(RayTracerBasic rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * Gets the height of the view plane
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the width of the view plane
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the distance between the camera and the view plane
     *
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Construct a ray from camera to specific pixel
     *
     * @param nX the number of pixels in each row
     * @param nY the number of pixels in each column
     * @param j  the column index of a pixel
     * @param i  the row index of a pixel
     * @return the ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // pIJ = Center
        Point pIJ = p0.add(vTo.scale(distance));
        double rX = width / nX;
        double rY = height / nY;
        double xJ = (j - (double) (nX - 1) / 2) * rX;
        double yI = -(i - (double) (nY - 1) / 2) * rY;
        if (!isZero(xJ)) pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI)) pIJ = pIJ.add(vUp.scale(yI));
        return new Ray(p0, pIJ.subtract(p0));
    }

    /**
     * Renders the Image while throwing an exception if values are not initialized
     */
    public Camera renderImage() {
        if (imageWriter == null || rayTracer == null)
            throw new MissingResourceException("ERROR", "Camera", "one of the key has not been initialized");

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nX; ++i) {
            for (int j = 0; j < nY; ++j) {
                Color color = castRay(nX, nY, i, j);
                imageWriter.writePixel(i, j, color);
            }
        }
        return this;
    }
    public Color castRay(int nX,int nY,int i,int j){
        Ray ray = constructRay(nX, nY, i, j);
        return rayTracer.traceRay(ray);

    }

    /**
     * Prints the color on to the images grid
     * @param interval the distance between the grid 'blocks'
     * @param color the color of the grid
     */
    public void printGrid(int interval,Color color){
        if (imageWriter == null)
            throw new MissingResourceException("ERROR", "Camera", "imageWriter");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; ++i) {
            for (int j = 0; j < nY; ++j) {
                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(i, j, color);
            }
        }

    }

    /**
     * Writes the data To the image file
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("ERROR", "Camera", "imageWriter");
        imageWriter.writeToImage();
    }
}
