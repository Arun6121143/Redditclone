package com.redditclone.redditclone.service;

import com.redditclone.redditclone.entity.Draft;
import com.redditclone.redditclone.repository.DraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DraftService {
    @Autowired
    DraftRepository draftRepository;
    public void saveDraft(String title,String content) {
        Draft draft = new Draft();
        draft.setContent(content);
        draft.setTitle(title);
        draftRepository.save(draft);
    }

    public List<Draft> findAllDraftedPosts() {
        return draftRepository.findAll();
    }

    public void deleteDraftById(UUID draftId) {
        draftRepository.deleteById(draftId);
    }

    public Draft getDraftById(UUID draftId) {
       return draftRepository.findById(draftId).get();
    }

    public void updateDraftById(UUID draftId, String title, String content) {
        Draft existingDraft = draftRepository.findById(draftId).get();
        existingDraft.setTitle(title);
        existingDraft.setContent(content);
        draftRepository.save(existingDraft);
    }
}