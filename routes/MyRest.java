// camel-k: language=java
// camel-k: dependency=camel-jackson
// camel-k: dependency=mvn:com.github.jeremyrdavis.quarkus-cafe-demo:grubhub-cafe-core:1.5-SNAPSHOT 
// camel-k: dependency=mvn:com.github.jeremyrdavis:quarkus-cafe-demo:1.5-SNAPSHOT  
import org.apache.camel.builder.RouteBuilder;
import com.redhat.grubhub.cafe.domain.GrubHubOrder;
import org.apache.camel.model.rest.RestBindingMode;

public class MyRest extends RouteBuilder {
	@Override
	public void configure() throws Exception {
        rest()
            .post("/order")
            .type(GrubHubOrder.class)
            .consumes("application/json")
            .bindingMode(RestBindingMode.json)
            .route()
                .to("log:order?showAll=true&multiline=true")
                .transform().body(GrubHubOrder.class, b -> b.getOrderId());
    }
}