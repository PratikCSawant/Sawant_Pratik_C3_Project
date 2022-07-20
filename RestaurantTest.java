import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    int initialMenuSize;

    @BeforeEach
    public void MockBeforeEach()
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        initialMenuSize = restaurant.getMenu().size();
        ;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        LocalTime openingTime = LocalTime.now().minusHours(1);
        LocalTime closingTime = LocalTime.now().plusHours(1);
        RestaurantService service = new RestaurantService();
        restaurant = service.addRestaurant("DEMO CAFE NAME","Mumbai",openingTime,closingTime);

        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        LocalTime openingTime = LocalTime.now().plusHours(1);
        LocalTime closingTime = LocalTime.now().plusHours(8);
        RestaurantService service = new RestaurantService();
        restaurant = service.addRestaurant("DEMO CAFE NAME","Mumbai",openingTime,closingTime);

        assertFalse(restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

     /*
    @Test
    public void total_price_will_be_calculated_for_menu_item_list_()
    {
        // add items in a list

        // calculate their total price and store in variable

        //assertequals check whether the variable total price value is same as the getTotalPrice() method created in Restaurant class

    }
    */

    @Test
    public void total_price_will_be_calculated_for_menu_item_list_()
    {
        List<String> itemList = new ArrayList<>();

        itemList.add("Sweet corn soup");
        itemList.add("Vegetable lasagne");

        int totalprice = 119+269;

        assertEquals(totalprice, restaurant.getTotalPrice(itemList));

    }

    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}