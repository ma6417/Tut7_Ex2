import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MakeGETCall {


    public static void main(String[] array) {
        try {
            URL myURL = new URL("https://eczema-app.herokuapp.com/eczemadatabase");
            HttpsURLConnection conn = (HttpsURLConnection) myURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(myURL.openStream()));

            String inputLine;
            // Read the body of the response

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        } catch (Exception e) {
        }
        makePostRequest();
    }

    public static void makePostRequest() {
        // Set up the body data
        try {

            Patient p = new Patient("Meg");
            p.phoneNumber = "07561302021";

            Gson gson = new Gson();
            String jsonString = gson.toJson(p);
//            System.out.println(jsonString);

            String message = jsonString;
            byte[] body = message.getBytes(StandardCharsets.UTF_8);

            URL myURL = new URL("https://eczema-app.herokuapp.com/eczemadatabase");
            HttpsURLConnection conn = (HttpsURLConnection) myURL.openConnection();


            // Set up the header
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "text/html");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(body.length));
            conn.setDoOutput(true);

            try (OutputStream outputStream = conn.getOutputStream()) {
                outputStream.write(body, 0, body.length);
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

            String inputLine;

            // Read the body of the response
            while ((inputLine = bufferedReader.readLine()) != null) {
                System.out.println(inputLine);
            }
            bufferedReader.close();
        } catch (Exception e) {

        }
    }

}
