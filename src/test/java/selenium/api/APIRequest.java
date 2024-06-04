package selenium.api;

import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import selenium.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class APIRequest extends SpecBuilder{
        public static Response get(String endPoint, Cookies cookies){
            return given(getReqSpec()).
                        cookies(cookies).
                    when().
                        get(endPoint).
                    then().
                        spec(getRespSpec()).
                        extract().
                        response();
        }

        public static Response post(String endPoint, Cookies cookies, Headers headers,
                                    HashMap<String,Object> formparam){
            return given(getReqSpec()).
                        headers(headers).
                        formParams(formparam).
                        cookies(cookies).
                    when().
                        post(endPoint).
                    then().
                        spec(getRespSpec()).
                        extract().
                        response();

        }

}
