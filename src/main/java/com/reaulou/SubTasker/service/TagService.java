package com.reaulou.SubTasker.service;

import com.reaulou.SubTasker.entity.Tag;
import com.reaulou.SubTasker.entity.Tag;
import com.reaulou.SubTasker.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    TagRepository repository;

    public List<Tag> getAllTags() {
        return repository.findAll();
    }

    public Optional<Tag> getTagById(Long id) {
        return repository.findById(id);
    }

    public Tag createTag(Tag Tag) {
        return repository.save(Tag);
    }

    public Tag updateTag(Long id, Tag updatedTag) {
        return repository.findById(id).map(Tag -> {
            Tag.setName(updatedTag.getName());
            return repository.save(Tag);
        }).orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    public void deleteTag(Long id) {
        repository.deleteById(id);
    }
}
