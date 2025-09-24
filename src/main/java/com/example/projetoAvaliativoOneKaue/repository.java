package com.example.projetoAvaliativoOneKaue;

import com.example.projetoAvaliativoOneKaue.model.task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repository extends JpaRepository<task, Long> {
	
}
