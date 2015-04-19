package database;

public class Restaurant implements Comparable{
    private String name;
    private Double latitude;
    private Double longitude;
    private int rating;

    public Restaurant(String name, Double latitude, Double longitude, int rating) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
    }

    public Restaurant(String name, Double longitude, Double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Restaurant" +
                "name:'" + name + '\'' +
                ", latitude:" + latitude +
                ", longitude:" + longitude +
                ", rating:" + rating;
    }

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		int compare=((Restaurant)o).getRating();
		return this.getRating()-compare;
	}
}
