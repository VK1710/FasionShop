package com.main.Dao;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.main.Entity.Stream;

public interface StreamDao extends JpaRepository<Stream, Long> {
	@Query("SELECT u FROM Stream u WHERE u.Id_Stream = 1")
	Collection<Stream> findID();
}
