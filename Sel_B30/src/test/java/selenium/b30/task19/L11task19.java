package selenium.b30.task19;

import org.junit.jupiter.api.Test;

public class L11task19 extends TestBase19 {

    @Test
    public void l11task19() {
        liteCart.open();//открыть главную страницу
        /*Добавить первый товар на странице в корзину 7 (для теста больше чем в 13 задании) раз*/
        for (int i = 0; i < 7; i++){
            liteCart.addProduct();
        }
        liteCart.openCart();//перейти в корзину
        liteCart.removeAllProducts();//удалить все из корзины
    }
}
