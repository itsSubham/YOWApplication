package f.ratnatech.yowapplication.AdapterResponse;

import java.io.Serializable;

/**
 * Created by RatnaDev008 on 1/2/2019.
 */

public class DailyRideResponse implements Serializable {
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String imageUrl;
    String name;
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;
}
