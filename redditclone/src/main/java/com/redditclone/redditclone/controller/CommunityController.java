package com.redditclone.redditclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {
    @GetMapping("/view-community/page")
    public String viewCommunity(){
        return "create-community";
    }
}