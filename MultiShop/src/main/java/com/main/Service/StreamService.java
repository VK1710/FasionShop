package com.main.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.Dao.StreamDao;
import com.main.Entity.Stream;

@Service
@Transactional
public class StreamService {
	@Autowired StreamDao dao;
	
	 public List<Stream> listAll() {
	        return dao.findAll();
	    }
}
