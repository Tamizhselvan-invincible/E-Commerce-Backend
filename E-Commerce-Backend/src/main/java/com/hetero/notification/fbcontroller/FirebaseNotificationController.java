package com.hetero.notification.fbcontroller;

import com.google.firebase.internal.FirebaseService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hetero.notification.fbservice.FirebaseMessagingService;
import com.hetero.notification.notificationModel.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class FirebaseNotificationController {

    @Autowired
    FirebaseMessagingService firebaseMessagingService;

    @PostMapping("/send")
    public String sendNotificationByToken(@RequestBody NotificationMessage notificationMessage) {
return  firebaseMessagingService.sendNotificationByToken(notificationMessage);
    }

}
