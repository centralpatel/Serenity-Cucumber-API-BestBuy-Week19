package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature",
            tags = "@UTILITIES")
public class UtilitiesRunner extends TestBase {
}
