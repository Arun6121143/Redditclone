package com.redditclone.redditclone.repository;

import com.redditclone.redditclone.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<Media,Long> {
}