package Customer;

import org.jetbrains.annotations.NotNull;

public class Address {
    public static final Address EMPTY_ADDRESS = new Address(null);
    private int zipCode = 0;
    private String cityName = null;
    private String streetName = null;
    private int buildingNumber = 0;
    private char buildingLetter = '\0';
    private int apartmentNumber = 0;

    public Address(String address) {
        this(address, ",");
    }

    public Address(String address, String delimiters) {
        if(address != null) {
            setAddress(address.split('[' + delimiters + "][ \\t]"));
        }
    }

    private void setAddress(String @NotNull [] addressParts) {
        for(int i = 0; i < addressParts.length; i++) {
            addressParts[i] = addressParts[i].trim();
        }

        if(addressParts.length < 1)
            return;
        else {
            try {
                zipCode = Integer.parseInt(addressParts[0]);
            } catch(NumberFormatException ignore) {
            }
        }

        if(addressParts.length < 2)
            return;
        else if(addressParts[1].length() > 0) {
                cityName = addressParts[1];
            }

        if(addressParts.length < 3)
            return;
        else if(addressParts[2].length() > 0) {
                streetName = addressParts[2];
            }

        if(addressParts.length < 4)
            return;
        else {
            try {
                buildingNumber = Integer.parseInt(addressParts[3]);
            } catch(NumberFormatException ignore) {
            }
        }

        if(addressParts.length < 5)
            return;
        else if(addressParts[4].length() > 0) {
                buildingLetter = addressParts[4].charAt(0);
            }

        if(addressParts.length < 6)
            return;
        else {
            try {
                apartmentNumber = Integer.parseInt(addressParts[5]);
            } catch(NumberFormatException ignore) {
            }
        }
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public char getBuildingLetter() {
        return buildingLetter;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    @Override
    public String toString() {
        String s = "";
        if(zipCode != 0) {
            s = zipCode + ", ";
        }
        if(cityName != null) {
            s += "город " + cityName + ", ";
        }
        if(streetName != null) {
            s += "улица " + streetName + ", ";
        }
        if(buildingNumber != 0) {
            s += "дом " + buildingNumber + ", ";
        }
        if(buildingLetter != '\0') {
            s += "корпус " + buildingLetter + ", ";
        }
        if(apartmentNumber != 0) {
            s += "квартира " + apartmentNumber + ", ";
        }
        if(s.equals("")) {
            return "Address{}";
        }
        return "Address{" + s + "\b\b}";
    }
}
