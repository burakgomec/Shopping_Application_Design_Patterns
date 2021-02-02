package com.burakgomec.shoppingapplication.MessagesBridge;

import android.content.Context;

public abstract class Message {


      protected IMessageSender messageSender;

      public Message(IMessageSender messageSender){
           this.messageSender = messageSender;
        }

      public abstract void showMessage(String text, Context context);


}
