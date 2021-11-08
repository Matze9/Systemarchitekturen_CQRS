package cqrs.queries.controller;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

@RestController
public class HelloController {

    @GetMapping("/")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {

        try {
            URL url = new URL("http://localhost:8080/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println("HEEEEEEE");

            return content.toString();
        } catch (Exception e){
            e.printStackTrace();
        }

        return "FAIL";
    }

    @PostMapping("/he")
    @ResponseBody
    public void sayHe(@RequestParam(value = "value1", defaultValue = "World") String value1, @RequestParam(value = "value2", defaultValue = "World") String value2) {

        System.out.println("HEEE");
        System.out.println(value1);
        System.out.println(value2);




    }
}
