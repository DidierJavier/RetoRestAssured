package co.com.sofka.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

    @RunWith(CucumberWithSerenity.class)
    @CucumberOptions(
            snippets = CucumberOptions.SnippetType.CAMELCASE,
            features = {"src/test/resources/features/methodGet.feature"},
            glue = {"co.com.sofka.stepdefinitionget"},
            tags = ""
    )

    public class TestMethodGet {
}
