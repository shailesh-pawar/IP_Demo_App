package com.techlearn.ip_es.springbootapp.repository;

import com.techlearn.ip_es.springbootapp.model.IpAddress;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface IpAddressRepository extends ElasticsearchRepository<IpAddress, String> {

    List<IpAddress> findByValue(String author);

    List<IpAddress> findByType(String title);

}
