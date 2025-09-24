package com.example.projetoAvaliativoOneKaue;

import com.example.projetoAvaliativoOneKaue.service.service;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class controler {

  private final service service;

  public controler(service service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<dto>> listAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<dto> getById(@PathVariable Long id) {
    return ResponseEntity.ok(service.findById(id));
  }

  @PostMapping
  public ResponseEntity<dto> create(@Valid @RequestBody dto dto) {
    dto created = service.create(dto);
    return ResponseEntity.created(URI.create("/api/tasks/" + created.getId())).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<dto> update(@PathVariable Long id, @Valid @RequestBody dto dto) {
    dto updated = service.update(id, dto);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
