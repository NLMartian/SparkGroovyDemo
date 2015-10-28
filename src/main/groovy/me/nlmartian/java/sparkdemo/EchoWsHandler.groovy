package me.nlmartian.java.sparkdemo

import org.eclipse.jetty.websocket.api.Session
import org.eclipse.jetty.websocket.api.annotations.*
import org.slf4j.LoggerFactory

/**
 * Created by nlmartian on 10/9/15.
 */
@WebSocket
class EchoWsHandler {
    static logger = LoggerFactory.getLogger(EchoWsHandler.class)

    private Session session

    @OnWebSocketConnect
    public void connect(Session session) {
        this.session = session
    }

    @OnWebSocketClose
    public void closed(int statusCode, String reason) {
        this.session = null
    }

    @OnWebSocketMessage
    public void message(String message) throws IOException {
        logger.info("Got: " + message)
        session.getRemote().sendString(message)
    }


}
