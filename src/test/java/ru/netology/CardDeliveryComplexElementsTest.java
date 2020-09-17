package ru.netology;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryComplexElementsTest {
    @Test
    void shouldShowCityWithTwoLitters(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Ом");
        $$(".menu-item").find(exactText("Омск")).click();
        $("[name='name']").setValue("Кавендиш Генри");
        $("[name='phone']").setValue("+71112223344");
        $("[class=checkbox__box]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }

    @Test
    void shouldCardDeliveryWithCalendar(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Ом");
        $$(".menu-item").find(exactText("Омск")).click();
        $("[data-test-id=date] button").click();
        String nextWeekDay = LocalDate.now().plusWeeks(2).format(DateTimeFormatter.ofPattern("d"));
        String thisWeekDay = LocalDate.now().format(DateTimeFormatter.ofPattern("d"));
        if(Integer.valueOf(thisWeekDay) > Integer.valueOf(nextWeekDay)){
            $("div[data-step='1']").click();
        }
        $$(".calendar__day").find(exactText(nextWeekDay)).click();
        $("[name='name']").setValue("Кавендиш Генри");
        $("[name='phone']").setValue("+71112223344");
        $("[class=checkbox__box]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }
}
