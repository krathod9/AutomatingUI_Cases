package selenium.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import selenium.api.APIRequest;
import selenium.constants.EndPoints;
import selenium.objects.UserInfo;
import selenium.utils.ConfigLoader;
import selenium.utils.FakerUtils;

import java.util.HashMap;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class SignUpApi extends APIRequest {
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }
    private String fetchRegisterNonceValueUsingGroovy(){
        Response response=getAccount();
        return response.htmlPath().getString("**.findAll{it.@name=='woocommerce-register-nonce'}.@value");
    }
    private String fetchRegisterNonceValueUsingJsoup(){
        Response response=getAccount();
        Document document= Jsoup.parse(response.body().prettyPrint());
        Element element=document.selectFirst("#woocommerce-register-nonce");
        return element.attr("value");
    }
    public Response getAccount(){
        Cookies cookies=new Cookies();
        Response response= get(EndPoints.ACCOUNT.url,cookies);
        if(response.getStatusCode()!=200){
            throw new RuntimeException("Failed to fetch the account. HTTP status code "+response.getStatusCode());
        }
        return response;
    }
    public Response register(UserInfo userInfo){
        Cookies cookies=new Cookies();
        Header header = new Header("content-type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String,Object> formparam= new HashMap<>();
        formparam.put("username",userInfo.getUserID());
        formparam.put("password",userInfo.getPassword());
        formparam.put("email",userInfo.getEmail());
        formparam.put("woocommerce-register-nonce",fetchRegisterNonceValueUsingJsoup());
        formparam.put("register","Register");
        Response response= post(EndPoints.ACCOUNT.url, cookies,headers,formparam);
        if(response.getStatusCode()!=302){
            throw new RuntimeException("Failed to register the account. HTTP status code "+response.getStatusCode());
        }
        this.cookies=response.getDetailedCookies();
        return response;
    }
}
