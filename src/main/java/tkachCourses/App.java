package tkachCourses;

import java.util.Map;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        super();
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Client client = ctx.getBean(Client.class);
        System.out.println(client.getGreeting());

        app.logEvents(ctx);
        ctx.close();
    }

    private void logEvents(ConfigurableApplicationContext ctx) throws IOException {
        Event event = ctx.getBean(Event.class);
        logEvent(EventType.INFO,event,"Some event for user 1");
        event = ctx.getBean(Event.class);
        logEvent(EventType.ERROR, event,"And one event for user 1");
        event = ctx.getBean(Event.class);
        logEvent(EventType.INFO, event,"Another event for user 1");
        event = ctx.getBean(Event.class);
        logEvent(EventType.ERROR, event,"Some event for user 3");
    }

    private void logEvent(EventType eventType, Event event, String msg) throws IOException {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }
}
