package com.example.primo_progetto_spring.topic.repository;


import com.example.primo_progetto_spring.topic.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
//    List<TopicEntity> findAll (Pageable pageable);
}
