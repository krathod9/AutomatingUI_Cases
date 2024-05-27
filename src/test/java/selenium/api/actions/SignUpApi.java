package selenium.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import selenium.utils.FakerUtils;

import java.lang.reflect.Array;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpApi {

    private Cookies cookies;
    public Cookies getCookies(){return cookies;}

    public String fetchRegisterNonceValueUsingGroovy(){
        Response response=getAccount();
        return response.htmlPath().getString("**.findall { it.@name == 'woocommerce-register-nonce' }.@value" );
    }

    public String fetchRegisterNonceValueUsingJsoup(){
        Response response=getAccount();
        Document doc= Jsoup.parse(response.body().asPrettyString());
        Element element=doc.selectFirst("#woocommerce-register-nonce");
        return element.attr("value");
    }
    public Response getAccount(){
        Cookies cookie=new Cookies();
        Response response = given().
                baseUri("https://askomdch.com/").cookies(cookie).
                when().
                get("/account").
                then().log().all().
                extract().response();

        if (response.getStatusCode()!=200){
            throw new RuntimeException("Failed to fetch the account HTTP Code"+response.getStatusCode());
        }
        return response;
    }

    public Response register(){
        Cookies cookie=new Cookies();
        Header header = new Header("content-type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String,String> formparam= new HashMap<>();
        formparam.put("username","testser"+new FakerUtils().generateRandongNumber());
        formparam.put("password","test");
        formparam.put("email","test@t1.com");
        formparam.put("woocommerce-register-nonce",fetchRegisterNonceValueUsingJsoup());
        formparam.put("register","Register");
        Response response = given().
                baseUri("https://askomdch.com/").
                headers(headers).
                formParams(formparam).
                cookies(cookie).
                when().
                post("/account").
                then().
                log().all().
                extract().
                response();

//        if (response.getStatusCode()!=302){
//            throw new RuntimeException("Failed to register the account HTTP Code"+response.getStatusCode());
//        }

        this.cookies=response.getDetailedCookies();
        //System.out.println(response.getHeaders());
        //System.out.println(response.getBody().toString());
        //System.out.println((char[]) response.jsonPath().get("fragments"));
        System.out.println(response.getStatusCode());
        return response;
    }
}
