package derry.assessmenttest.model;

public class Address {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo Geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public derry.assessmenttest.model.Geo getGeo() {
        return Geo;
    }

    public void setGeo(derry.assessmenttest.model.Geo geo) {
        Geo = geo;
    }
}
