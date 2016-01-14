package yoffe.weather;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather16 {

	private DailyWeather[] weather;
	private Temperature temp;
	private double rain;
	private long dt;

	public DailyWeather[] getWeather() {
		return weather;
	}

	public Temperature getTemp() {
		return temp;
	}

	public double getRain() {
		return rain;
	}

	public Date getDt() {
		Date date = new Date(dt * 1000);
		return date;
	}

}
