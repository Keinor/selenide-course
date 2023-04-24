package com.nastyabelova.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideSearchTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        open("https://github.com/");
    }

    @Test
    void shouldSearchSelenideWiki() {
        //Откройте страницу Selenide в Github
        $("[name=q]").setValue("Selenide").pressEnter();
        $$(".repo-list li").first().$("a").click();
        // Перейдите в раздел Wiki проекта
        $("a[href$=wiki]").click();
        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $$("[data-filterable-for=wiki-pages-filter] li").last().scrollTo().$("button").click();
        $$("[data-filterable-for=wiki-pages-filter] li").last().preceding(0).should(text("SoftAssertions"));
        // Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $$("[data-filterable-for=wiki-pages-filter] li").last().preceding(0).$("a").click();
        $x("//div[@class='markdown-body']/div").sibling(1).should(text("public class Tests"));
    }
}
