import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Base64;

public class Sighting {
    private double latitude;
    private double longitude;
    private String date;
    private Timestamp timestamp;
    private Blob image; // Optional field
    private int accuracy; // Optional field

    // Constructor without image and accuracy
    public Sighting(double latitude, double longitude, String date, Timestamp timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.timestamp = timestamp;
    }

    // Constructor with image and accuracy
    public Sighting(double latitude, double longitude, String date, Timestamp timestamp, Blob image, int accuracy) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.timestamp = timestamp;
        this.image = image;
        this.accuracy = accuracy;
    }

    // Getters
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getDate() { return date; }
    public Timestamp getTimestamp() { return timestamp; }

    public int getAccuracy() { return accuracy; }

    // Method to get the image as Base64
    public String getImageAsBase64() {
        if (image != null) {
            try {
                byte[] imageBytes = image.getBytes(1, (int) image.length());
                return Base64.getEncoder().encodeToString(imageBytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
