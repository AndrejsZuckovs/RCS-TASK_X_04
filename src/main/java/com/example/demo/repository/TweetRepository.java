package com.example.demo.repository;

import com.example.demo.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TweetRepository extends JpaRepository<Tweet, Long> {



}
