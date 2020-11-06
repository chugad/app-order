package ru.netology;


import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class AppOrderTest {
        @Test
        void shouldTestHappyPath() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Дудин Олег");
                $("[type='tel']").setValue("+79995552211");
                $("[data-test-id='agreement']").click();
                $(byText("Продолжить")).click();
                $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        }
        @Test
        void shouldTestPathIfEmptyName() {
                open("http://localhost:9999");
                $("[type='text']").setValue("");
                $("[type='tel']").setValue("+79995552211");
                $("[data-test-id='agreement']").click();
                $(byText("Продолжить")).click();
                $$("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactTexts("Поле обязательно для заполнения"));

        }
        @Test
        void shouldTestPathIfInvalidPhoneAfterLimit() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Дудин Олег");
                $("[type='tel']").setValue("179995552211346587980");
                $("[data-test-id='agreement']").click();
                $(byText("Продолжить")).click();
                $$("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactTexts("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }
        @Test
        void shouldTestPathIfValidPhoneNumberWithoutPlus() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Дудин Олег");
                $("[type='tel']").setValue("9231002323");
                $("[data-test-id='agreement']").click();
                $(byText("Продолжить")).click();
                $$("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactTexts( "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }
        @Test
        void shouldTestPathIfInvalidPhoneUnderLimitDown() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Дудин Олег");
                $("[type='tel']").setValue("17");
                $("[data-test-id='agreement']").click();
                $(byText("Продолжить")).click();
                $$("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactTexts( "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }
        @Test
        void shouldTestPathIfEmptyPhoneNumber() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Дудин Олег");
                $("[type='tel']").setValue("");
                $("[data-test-id='agreement']").click();
                $(byText("Продолжить")).click();
                $$("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactTexts("Поле обязательно для заполнения"));

        }
        @Test
        void shouldTestPathWithOutCheckbox() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Дудин Олег");
                $("[type='tel']").setValue("+79995552211");
                $(byText("Продолжить")).click();
                $("[data-test-id='agreement'].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
        }
}