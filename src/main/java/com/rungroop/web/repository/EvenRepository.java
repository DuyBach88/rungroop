package com.rungroop.web.repository;

import com.rungroop.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenRepository extends JpaRepository<Event, Long> {

}
