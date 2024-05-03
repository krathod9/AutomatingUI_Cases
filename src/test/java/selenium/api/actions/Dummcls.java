package selenium.api.actions;

public class Dummcls {

    public static void main (String args[]){
        SignUpApi sapi=new SignUpApi();

        sapi.register();
        //System.out.println("Register Cookies"+sapi.getCookies());
        CartApi cartApi=new CartApi(sapi.getCookies());
        cartApi.addToCart(1215,2);
        //System.out.println(cartApi.getCookie());

    }
}
