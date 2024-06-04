package selenium.api.actions;

import com.google.gson.JsonObject;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;
import selenium.api.APIRequest;
import selenium.constants.EndPoints;
import selenium.utils.FakerUtils;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CartApi extends APIRequest {
    Cookies cookie=new Cookies();

    public CartApi(Cookies cookie) {
        this.cookie = cookie;
    }

    public CartApi(){}

    public Cookies getCookies() {
        return cookie;
    }



    public Response addToCart(int productID, int quantity){
        Header header = new Header("content-type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String,Object> formparam= new HashMap<>();
        formparam.put("product_aku","");
        formparam.put("product_id",productID);
        formparam.put("quantity",quantity);
        if(cookie==null){cookie=new Cookies();}

        Response response = post(EndPoints.ADD_TO_CART.url, cookie,headers,formparam);

        if (response.getStatusCode()!=200){
            throw new RuntimeException("Failed to add product"+productID+"to the cart"+response.getStatusCode());
        }

        this.cookie=response.getDetailedCookies();
        return response;
    }
}
