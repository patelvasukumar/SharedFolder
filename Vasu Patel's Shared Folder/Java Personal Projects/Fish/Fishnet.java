package Test;


public class Fishnet {
	private double largeFish;
	private double mediumFish;
	
	Fishnet(double largeFish, double mediumFish){
		this.largeFish = largeFish;
		this.mediumFish = mediumFish;
	}
	
	public double getLargeFish() {
		return largeFish;
	}
	
	public void setLargeFish(double largeFish) {
		this.largeFish = largeFish;
	}
	
	public double getMediumFish() {
		return mediumFish;
	}
	
	public void setMediumFish(double mediumFish) {
		this.mediumFish = mediumFish;
	}
	
	public double calculateTotal() {
		largeFish = largeFish*15.50;
		mediumFish = mediumFish*5.50;
		
		return largeFish + mediumFish;
	}
}

