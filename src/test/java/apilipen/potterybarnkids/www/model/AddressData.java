package apilipen.potterybarnkids.www.model;

public class AddressData {



    private String fullName ;
    private String address;    //   private String address2;
    private String city;
    private  String state;
    private String zip;
    private String phone;


//    public AddressData    (String fullName, String address, String city,
//                                   String state, String zip, String phone) {
//        this.fullName = fullName;
//        this.address = address;
//        this.city = city;
//        this.state = state;
//        this.zip = zip;
//        this.phone = phone;
//    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }






    //   modified setters for fluent interface
    public AddressData withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public  AddressData withAddress(String address) {
        this.address = address;
        return this;
    }

    public AddressData withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressData withState(String state) {
        this.state = state;
        return this;
    }

    public AddressData withZip(String zip) {
        this.zip = zip;
        return this;
    }

    public AddressData withPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
