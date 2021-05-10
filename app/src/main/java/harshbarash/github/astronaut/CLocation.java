package harshbarash.github.astronaut;

import android.location.Location;

public class CLocation extends Location {

    private boolean bUseMetricsUnits = false;

    public CLocation(Location location) {
        this(location, true);
    }

    public CLocation(Location location, boolean bUseMetricsUnits) {
        super(location);
        this.bUseMetricsUnits = bUseMetricsUnits;
    }

    public boolean getUseMetricUnits() {
        return this.bUseMetricsUnits;
    }

    public void setbUseMetricsUnits(boolean bUseMetricsUnits){
       this.bUseMetricsUnits = bUseMetricsUnits;
    }

    @Override
    public float distanceTo(Location dest) {

        return super.distanceTo(dest);
    }

    @Override
    public double getAltitude() {

        return super.getAltitude();

    }

    @Override
    public float getSpeed() {
        return super.getSpeed();
    }

    @Override
    public float getAccuracy() {
        return super.getAccuracy();
    }
}
