package com.reaulou.SubTasker.service;

import com.reaulou.SubTasker.entity.Tag;
import com.reaulou.SubTasker.entity.Task;
import com.reaulou.SubTasker.repository.TagRepository;
import com.reaulou.SubTasker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;
    @Autowired
    private TagRepository tagRepository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return repository.findById(id);
    }

    public List<Task>getTasksByTag(String tagName) {
        return getTasksByTag(new ArrayList<String>() {
            {
                add(tagName);
            }
        });
    }
    public List<Task> getTasksByTag(List<String> tagNames) {
        return repository.findByTags_NameIn(tagNames);
    }

    // Add a task with tags
    public Task createTask(Task task) {
        Set<Tag> managedTags = new HashSet<>();

        for (Tag tag : task.getTags()) {
            Tag existingTag = tagRepository.findByName(tag.getName());
            if (existingTag != null) {
                // Use the existing tag if found
                managedTags.add(existingTag);
            } else {
                // Save new tag only if it doesn't exist
                Tag savedTag = tagRepository.save(tag);
                managedTags.add(savedTag);
            }
        }

        // Replace the task's tags with managed tags
        task.setTags(managedTags);

        return repository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        return repository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            return repository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
