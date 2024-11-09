package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"feature/"},
        glue = "src/test/java/step",
        tags = {"@pessoa"},
        dryRun = false,
        monochrome = true,
        plugin = {"pretty"})
public class Runner {
}