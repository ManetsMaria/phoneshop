package validation.cart;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CartFormItem {

    @NotNull(message = "input number")
    @Min(1L)
    private Long quantity;

    private Long phoneId;

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    @Override
    public String toString() {
        return "CartFormItem{" +
                "quantity=" + quantity +
                ", phoneId=" + phoneId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartFormItem that = (CartFormItem) o;
        return Objects.equals(phoneId, that.phoneId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(phoneId);
    }
}
