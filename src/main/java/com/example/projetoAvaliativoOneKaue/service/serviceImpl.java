package com.example.projetoAvaliativoOneKaue.service;

import com.example.projetoAvaliativoOneKaue.dto;
import com.example.projetoAvaliativoOneKaue.repository;
import com.example.projetoAvaliativoOneKaue.exception.notFound;
import com.example.projetoAvaliativoOneKaue.model.task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public abstract class serviceImpl implements service {

  private final repository repo;

  public serviceImpl(repository repo) {
    this.repo = repo;
  }

  private dto toDTO(task t) {
    dto d = new dto();
    d.setId(t.getId());
    d.setName(t.getName());
    d.setDescription(t.getDescription());
    d.setStatus(t.getStatus());
    d.setNotes(t.getNotes());
    d.setCreatedAt(t.getCreatedAt());
    d.setUpdatedAt(t.getUpdatedAt());
    return d;
  }

  private task toEntity(dto d) {
    task t = new task();
    t.setName(d.getName());
    t.setDescription(d.getDescription());
    t.setStatus(d.getStatus());
    t.setNotes(d.getNotes());
    return t;
  }

  @Override
  public dto create(dto dto) {
    task saved = repo.save(toEntity(dto));
    return toDTO(saved);
  }

  @Override
  public dto update(Long id, dto dto) {
    task existing = repo.findById(id).orElseThrow(() ->
      new notFound("Task não encontrada com id: " + id));
    existing.setName(dto.getName());
    existing.setDescription(dto.getDescription());
    existing.setStatus(dto.getStatus());
    existing.setNotes(dto.getNotes());
    task saved = repo.save(existing);
    return toDTO(saved);
  }

  @Override
  public void delete(Long id) {
    if (!repo.existsById(id)) {
      throw new notFound("Task não encontrada com id: " + id);
    }
    repo.deleteById(id);
  }

  @Override
  public dto findById(Long id) {
    task t = repo.findById(id).orElseThrow(() ->
      new notFound("Task não encontrada com id: " + id));
    return toDTO(t);
  }

  @Override
  public List<dto> findAll() {
    return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
  }
}
