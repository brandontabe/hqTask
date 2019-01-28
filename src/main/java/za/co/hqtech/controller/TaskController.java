package za.co.hqtech.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.hqtech.entity.Task;
import za.co.hqtech.repository.TaskRepository;
import za.co.util.DateUtil;

@RestController
@RequestMapping("/v1/")
public class TaskController {

	@Autowired
	private TaskRepository taskRepo;

	@CrossOrigin
	@GetMapping(path = "task")
	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}

	@CrossOrigin
	@GetMapping(path = "task/{id}")
	public Task getTaskById(@PathVariable Integer id) {
		return taskRepo.findById(id).get();
	}

	@CrossOrigin
	@PostMapping(path = "task")
	public Task createTask(@Valid @RequestBody Task task) {
		return taskRepo.save(task);
	}

	@CrossOrigin
	@PutMapping(path = "task")
	public Boolean updateTask(@Valid @RequestBody Task task) {
		Optional<Task> t = taskRepo.findById(task.getId());
		System.out.println(".............................."+task);
		if (t.isPresent()) {
			Task updatedTask = t.get();
			updatedTask.setDescription(task.getDescription());
			updatedTask.setDueDate(task.getDueDate());
			taskRepo.save(updatedTask);
			return true;
		}
		return false;
	}

	@CrossOrigin
	@GetMapping(path = "task/remove/{id}")
	public Boolean deleteTask(@PathVariable Integer id) {
		Optional<Task> t = taskRepo.findById(id);
		if (t.isPresent()) {
			taskRepo.delete(t.get());
			return true;
		}
		return false;

	}
	@CrossOrigin
	@GetMapping(path = "task/status/{id}")
	public Long checkTaskStatus(@PathVariable Integer id) {
		String taskDate = taskRepo.findById(id).get().getDueDate().toString();
		return DateUtil.daysBetween(taskDate);
	}

}
