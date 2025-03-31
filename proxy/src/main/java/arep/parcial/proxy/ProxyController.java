package arep.parcial.proxy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class ProxyController {
    private int aux=0;
    private static final String USER_AGENT = "Mozilla/5.0";

    @GetMapping("/collatzsequence")
    public ResponseEntity<?> collatz(@RequestParam Integer value) throws IOException {
        String ans= "";
        if(aux%2==0){
            ans= HttpConnection("http://ec2-34-224-214-215.compute-1.amazonaws.com:8080/collatzsequence?"+"value="+value);
            System.out.println("Se ejecuto MathServices1: " + ans);
        }else{
            ans= HttpConnection("http://ec2-54-198-53-74.compute-1.amazonaws.com:8080/collatzsequence?"+"value="+value);
            System.out.println("Se ejecuto MathServices2: " + ans);
        }
        this.aux+=1;
        return ResponseEntity.status(200).body(ans);
    }

    private String HttpConnection(String GET_URL) throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
            return "GET request not worked";
        }
    }

}

