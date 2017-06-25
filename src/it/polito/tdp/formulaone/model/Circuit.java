package it.polito.tdp.formulaone.model;

public class Circuit {
	
	private int circuitId ;
	private String circuitRef ;
	private String name ;
	private String location ;
	private String country ;
	private double lat ;
	private double lng ;
	private int alt ;
	private String url ;
	
	public Circuit (int circuitId, String name) {
		this. circuitId = circuitId;
		this. name = name;
	}
	
	public Circuit(int circuitId, String circuitRef, String name, String location, String country, double lat,
			double lng, int alt, String url) {
		super();
		this.circuitId = circuitId;
		this.circuitRef = circuitRef;
		this.name = name;
		this.location = location;
		this.country = country;
		this.lat = lat;
		this.lng = lng;
		this.alt = alt;
		this.url = url;
	}
	public int getCircuitId() {
		return circuitId;
	}
	public void setCircuitId(int circuitId) {
		this.circuitId = circuitId;
	}
	public String getCircuitRef() {
		return circuitRef;
	}
	public void setCircuitRef(String circuitRef) {
		this.circuitRef = circuitRef;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public int getAlt() {
		return alt;
	}
	public void setAlt(int alt) {
		this.alt = alt;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return circuitId+"";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alt;
		result = prime * result + circuitId;
		result = prime * result + ((circuitRef == null) ? 0 : circuitRef.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		long temp;
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lng);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circuit other = (Circuit) obj;
		if (alt != other.alt)
			return false;
		if (circuitId != other.circuitId)
			return false;
		if (circuitRef == null) {
			if (other.circuitRef != null)
				return false;
		} else if (!circuitRef.equals(other.circuitRef))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (Double.doubleToLongBits(lng) != Double.doubleToLongBits(other.lng))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
}
