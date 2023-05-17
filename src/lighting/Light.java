package lighting;

import primitives.Color;


/**
 * The Light class represents a light source with a specified intensity.
 */
class Light {
    private Color intensity;

    /**
     * Constructs a Light object with the specified intensity.
     * @param intensity the intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Retrieves the original intensity of the light.
     * @return the intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}
