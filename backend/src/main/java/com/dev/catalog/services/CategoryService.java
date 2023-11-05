package com.dev.catalog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.catalog.dto.CategoryDTO;
import com.dev.catalog.entities.Category;
import com.dev.catalog.repositories.CategoryRepository;
import com.dev.catalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll(){
			List<Category> list = repository.findAll();
			
//			List<CategoryDTO> listDto = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());		
			
			List<CategoryDTO> listDto = new ArrayList<>();
			
			for(Category category : list) {
				listDto.add(new CategoryDTO(category));
			}
			
			return listDto;
			
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Objeto não encontrada"));
		return new CategoryDTO(entity);
		
	}
	
}
