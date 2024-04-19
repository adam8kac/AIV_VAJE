package si.um.feri.aiv.rest;

import si.um.feri.aiv.rest.vao.Lokacija;
import si.um.feri.aiv.rest.vao.MSE;
import si.um.feri.aiv.rest.vao.Oseba;
import si.um.feri.aiv.rest.vao.Skupnost;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClient {
    static final URI MSE_URI = URI.create("http://localhost:8080/FirstLectures/api/mse/");
    static final URI SKUPNOST_URI = URI.create("http://localhost:8080/FirstLectures/api/skupnost/");

    HttpClient client = HttpClient.newBuilder().build();
    Jsonb jsonb= JsonbBuilder.create();

    void allMse() throws Exception {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(MSE_URI).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    void addMse(MSE o) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonb.toJson(o)))
                .header("Content-Type","application/json")
                .uri(MSE_URI)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response.body());
    }

    void allSkupnost() throws Exception {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(SKUPNOST_URI).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    void addSkupnost(Oseba o) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonb.toJson(o)))
                .header("Content-Type","application/json")
                .uri(SKUPNOST_URI)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response.body());
    }

    public static void main(String[] args) throws Exception {

        Lokacija l = new Lokacija(2,2);
        Oseba o =new Oseba("a","a","a");

        RestClient client=new RestClient();
        client.addMse(new MSE(l,12, o));
        client.allMse();

    }

}
