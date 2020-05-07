package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "billingZipcode", "billingStreet", "billingAddresNumber", "billingComplement",
        "billingNeighbourhood", "billingCity", "billingState" })
public class BillingAddress {

    @JsonProperty("billingZipcode")
    private String billingZipcode;
    @JsonProperty("billingStreet")
    private String billingStreet;
    @JsonProperty("billingAddresNumber")
    private String billingAddresNumber;
    @JsonProperty("billingComplement")
    private String billingComplement;
    @JsonProperty("billingNeighbourhood")
    private String billingNeighbourhood;
    @JsonProperty("billingCity")
    private String billingCity;
    @JsonProperty("billingState")
    private String billingState;

    public String getBillingZipcode() {
        return billingZipcode;
    }

    public void setBillingZipcode(String billingZipcode) {
        this.billingZipcode = billingZipcode;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getBillingAddresNumber() {
        return billingAddresNumber;
    }

    public void setBillingAddresNumber(String billingAddresNumber) {
        this.billingAddresNumber = billingAddresNumber;
    }

    public String getBillingComplement() {
        return billingComplement;
    }

    public void setBillingComplement(String billingComplement) {
        this.billingComplement = billingComplement;
    }

    public String getBillingNeighbourhood() {
        return billingNeighbourhood;
    }

    public void setBillingNeighbourhood(String billingNeighbourhood) {
        this.billingNeighbourhood = billingNeighbourhood;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }
}
