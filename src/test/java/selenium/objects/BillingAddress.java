package selenium.objects;

public class BillingAddress {
    private String fistName;
    private String lastName;
    private String address;
    private String postalCode;
    private String city;
    private String email;
    private String country;



    private String state;

    public BillingAddress() {    }
    public BillingAddress(String country,String state,String fistName, String lastName, String address, String postalCode, String city, String email) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.email = email;
        this.country=country;
        this.state=state;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFistName() {
        return fistName;
    }

    public BillingAddress setFistName(String fistName) {
        this.fistName = fistName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BillingAddress setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public BillingAddress setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress setEmail(String email) {
        this.email = email;
        return this;
    }
}
