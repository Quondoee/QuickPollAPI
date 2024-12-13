package com.Polls.demo_polls.repository;

import com.Polls.demo_polls.model.OptionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<OptionModel, Long> {

}
