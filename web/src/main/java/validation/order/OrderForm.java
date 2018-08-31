package validation.order;

import com.es.core.model.order.Order;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class OrderForm {
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[0-9\\-\\+]{9,15}$")
    private String phone;

    private String additionalInformation;

    private String outOfStock;

    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NotNull String address) {
        this.address = address;
    }

    @NotNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NotNull String phone) {
        this.phone = phone;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(String outOfStock) {
        this.outOfStock = outOfStock;
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", additionalInformation='" + additionalInformation + '\'' +
                ", outOfStock='" + outOfStock + '\'' +
                '}';
    }
}
