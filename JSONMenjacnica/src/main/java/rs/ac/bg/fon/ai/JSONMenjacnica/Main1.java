package rs.ac.bg.fon.ai.JSONMenjacnica;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Main1 {
	
	private static final String BASE_URL = "http://api.currencylayer.com";
	private static final String API_KEY = "2e4baadf5c5ae6ba436f53ae5558107f";
	private static final String SOURCE = "USD";
	private static final String CURRENCIES = "AUD";

	public static void main(String[] args) {
		
		Transakcija transakcija=new Transakcija();
		transakcija.setPocetniIznos(267);
		transakcija.setDatumTransakcije(new Date());
		transakcija.setIzvornaValuta(SOURCE);
		transakcija.setKrajnjaValuta(CURRENCIES);

		try (FileWriter file = new FileWriter("prva_transakcija.json")){
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			URL url = new URL(
					BASE_URL + "/live?access_key=" + API_KEY + "&source=" + SOURCE + "&currencies=" + CURRENCIES);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			JsonObject jobj = gson.fromJson(reader, JsonObject.class);

			if (jobj.get("success").getAsBoolean()) {
				double kurs = jobj.get("quotes").getAsJsonObject().get("USDAUD").getAsDouble();
				transakcija.setKonvertovaniIznos(transakcija.getPocetniIznos()*kurs);
				System.out.println(transakcija);
				gson.toJson(transakcija, file);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		

	}

}
