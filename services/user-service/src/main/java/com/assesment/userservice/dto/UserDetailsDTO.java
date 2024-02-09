package com.assesment.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetailsDTO implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String address;

    @Override
    public String toString() {
        return "UserDetailsDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
