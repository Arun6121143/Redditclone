package com.redditclone.redditclone.controller;

import com.redditclone.redditclone.entity.Draft;
import com.redditclone.redditclone.entity.Media;
import com.redditclone.redditclone.service.DraftService;
import com.redditclone.redditclone.service.FileService;
import com.redditclone.redditclone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Controller
public class PostController {
    @Autowired
    FileService fileService;
    @Autowired
    PostService postService;
    @Autowired
    DraftController draftController;
    @Autowired
    DraftService draftService;

    @Value("${project.image}")
    private String path;
    @GetMapping("/view-post/page")
    public String viewPost(Model model){
        model.addAttribute("draftedPosts",draftService.findAllDraftedPosts().size());
        return "create-post";
    }

    @PostMapping("/save-post")
    public String fileUpload(@RequestParam(value = "draft",required = false)String draft,
                             @RequestParam(value = "title")String title,
                             @RequestParam(value = "image",required = false) List<MultipartFile> images,
                             @RequestParam(value = "content",required = false) String content,
                             @RequestParam(value = "url",required = false) String url,
                             @RequestParam(value = "poll",required = false) String poll,
                             @RequestParam(value = "draftId",required = false) UUID draftId,
                             Model model){
        if(draftId!=null){
            draftController.updateDraft(draftId,title,content);
            return "redirect:/draft";
        }
        else if(draft!=null && !draft.isEmpty()){
            draftController.saveDraftPost(title,content,model);
            List<Draft> draftPosts = draftService.findAllDraftedPosts();
            model.addAttribute("draftedPosts",draftPosts);
            return "draft";
        }
        else {
            if ((images != null && (!images.isEmpty())) && (url != null && (!url.isEmpty())) && (content != null
                    && (!content.isEmpty()))) {
                List<Media> savedMediaList = fileService.uploadImage(path, images);
                postService.savePost(title, savedMediaList, url, content);
                return "file-response";
            }
            if (images != null && (!images.isEmpty())) {
                List<Media> savedMediaList = fileService.uploadImage(path, images);
                postService.saveMedia(title, savedMediaList);
                return "file-response";
            }
            if (url != null && (!url.isEmpty())) {
                postService.savePostUrl(title, url);
                return "file-response";
            }
            if (content != null && (!content.isEmpty())) {
                postService.savePostContent(title, content);
                return "file-response";
            }
        }
        return "file-response";
    }
}