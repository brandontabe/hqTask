package za.co.hqtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.hqtech.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}