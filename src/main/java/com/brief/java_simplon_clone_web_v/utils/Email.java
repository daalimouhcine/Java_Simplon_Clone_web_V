package com.brief.java_simplon_clone_web_v.utils;

import com.brief.java_simplon_clone_web_v.entities.BriefsEntity;
import com.brief.java_simplon_clone_web_v.entities.StudentsEntity;
import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import services.Courier;
import services.SendService;

import java.io.IOException;
import java.util.HashMap;

public class Email {

    public static void sendEmail(BriefsEntity brief, StudentsEntity student) {

        Courier.init("pk_prod_Q02ESMDGP3MRBNMWTZ3Q1XN0A244");

        SendEnhancedRequestBody sendEnhancedRequestBody = new SendEnhancedRequestBody();
        SendRequestMessage sendRequestMessage = new SendRequestMessage();
        HashMap<String, String> to = new HashMap<String, String>();
        to.put("email", student.getEmail());
        sendRequestMessage.setTo(to);


        HashMap<String, String> content = new HashMap<String, String>();
        content.put("title", "Simplon Clone : Nouveau Brief");
        content.put("body", "Hello " + student.getFullname() + ",\nYour teacher is assign the brief: " + brief.getTitle() + " for your promo.\n" + "Brief Details:\nTitle: " + brief.getTitle() +".\nDescription: " + brief.getDescription() + ".\nTechnologies: " + brief.getTechnologies());
        sendRequestMessage.setContent(content);

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("joke", "Why do Java programmers have to wear glasses? Because they don't C#");
        sendRequestMessage.setData(data);
        sendEnhancedRequestBody.setMessage(sendRequestMessage);

        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(sendEnhancedRequestBody);
            System.out.println(response);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
