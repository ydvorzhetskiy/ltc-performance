import org.junit.jupiter.api.Test;
import org.loadtest4j.LoadTester;
import org.loadtest4j.Request;
import org.loadtest4j.Result;
import org.loadtest4j.factory.LoadTesterFactory;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StressTest {

    private static final LoadTester loadTester = LoadTesterFactory.getLoadTester();

    @Test
    public void accessUrl() {
        List<Request> requests = List.of(Request.get("/persons")
                .withHeader("Accept", "application/json")
                .withQueryParam("status", "available"));

        Result result = loadTester.run(requests);

        assertThat(result.getResponseTime().getPercentile(90))
                .isLessThanOrEqualTo(Duration.ofMillis(5000));
    }
}
