package com.burakgomec.shoppingapplication.MessagesBridge;

import android.content.Context;

public class ToastMessage extends Message{

    public ToastMessage(IMessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void showMessage(String text, Context context) {
        super.messageSender.sendMessage(text,context);
    }
}
