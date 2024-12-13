package com.Polls.demo_polls.controller;

import com.Polls.demo_polls.exceptions.ResourceNotFoundException;
import com.Polls.demo_polls.model.Poll;
import com.Polls.demo_polls.service.PollService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
public class PollController {

    private final Logger logger = LoggerFactory.getLogger(PollController.class);

    @Autowired
    private PollService pollService;

    @GetMapping("/poll")
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        logger.info("Fetching all polls");
        Iterable<Poll> allPolls = pollService.getAllPolls();
        logger.debug("Retrieved {} polls", ((Collection<?>) allPolls).size());
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @PostMapping("/poll")
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        logger.info("Creating a new poll with question: {}", poll.getQuestion());
        poll = pollService.createPoll(poll);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);

        logger.debug("New poll created with ID: {}", poll.getId());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        logger.info("Fetching poll with ID: {}", pollId);
        Poll poll = pollService.getPollById(pollId);
        logger.debug("Retrieved poll: {}", poll);
        return new ResponseEntity<>(poll, HttpStatus.OK);
    }

    @PutMapping("/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        pollService.updatePoll(pollId, poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        pollService.deletePoll(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
