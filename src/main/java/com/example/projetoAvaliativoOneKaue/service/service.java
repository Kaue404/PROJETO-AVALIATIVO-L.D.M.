package com.example.projetoAvaliativoOneKaue.service;

import com.example.projetoAvaliativoOneKaue.dto;
import java.util.List;

public interface service {
  dto create(dto dto);
  dto update(Long id, dto dto);
  void delete(Long id);
  dto findById(Long id);
  List<dto> findAll();
}
