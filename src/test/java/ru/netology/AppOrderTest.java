package ru.netology;


import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class AppOrderTest {
        @Test
        void shouldTestHappyPath1() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Олег-Олег-   Йцукен      ДДДДДДДДДдддддвтсттсррапа     -----");
                $("[type='tel']").setValue("+79995552211");
                $("[data-test-id='agreement']").click();
                $(new ByText("Продолжить")).click();
                $("[data-test-id=order-success]").waitUntil(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."), 2000);
        }
        @Test
        void shouldTestHappyPath2() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Олег");
                $("[type='tel']").setValue("+00000000000");
                $("[data-test-id='agreement']").click();
                $(new ByText("Продолжить")).click();
                $("[data-test-id=order-success]").waitUntil(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."), 2000);
        }
        @Test
        void shouldTestHappyPath3() {
                open("http://localhost:9999");
                $("[type='text']").setValue("О        л             е                    г");
                $("[type='tel']").setValue("+12345678901");
                $("[data-test-id='agreement']").click();
                $(new ByText("Продолжить")).click();
                $("[data-test-id=order-success]").waitUntil(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."), 2000);
        }
        @Test
        void shouldTestHappyPath4() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Олег-Олег");
                $("[type='tel']").setValue("+99999999999");
                $("[data-test-id='agreement']").click();
                $(new ByText("Продолжить")).click();
                $("[data-test-id=order-success]").waitUntil(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."), 2000);
        }
        @Test
        void shouldTestPathIfInvalidName() {
                open("http://localhost:9999");
                $("[type='text']").setValue("OOOOO)))))#$%ddddddddd235");
                $("[type='tel']").setValue("+79995552211");
                $("[data-test-id='agreement']").click();
                $(new ByText("Продолжить")).click();
                $$(".input__sub").find(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."), 2000);

        }
        @Test
        void shouldTestPathIfEmptyName() {
                open("http://localhost:9999");
                $("[type='text']").setValue("");
                $("[type='tel']").setValue("+79995552211");
                $("[data-test-id='agreement']").click();
                $(new ByText("Продолжить")).click();
                $$("[data-test-id='name'] .input__sub").find(exactText("Поле обязательно для заполнения")).waitUntil(text("Поле обязательно для заполнения"), 2000);

        }
        @Test
        void shouldTestPathIfInvalidPhoneAfterLimit() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Олег");
                $("[type='tel']").setValue("179995552211346587980");
                $("[data-test-id='agreement']").click();
                $(new ByText("Продолжить")).click();
                $$(".input__sub").find(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."), 2000);

        }
        @Test
        void shouldTestPathIfValidPhoneNumberWithoutPlus() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Олег");
                $("[type='tel']").setValue("9231002323");
                $("[data-test-id='agreement']").click();
                $(new ByText("Продолжить")).click();
                $$(".input__sub").find(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."), 2000);

        }
        @Test
        void shouldTestPathIfInvalidPhoneUnderLimitDown() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Олег");
                $("[type='tel']").setValue("17");
                $("[data-test-id='agreement']").click();
                $(new ByText("Продолжить")).click();
                $$(".input__sub").find(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).waitUntil(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."), 2000);

        }
        @Test
        void shouldTestPathIfEmptyPhoneNumber() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Олег");
                $("[type='tel']").setValue("");
                $("[data-test-id='agreement']").click();
                $(new ByText("Продолжить")).click();
                $$("[data-test-id='phone'] .input__sub").find(exactText("Поле обязательно для заполнения")).waitUntil(text("Поле обязательно для заполнения"), 2000);

        }
        @Test
        void shouldTestPathWhithoutCheckbox() {
                open("http://localhost:9999");
                $("[type='text']").setValue("Олег-Олег-   Йцукен      ДДДДДДДДДдддддвтсттсррапа     -----");
                $("[type='tel']").setValue("+79995552211");
                $(new ByText("Продолжить")).click();
                $("[data-test-id='agreement'] .checkbox__text").waitUntil(text("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"), 2000);
        }
        }