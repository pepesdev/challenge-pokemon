package com.api_pokemon_soap.repository;

import com.api_pokemon_soap.dto.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog,Long> {

}
