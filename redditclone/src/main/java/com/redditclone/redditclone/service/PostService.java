package com.redditclone.redditclone.service;

import com.redditclone.redditclone.entity.Media;
import com.redditclone.redditclone.entity.Post;
import com.redditclone.redditclone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    public void savePostUrl(String title, String url) {
        Post post = new Post();
        post.setTitle(title);
        post.setUrl(url);
        postRepository.save(post);
    }

    public void savePostContent(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setIsPublished(true);
        postRepository.save(post);
    }

    public void saveMedia(String title, List<Media> savedMediaList) {
        Post post = new Post();
        post.setMediaList(savedMediaList);
        post.setTitle(title);
        post.setIsPublished(true);
        postRepository.save(post);
    }

    public void savePost(String title, List<Media> savedMediaList, String url, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setUrl(url);
        post.setMediaList(savedMediaList);
        post.setIsPublished(true);
        postRepository.save(post);
    }
}