package program;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;

public class Data
{
    int confirmed;
    int recovered;
    int critical;
    int deaths;
    String lastChange;
    String lastUpdate;
    String countryName;


    public void collectGlobalData() throws JSONException, IOException, InterruptedException {
        HttpRequest requestTwo = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-19-data.p.rapidapi.com/totals"))
                .header("x-rapidapi-key", "f6aa8129a5msh0a737827b7ea042p182960jsn0c0a7e40ab8a")
                .header("x-rapidapi-host", "covid-19-data.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> responseTwo = HttpClient.newHttpClient().send(requestTwo, HttpResponse.BodyHandlers.ofString());
        //System.out.println(responseTwo.body());

        JSONArray k = new JSONArray(responseTwo.body());
        for (int i = 0; i < k.length(); i++) {
            confirmed = k.getJSONObject(i).getInt("confirmed");
            recovered = k.getJSONObject(i).getInt("recovered");
            critical = k.getJSONObject(i).getInt("critical");
            deaths = k.getJSONObject(i).getInt("deaths");
            //lastChange = k.getJSONObject(i).getString("lastChange");
            //lastUpdate = k.getJSONObject(i).getString("lastUpdate");


            System.out.println("\n" + "Confirmed = " + confirmed + "\n" + "Recovered = " + recovered + "\n" + "Critical = " + critical + "\n" + "Deaths = " + deaths
                    + "\n" + "Last Updated: " + lastChange);
        }
    }

    public void collectCountryData() throws JSONException, IOException, InterruptedException, ParseException {
        try {

        //Uses + replace(countryName))) to use the method to remove spaces.
        HttpRequest requestTwo = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-19-data.p.rapidapi.com/country?name=" + replace(countryName)))
                .header("x-rapidapi-key", "f6aa8129a5msh0a737827b7ea042p182960jsn0c0a7e40ab8a")
                .header("x-rapidapi-host", "covid-19-data.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> responseTwo = HttpClient.newHttpClient().send(requestTwo, HttpResponse.BodyHandlers.ofString());
        //System.out.println(responseTwo.body());

        JSONArray k = new JSONArray(responseTwo.body());
        for (int i = 0; i < k.length(); i++) {
            confirmed = k.getJSONObject(i).getInt("confirmed");
            recovered = k.getJSONObject(i).getInt("recovered");
            critical = k.getJSONObject(i).getInt("critical");
            deaths = k.getJSONObject(i).getInt("deaths");
            //lastChange = k.getJSONObject(i).getString("lastChange");
            //lastUpdate = k.getJSONObject(i).getString("lastUpdate");

            System.out.println("\n" + "Confirmed = " + confirmed + "\n" + "Recovered = " + recovered + "\n" + "Critical = " + critical + "\n" + "Deaths = " + deaths
                    + "\n" + "Last Updated: " + lastChange);
        }
        }
        catch(IllegalArgumentException e) {
            System.err.println("Wrong country name!");
        }
    }

    //Replaces spaces from country name with %20 if there is any.
    public static String replace(String s) {
        s = s.trim();
        s = s.replaceAll(" ", "%20");
        return s;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getCritical() {
        return critical;
    }

    public int getDeaths() {
        return deaths;
    }

    public String getLastChange() {
        return lastChange;
    }
    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setCountryName(String country) {
        this.countryName = country;
    }

}
