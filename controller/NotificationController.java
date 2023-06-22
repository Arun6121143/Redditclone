package com.reddit.controller;

import com.reddit.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationController {

    @Autowired
    NotificationService notificationService;
    @GetMapping("/notify/upvote")
    public String upvoteNotification(@RequestParam(value="postId",required = false) Long postId,Long userId){
        notificationService.saveUpvoteNotification(postId,1L);
        return null;
    }

}
