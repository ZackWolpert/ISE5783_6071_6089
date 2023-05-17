package primitives;


/**
 * The Material class represents the material properties of an object in a scene.
 */
public class Material {
    public Double3 kD = new Double3(0),kS = new Double3(0);
    public int nShininess = 0;

    /**
     * Sets the diffuse reflection coefficient of the material (Double3).
     * @param kD the diffuse reflection coefficient
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;return this;
    }

    /**
     * Sets the diffuse reflection coefficient of the material (double).
     * @param kD the diffuse reflection coefficient
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * * Sets the specular reflection coefficient of the material (Double3).
     * @param kS the specular reflection coefficient
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;return this;
    }

    /**
     * * Sets the specular reflection coefficient of the material (double)
     * @param kS the specular reflection coefficient
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Sets the shininess exponent of the material.
     * @param nShininess the shininess exponent
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;return this;
    }


}
