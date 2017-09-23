package tkachCourses;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.logEvents(ctx);
        ctx.close();
    }

    private void logEvents(ConfigurableApplicationContext ctx) throws IOException {
        Event event = ctx.getBean(Event.class);
        logEvent(event,"Some event for user 1");
        event = ctx.getBean(Event.class);
        logEvent(event,"Some event for user 1");
        event = ctx.getBean(Event.class);
        logEvent(event,"Some event for user 1");
        event = ctx.getBean(Event.class);
        logEvent(event,"Some event for user 1");
    }

    private void logEvent(Event event, String msg) throws IOException {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }
}
