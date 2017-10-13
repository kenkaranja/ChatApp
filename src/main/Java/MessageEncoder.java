import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {
    private static Gson gson = new Gson();

    public String encode(Message message) throws EncodeException {
        message.setContent("ekmksmkmk");
        message.setFrom("ken");
        message.setTo("karanja");
        return gson.toJson(message);
    }

    public void init(EndpointConfig endpointConfig) {

    }

    public void destroy() {

    }
}
