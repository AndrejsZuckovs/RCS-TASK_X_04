package com.example.demo.controller.v1;

import com.example.demo.model.Tweet;
import com.example.demo.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tweets")

public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping
    public List<Tweet> getAllTweets(){
        return tweetRepository.findAll();
    }
    //-------------------------------------------------------------------------------
    @PostMapping
    public Tweet createUser(@RequestBody Tweet tweet){
        return tweetRepository.save(tweet);
    }
    //-------------------------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable Long id){

        Optional<Tweet> tweet = tweetRepository.findById(id);

        return tweet.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    //-------------------------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Tweet> updateUser(@PathVariable Long id, @RequestBody Tweet tweet){

        return tweetRepository.findById(id)
                .map(existingTweet ->{
                    existingTweet.setContent(tweet.getContent());


                    tweetRepository.save(existingTweet);
                    return ResponseEntity.ok(existingTweet);
                })
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
    //-------------------------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Tweet> deleteUser(@PathVariable Long id) {
        if (tweetRepository.existsById(id)) {
            tweetRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //-------------------------------------------------------------------------------



}
