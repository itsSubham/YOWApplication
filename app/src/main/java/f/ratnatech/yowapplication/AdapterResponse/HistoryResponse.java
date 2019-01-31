package f.ratnatech.yowapplication.AdapterResponse;

import java.io.Serializable;

/**
 * Created by RatnaDev008 on 12/31/2018.
 */

public class HistoryResponse implements Serializable {
    String time;
    String name;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPick_loc() {
        return pick_loc;
    }

    public void setPick_loc(String pick_loc) {
        this.pick_loc = pick_loc;
    }

    public String getDrop_loc() {
        return drop_loc;
    }

    public void setDrop_loc(String drop_loc) {
        this.drop_loc = drop_loc;
    }

    String pick_loc;
    String drop_loc;
}
