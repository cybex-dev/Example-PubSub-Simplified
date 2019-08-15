import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class main {

    // The broker implementation is a list of topics, where each topic has an associated list of subscribers
    Map<String, List<Consumer<String>>> broker = new HashMap<>();

    public main() {
        // Add simple subscribers for /room/temp
        broker.put("/room/temp", new ArrayList<>());
        // get the topic list       Add a subscriber with a consumer interface (describes what should happen when the publisher sends a new message)
        broker.get("/room/temp").add(s -> System.out.printf(s));
        broker.get("/room/temp").add(s -> System.out.printf("New Message: " + s));

        // Add simple subscribers for /room/light
        broker.put("/room/light", new ArrayList<>());
        broker.get("/room/light").add(s -> System.out.printf("Light Changed: " + s));

        // Publish a new message to the /room/light topic
        newMessageToPublish("/room/light", "50 lumens");
    }

    public void newMessageToPublish(String topic, String message) {
        broker.get(topic).forEach(c -> c.accept(message));
    }
}
