package com.curl.hackathon;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DataSetRepository extends MongoRepository<DataSetModel,String> {
	
	@Query(value = "{'_id': ?0}")
	List<DataSetModel> findFile(String email);	
}
