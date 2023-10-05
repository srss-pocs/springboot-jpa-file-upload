package com.example.springbootjpafileupload.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.springbootjpafileupload.entity.FileData;
import com.example.springbootjpafileupload.repository.FileDataRepository;
import com.example.springbootjpafileupload.util.FileUtils;

@Service
public class FileDataService {

	@Autowired
	private FileDataRepository repository;

	public String uploadImage(MultipartFile file) throws IOException {

		FileData fileData = repository.save(FileData.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).fileData(FileUtils.compressImage(file.getBytes())).build());
		if (fileData != null) {
			return "file uploaded successfully : " + file.getOriginalFilename();
		}
		return null;
	}

	public byte[] downloadImage(String fileName) {
		Optional<FileData> dbImageData = repository.findByName(fileName);
		byte[] images = FileUtils.decompressImage(dbImageData.get().getFileData());
		return images;
	}

}
