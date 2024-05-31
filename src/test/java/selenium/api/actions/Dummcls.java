package selenium.api.actions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import selenium.objects.UserInfo;
import selenium.utils.FakerUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

public class Dummcls {

    public static void main (String args[]) {
        UserInfo userInfo =new UserInfo().
                setUserID("demo"+ new FakerUtils().generateRandongNumber()).
                setEmail("demo121@email.com").
                setPassword("demopwd122");

        SignUpApi sapi=new SignUpApi();
        sapi.register(userInfo);
        System.out.println("Register Cookies"+sapi.getCookies());
////
////        sapi.register();
////        //System.out.println("Register Cookies"+sapi.getCookies());
////        CartApi cartApi=new CartApi(sapi.getCookies());
////        cartApi.addToCart(1215,2);
////        //System.out.println(cartApi.getCookie());
//
//        JsonParser parser=new JsonParser();
//        System.out.println(System.getProperty("user.dir"));
//        Object obj=parser.parse(new FileReader("C:\\Users\\krnra\\IdeaProjects\\AutomatingUI_Cases\\src\\test\\resources\\pizza.json"));
//        JsonObject jsonObject=(JsonObject) obj;
////        String name = jsonObject.get("Name").toString();
////        String course = jsonObject.get("Course").toString();
//        JsonArray pizzaArray=jsonObject.getAsJsonArray("pizza");
//        for(int i=0;i<pizzaArray.size();i++){
//            JsonObject  jsonObject1= pizzaArray.get(i).getAsJsonObject();
//            if(jsonObject1.get("piece").getAsInt()>5){
//                System.out.println(jsonObject1.get("name").toString());
//            }
//        }
   }
}
