package services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.twilio.Twilio;

/**
 *
 * @author 21627
 */
public class Smsapi {
     public static final String ACCOUNT_SID = "AC2ebedfd3ee1e0b41e0065f6166bbd642";
    public static final String AUTH_TOKEN = "e5b955cde2e3dd6a9aaa545171d01fab";

    /**
     * @param num     
     * @param msg     
     */
    public static void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(num),new PhoneNumber("+18646592326"), msg).create();

        System.out.println(message.getSid());
//
    }
    
}
