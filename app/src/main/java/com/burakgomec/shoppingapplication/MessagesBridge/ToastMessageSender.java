package com.burakgomec.shoppingapplication.MessagesBridge;

import android.content.Context;
import android.widget.Toast;

public class ToastMessageSender implements  IMessageSender{

    @Override
    public void sendMessage(String text,Context context) {
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
    }

}
