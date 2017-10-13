
import com.google.gson.Gson;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/chatting/{username}")
public class ChatServerEndPoint {
    static Map<String, Session> sessionMap = new HashMap<String, Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String s) {

        try {
            session.getBasicRemote().sendText("Connection Successful");
            sessionMap.put(s, session);
            getNumberOfConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @OnMessage
    public void onMessage(Session session, String s) {
        try {
            Gson gson = new Gson();
            Msg msg = gson.fromJson(s, Msg.class);
            if (sessionMap.get(msg.getTo()) != null) {
                sessionMap.get(msg.getTo()).getBasicRemote().sendText(msg.getMessage());
                sessionMap.get(msg.getFrom()).getBasicRemote().sendText("Message Sent Successfully");
            } else {
                onError(session, new Throwable());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(Session session) {
        try {
            session.getBasicRemote().sendText("Connection closed");
            sessionMap.remove(session);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.getBasicRemote().sendText("Receiver doesn't exist");
        } catch (IOException e) {
            e.printStackTrace();
            throwable.getMessage();

        }


    }

    public void getNumberOfConnection() {
        for (Map.Entry m : sessionMap.entrySet()) {
            Session s = (Session) m.getValue();
            System.out.println(m.getKey() + " " + s.getId());
        }
    }


}
