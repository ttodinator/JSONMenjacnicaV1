package rs.ac.bg.fon.ai.JSONMenjacnica;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Main2 {
	
	private static final String BASE_URL = "http://api.currencylayer.com";
	private static final String API_KEY = "2e4baadf5c5ae6ba436f53ae5558107f";
	private static final String SOURCE = "USD";
	private static final String[] CURRENCIES = {"EUR","CHF","CAD"};
	
	public static void main(String[] args) {
		
		
		
		Transakcija transakcija = new Transakcija();
		transakcija.setPocetniIznos(100);
		Date date = new GregorianCalendar(2020,Calendar.AUGUST,27).getTime();	
		transakcija.setDatumTransakcije(date);
		transakcija.setIzvornaValuta(SOURCE);
		
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		String dat = f.format(transakcija.getDatumTransakcije());

		try (FileWriter file = new FileWriter("ostale_transakcije.json")){
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			for (String currency : CURRENCIES) {
				transakcija.setKrajnjaValuta(currency);
				URL url = new URL(
						BASE_URL + "/historical?date="+dat+"&access_key=" + API_KEY + "&source=" + SOURCE + "&currencies=" + currency);
			
				
				HttpURLConnection con = (HttpURLConnection) url.openConnection();

				con.setRequestMethod("GET");

				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

				JsonObject jobj = gson.fromJson(reader, JsonObject.class);

				if (jobj.get("success").getAsBoolean()) {
					double kurs = jobj.get("quotes").getAsJsonObject().get("USD"+currency).getAsDouble();
					transakcija.setKonvertovaniIznos(transakcija.getPocetniIznos()*kurs);
					System.out.println(kurs);
					System.out.println(transakcija);
					gson.toJson(transakcija, file);
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
