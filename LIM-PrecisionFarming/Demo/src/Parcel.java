
public class Parcel {

	
	String  parcelId = null;
    String owner = null;
    String soilType = null;
    String cropType = null;
    String address=null;
    String image=null;
    double phos = 0.0;
    double nitrogen = 0.0;
    double pottasium=0.0;
    double area=0.0;
    boolean isOnLease=false;
    String landUseType=null;
    String leaseStart=null;
    String leaseEnd=null;
    public String getParcelId() {
		return parcelId;
	}
	public void setParcelId(String parcelId) {
		this.parcelId = parcelId;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getSoilType() {
		return soilType;
	}
	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}
	public String getCropType() {
		return cropType;
	}
	public void setCropType(String cropType) {
		this.cropType = cropType;
	}
	public double getPhos() {
		return phos;
	}
	public void setPhos(double phos) {
		this.phos = phos;
	}
	public double getNitrogen() {
		return nitrogen;
	}
	public void setNitrogen(double nitrogen) {
		this.nitrogen = nitrogen;
	}
	public double getPottasium() {
		return pottasium;
	}
	public void setPottasium(double pottasium) {
		this.pottasium = pottasium;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public String getLandUseType() {
		return landUseType;
	}
	public void setLandUseType(String landUseType) {
		this.landUseType = landUseType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isOnLease() {
		return isOnLease;
	}
	public void setOnLease(boolean isOnLease) {
		this.isOnLease = isOnLease;
	}
	public String getLeaseStart() {
		return leaseStart;
	}
	public void setLeaseStart(String leaseStart) {
		this.leaseStart = leaseStart;
	}
	public String getLeaseEnd() {
		return leaseEnd;
	}
	public void setLeaseEnd(String leaseEnd) {
		this.leaseEnd = leaseEnd;
	}
    
    
}
