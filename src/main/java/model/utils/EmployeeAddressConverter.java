package model.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EmployeeAddressConverter implements AttributeConverter<EmployeeAddress, String> {

    @Override
    public String convertToDatabaseColumn(EmployeeAddress employeeAddress) {
        if (employeeAddress == null){
            return null;
        }
        return String.format("%s/%s/%s/%s", employeeAddress.getCountry(), employeeAddress.getCity(), employeeAddress.getStreet(), employeeAddress.getPostCode());
    }

    @Override
    public EmployeeAddress convertToEntityAttribute(String s) {
        if(s == null){
            return null;
        }

        String[] address = s.split("/");

        if(address.length != 4){
            throw new IllegalArgumentException("Failed to convert " + s + " to UserAddress");
        }
        return new EmployeeAddress(address[0], address[1], address[2], address[3]);
    }
}
