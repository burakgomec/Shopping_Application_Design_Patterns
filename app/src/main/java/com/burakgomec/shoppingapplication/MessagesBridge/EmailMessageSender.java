package com.burakgomec.shoppingapplication.MessagesBridge;

import android.content.Context;

public class EmailMessageSender implements IMessageSender {

    @Override
    public void sendMessage(String text, Context context) {
        System.out.println(text);
    }
}
