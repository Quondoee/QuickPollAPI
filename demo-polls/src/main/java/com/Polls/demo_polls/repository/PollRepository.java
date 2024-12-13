package com.Polls.demo_polls.repository;

import com.Polls.demo_polls.model.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {

}

