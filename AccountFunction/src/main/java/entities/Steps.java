package entities;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class Steps {

    private String nameStep;
    private String status;
    private String createdAt;

    public Steps(String nameStep, String status,String createdAt) {
        this.nameStep = nameStep;
        this.status = status;
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public Steps() {
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
