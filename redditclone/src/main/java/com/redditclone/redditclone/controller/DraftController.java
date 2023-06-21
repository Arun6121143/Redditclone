package com.redditclone.redditclone.controller;

import com.redditclone.redditclone.entity.Draft;
import com.redditclone.redditclone.service.DraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class DraftController {
    @Autowired
    DraftService draftService;

    @GetMapping("/draft")
    public String viewDraft(Model model) {
        List<Draft> draftPosts = draftService.findAllDraftedPosts();
        model.addAttribute("draftedPosts", draftPosts);
        return "draft";
    }

    @PostMapping("/draft-posts")
    public String saveDraftPost(String title, String content, Model model) {
        draftService.saveDraft(title, content);
        return "redirect:/draft";
    }

    @GetMapping("/delete/draft")
    public String deleteDraftPost(@RequestParam(value = "draftId") UUID draftId) {
        draftService.deleteDraftById(draftId);
        return "redirect:/draft";
    }

    @GetMapping("/edit/draft")
    public String editDraft(@RequestParam(value = "draftId") UUID draftId, Model model) {
        Draft draft = draftService.getDraftById(draftId);
        model.addAttribute("draftedPosts", draftService.findAllDraftedPosts().size());
        model.addAttribute("editDraft", draft);
        return "edit";
    }

    @GetMapping("/update/draft")
    public void updateDraft(UUID draftId, String title, String content) {
        draftService.updateDraftById(draftId, title, content);
    }
}
