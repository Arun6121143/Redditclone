package com.reddit.service;

import com.reddit.entity.Notification;
import com.reddit.entity.User;
import com.reddit.repository.NotificationRepository;
import com.reddit.repository.PostRepository;
import com.reddit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    public void saveUpvoteNotification(Long postId, long userId) {
        Notification notification = new Notification();
        Long ownerId = postRepository.findById(postId).get().getUser().getUserId();
        User user = userRepository.findById(ownerId).get();
        List<Notification> userNotifications = new ArrayList<>();
        if(ownerId!=userId) {
            String username = userRepository.findById(userId).get().getUsername();
            notification.setNotificationMessage(username + " " + "upvote" + "your post");
            notification.setUser(userRepository.findById(ownerId).get());
            userNotifications.add(notificationRepository.save(notification));
            user.setNotifications(userNotifications);
            userRepository.save(user);
        }
    }
}
