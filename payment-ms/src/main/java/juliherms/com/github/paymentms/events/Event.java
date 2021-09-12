package juliherms.com.github.paymentms.events;

import java.util.Date;
import java.util.UUID;

/**
 * Class responsible to represent event
 */
public interface Event {

    UUID getEventId();
    Date getDate();
}
