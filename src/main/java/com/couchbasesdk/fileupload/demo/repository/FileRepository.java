package com.couchbasesdk.fileupload.demo.repository;

import com.couchbasesdk.fileupload.demo.model.LoadFile;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<LoadFile, String> {

}
