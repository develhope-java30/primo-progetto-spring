package com.example.primo_progetto_spring.classprogram.topic;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
//    List<TopicEntity> findAllTopics (Pageable pageable);
}
