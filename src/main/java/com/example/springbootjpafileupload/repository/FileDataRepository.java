package com.example.springbootjpafileupload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootjpafileupload.entity.FileData;

import jakarta.transaction.Transactional;


public interface FileDataRepository extends JpaRepository<FileData, Long> {

	@Transactional
	Optional<FileData> findByName(String fileName);

}
