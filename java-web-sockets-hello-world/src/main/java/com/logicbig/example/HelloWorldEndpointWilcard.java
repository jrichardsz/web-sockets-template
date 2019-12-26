package com.logicbig.example;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import javax.websocket.server.PathParam;

@ServerEndpoint("/hello/{parameter}")
public class HelloWorldEndpointWilcard {

    public HelloWorldEndpointWilcard() {
        System.out.println("class loaded " + this.getClass());
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("parameter") String parameter) {
        System.out.printf("Session opened, id: %s%n", session.getId());
        System.out.println("parameter onOpen:"+parameter);
        try {
            session.getBasicRemote().sendText("Hi there, we are successfully connected.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("parameter") String parameter) {
        System.out.printf("Message received. Session id: %s Message: %s%n",
                session.getId(), message);
        System.out.println("parameter onMessage:"+parameter);
        try {
            session.getBasicRemote().sendText(String.format("We received your message: %s%n", message));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.printf("Session closed with id: %s%n", session.getId());
    }
}
