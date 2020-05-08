package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "nameStep", "status" })
public class StepsRest {
    @JsonProperty("nameStep")
    private String nameStep;
    @JsonProperty("status")
    private String status;
    @JsonProperty("status")
    private String createdAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getNameStep() {
        return nameStep;
    }

    public void setNameStep(String nameStep) {
        this.nameStep = nameStep;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StepsRest() {
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
