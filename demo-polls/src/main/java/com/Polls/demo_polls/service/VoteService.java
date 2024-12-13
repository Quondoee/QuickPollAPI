package com.Polls.demo_polls.service;

import com.Polls.demo_polls.model.Vote;
import com.Polls.demo_polls.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    public Iterable<Vote> getVotesByPoll(Long pollId) {
        return voteRepository.findByPoll(pollId);
    }

    public Vote createVote(Vote vote) {
        return voteRepository.save(vote);
    }
}
