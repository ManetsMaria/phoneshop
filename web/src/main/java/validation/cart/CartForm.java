package validation.cart;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CartForm {
    @Valid
    @NotNull
    private List<CartFormItem> cartFormItems;

    public CartForm(){
        cartFormItems = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "CartForm{" +
                "cartFormItems=" + cartFormItems +
                '}';
    }

    public void addQuantityField(Long phoneId, Long quantity){
        CartFormItem cartFormItem = new CartFormItem();
        cartFormItem.setPhoneId(phoneId);
        cartFormItem.setQuantity(quantity);
        cartFormItems.add(cartFormItem);
    }

    public void setCartFormItems(List<CartFormItem> cartFormItems) {
        this.cartFormItems = cartFormItems;
    }

    public List<CartFormItem> getCartFormItems() {
        return cartFormItems;
    }
}
