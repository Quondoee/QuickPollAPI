package com.Polls.demo_polls.service;

import com.Polls.demo_polls.exceptions.ResourceNotFoundException;
import com.Polls.demo_polls.model.Poll;
import com.Polls.demo_polls.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollService {
    @Autowired
    private PollRepository pollRepository;

    public Iterable<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public Poll getPollById(Long pollId) throws ResourceNotFoundException {
        return pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll with ID " + pollId + " not found"));
    }

    public Poll updatePoll(Long pollId, Poll poll) throws ResourceNotFoundException {
        verifyPoll(pollId);
        poll.setId(pollId);
        return pollRepository.save(poll);
    }

    public void deletePoll(Long pollId) throws ResourceNotFoundException {
        verifyPoll(pollId);
        pollRepository.deleteById(pollId);
    }

    private void verifyPoll(Long pollId) throws ResourceNotFoundException {
        if (!pollRepository.existsById(pollId)) {
            throw new ResourceNotFoundException("Poll with ID " + pollId + " not found");
        }
    }
}
