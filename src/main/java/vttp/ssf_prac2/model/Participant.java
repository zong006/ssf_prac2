package vttp.ssf_prac2.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Participant {
    @NotNull(message = "Required Field.")
    @Size(min=5, max=25, message = "Name must be between 5 to 25 characters long.")
    private String name;

    @Past(message = "date of birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotNull(message = "Required Field.")
    @Email(message = "Must be valid email format: <emailname>@<domain_name>")
    @Size(max = 50, message = "Up to a maximum of 50 characters")
    private String email;

    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Phone number must starts with 8 or 9 follow by 7 digits")
    private String phoneNumber;

    @Min(value=1, message = "Minimum request of 1 ticket.")
    @Max(value=3, message = "Maximum request of 3 tickets.")
    private int numTickets;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
    public int getNumTickets() {
        return numTickets;
    }
    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }
    @Override
    public String toString() {
        return "Participant [name=" + name + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", phoneNumber="
                + phoneNumber + ", numTickets=" + numTickets + "]";
    }
    
}
