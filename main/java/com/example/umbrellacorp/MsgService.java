package com.example.umbrellacorp;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Iterator;
import java.util.Map;

public class MsgService extends FirebaseMessagingService
{
    void printMessage(Map<String,String> payload)
    {
        Iterator<String> it = payload.keySet().iterator();
        while (it.hasNext())
        {
            String key =it.next();
            String msg = payload.get(it);
        }
    }
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        Map<String,String> payload = remoteMessage.getData();
        printMessage(payload);
    }
}
