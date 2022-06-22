//package getRequest;

//import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import org.testng.annotations.Test;


public class AutomateAPI {

            @Test
            public void testCase1() {
                Response resp = RestAssured.get("http://api.weatherapi.com/v1/current.json?key=2fba48b51d3749f28b533507222604&q=95377");

                /*
                -----------To Print as a String ------------
                ResponseBody respBody = resp.getBody();
                System.out.println("Status is= " + respBody.asString());
                */

                JsonPath j= resp.jsonPath();  // response is converted to json and stored in j variable
                Float temperature = j.get("current.temp_f");

                //#1 Check if Temp_f is above 50. If >50 then assert should pass otherwise fail
                assertThat(Math.round(temperature), is(greaterThan(80)));

                //#2 If assert passes, then print the temp with city name and wind speed
                System.out.println("Temperature is= " + temperature);
                String city= j.get("location.name");
                System.out.println("City name is =" + city);
                //System.out.println("City name is= " +j.get("location.name"));
                Float wind_speed= j.get("current.wind_mph");
                System.out.println("Wind speed is= " + wind_speed);
            }
    }


