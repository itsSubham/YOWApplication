package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by RatnaDev008 on 1/8/2019.
 */

public class GoogleDistanceResponse {
    public ArrayList<GeocodedWaypoints> getGeocoded_waypoints() {
        return geocoded_waypoints;
    }

    public void setGeocoded_waypoints(ArrayList<GeocodedWaypoints> geocoded_waypoints) {
        this.geocoded_waypoints = geocoded_waypoints;
    }

    public ArrayList<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Routes> routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("geocoded_waypoints")
    private ArrayList<GeocodedWaypoints> geocoded_waypoints;
    @SerializedName("routes")
    private ArrayList<Routes> routes;
    @SerializedName("status")
    private String status;


    public class Routes{
     @SerializedName("bounds")
        private Bounds bounds;
     @SerializedName("copyrights")
        private String copyrights;
     @SerializedName("legs")
        private ArrayList<Legs> legs;

        public Bounds getBounds() {
            return bounds;
        }

        public void setBounds(Bounds bounds) {
            this.bounds = bounds;
        }

        public String getCopyrights() {
            return copyrights;
        }

        public void setCopyrights(String copyrights) {
            this.copyrights = copyrights;
        }

        public ArrayList<Legs> getLegs() {
            return legs;
        }

        public void setLegs(ArrayList<Legs> legs) {
            this.legs = legs;
        }

        public OverviewPolyline getOverview_polyline() {
            return overview_polyline;
        }

        public void setOverview_polyline(OverviewPolyline overview_polyline) {
            this.overview_polyline = overview_polyline;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public ArrayList<Warnings> getWarnings() {
            return warnings;
        }

        public void setWarnings(ArrayList<Warnings> warnings) {
            this.warnings = warnings;
        }

        public ArrayList<WaypointOrder> getWaypoint_order() {
            return waypoint_order;
        }

        public void setWaypoint_order(ArrayList<WaypointOrder> waypoint_order) {
            this.waypoint_order = waypoint_order;
        }

        @SerializedName("overview_polyline")
        private OverviewPolyline overview_polyline;
        @SerializedName("summary")
        private String summary;
        @SerializedName("warnings")
        private ArrayList<Warnings> warnings;
        @SerializedName("waypoint_order")
        private ArrayList<WaypointOrder> waypoint_order;
    }


    public class GeocodedWaypoints{
        public String getGeocoder_status() {
            return geocoder_status;
        }

        public void setGeocoder_status(String geocoder_status) {
            this.geocoder_status = geocoder_status;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public ArrayList<String> getTypes() {
            return types;
        }

        public void setTypes(ArrayList<String> types) {
            this.types = types;
        }

        @SerializedName("geocoder_status")
        private String geocoder_status;
        @SerializedName("place_id")
        private String place_id;
        @SerializedName("types")
        private ArrayList<String> types;


    }
    public class WaypointOrder{

    }
    public class Warnings{

    }
    public class OverviewPolyline{
        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        @SerializedName("points")
        private String points;
    }
    public class Legs{
        @SerializedName("distance")
        private Distance distance;
        @SerializedName("end_address")
        private String end_address;

        public Distance getDistance() {
            return distance;
        }

        public void setDistance(Distance distance) {
            this.distance = distance;
        }

        public String getEnd_address() {
            return end_address;
        }

        public void setEnd_address(String end_address) {
            this.end_address = end_address;
        }

        public String getStart_address() {
            return start_address;
        }

        public void setStart_address(String start_address) {
            this.start_address = start_address;
        }

        public Duration getDuration() {
            return duration;
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public EndLocation getEnd_location() {
            return end_location;
        }

        public void setEnd_location(EndLocation end_location) {
            this.end_location = end_location;
        }

        public StartLocation getStart_location() {
            return start_location;
        }

        public void setStart_location(StartLocation start_location) {
            this.start_location = start_location;
        }

        public ArrayList<Traffic_speed_entry> getTraffic_speed_entry() {
            return traffic_speed_entry;
        }

        public void setTraffic_speed_entry(ArrayList<Traffic_speed_entry> traffic_speed_entry) {
            this.traffic_speed_entry = traffic_speed_entry;
        }

        public ArrayList<Via_waypoint> getVia_waypoint() {
            return via_waypoint;
        }

        public void setVia_waypoint(ArrayList<Via_waypoint> via_waypoint) {
            this.via_waypoint = via_waypoint;
        }

        public ArrayList<GoogleDistanceResponse.steps> getSteps() {
            return steps;
        }

        public void setSteps(ArrayList<GoogleDistanceResponse.steps> steps) {
            this.steps = steps;
        }

        @SerializedName("start_address")
        private String start_address;
        @SerializedName("duration")
        private Duration duration;
        @SerializedName("end_location")
        private EndLocation end_location;
        @SerializedName("start_location")
        private StartLocation start_location;
        @SerializedName("traffic_speed_entry")
        private ArrayList<Traffic_speed_entry> traffic_speed_entry;
        @SerializedName("via_waypoint")
        private ArrayList<Via_waypoint> via_waypoint;
        @SerializedName("steps")
        private ArrayList<steps> steps;

    }
    public class steps{
        @SerializedName("distance")
        private Stepdistance distance;
        @SerializedName("duration")
        private Stepduration duration;

        public Stepdistance getDistance() {
            return distance;
        }

        public void setDistance(Stepdistance distance) {
            this.distance = distance;
        }

        public Stepduration getDuration() {
            return duration;
        }

        public void setDuration(Stepduration duration) {
            this.duration = duration;
        }

        public StepEnd_location getEnd_location() {
            return end_location;
        }

        public void setEnd_location(StepEnd_location end_location) {
            this.end_location = end_location;
        }

        public String getHtml_instructions() {
            return html_instructions;
        }

        public void setHtml_instructions(String html_instructions) {
            this.html_instructions = html_instructions;
        }

        public Polyline getPolyline() {
            return polyline;
        }

        public void setPolyline(Polyline polyline) {
            this.polyline = polyline;
        }

        public StepStartLocation getStart_location() {
            return start_location;
        }

        public void setStart_location(StepStartLocation start_location) {
            this.start_location = start_location;
        }

        public String getTravel_mode() {
            return travel_mode;
        }

        public void setTravel_mode(String travel_mode) {
            this.travel_mode = travel_mode;
        }

        @SerializedName("end_location")
        private StepEnd_location end_location;
        @SerializedName("html_instructions")
        private String html_instructions;
        @SerializedName("polyline")
        private Polyline polyline;
        @SerializedName("start_location")
        private StepStartLocation start_location;
        @SerializedName("travel_mode")
        private String travel_mode;

    }
    public class StepStartLocation{
        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        @SerializedName("lat")
        private Double lat;
        @SerializedName("lng")
        private Double lng;
    }
    public class Polyline{
        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        @SerializedName("points")
        private String points;
    }
    public class StepEnd_location{
        @SerializedName("lat")
        private Double lat;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        @SerializedName("lng")
        private Double lng;
    }
    public class Stepduration{
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @SerializedName("text")
        private String text;
        @SerializedName("value")
        private int value;

    }
    public class Stepdistance{
        @SerializedName("text")
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @SerializedName("value")
        private int value;
    }
    public class Via_waypoint{

    }
    public class Traffic_speed_entry{

    }
    public class StartLocation{
        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        @SerializedName("lat")
        private Double lat;
        @SerializedName("lng")
        private Double lng;
    }
    public class EndLocation{
        @SerializedName("lat")
        private Double lat;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        @SerializedName("lng")
        private Double lng;
    }
    public class Duration{
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        @SerializedName("text")
        private String text;
        @SerializedName("value")
        private Double value;
    }
    public class Distance{
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        @SerializedName("text")
        private String text;
        @SerializedName("value")
        private Double value;

    }
    public class Bounds{
        @SerializedName("northeast")
        private Northeast northeast;

        public Northeast getNortheast() {
            return northeast;
        }

        public void setNortheast(Northeast northeast) {
            this.northeast = northeast;
        }

        public Southwest getSouthwest() {
            return southwest;
        }

        public void setSouthwest(Southwest southwest) {
            this.southwest = southwest;
        }

        @SerializedName("southwest")
        private Southwest southwest;


    }

    public class Northeast{
        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        @SerializedName("lat")
        private Double lat;
        @SerializedName("lng")
        private Double lng;
    }
    public class Southwest{
        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        @SerializedName("lat")
        private Double lat;
        @SerializedName("lng")
        private Double lng;

    }
}
