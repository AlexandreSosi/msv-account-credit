package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;


@JsonPropertyOrder({ "name", "email", "phonenumber", "motherName", "bornDate",
        "salary" })
public class Account {

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phonenumber")
    private String phoneNumber;
    @JsonProperty("motherName")
    private String motherName;
    @JsonProperty("bornDate")
    private String bornDate;
    @JsonProperty("salary")
    private String salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(name, account.name) &&
                Objects.equals(email, account.email) &&
                Objects.equals(phoneNumber, account.phoneNumber) &&
                Objects.equals(motherName, account.motherName) &&
                Objects.equals(bornDate, account.bornDate) &&
                Objects.equals(salary, account.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber, motherName, bornDate, salary);
    }
}
