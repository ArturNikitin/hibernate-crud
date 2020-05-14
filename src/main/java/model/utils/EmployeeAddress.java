package model.utils;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class EmployeeAddress {
    private String country;
    private String city;
    private String street;
    private String postCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeAddress address = (EmployeeAddress) o;
        return postCode.equals(address.postCode) &&
                Objects.equals(country, address.country) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, postCode);
    }
}
