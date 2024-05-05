package germanmolf.hergenc.shared.infraestructure;

import germanmolf.hergenc.shared.infraestructure.event.MySqlDomainEventsConsumer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public final class AppEventListener {

    private final MySqlDomainEventsConsumer mySqlDomainEventsConsumer;

    public AppEventListener(MySqlDomainEventsConsumer consumer) {
        this.mySqlDomainEventsConsumer = consumer;
    }

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        startMySqlDomainEventsConsumer();
    }

    private void startMySqlDomainEventsConsumer() {
        Thread consumerProcess = new Thread(mySqlDomainEventsConsumer::consume);
        consumerProcess.start();
    }
}
