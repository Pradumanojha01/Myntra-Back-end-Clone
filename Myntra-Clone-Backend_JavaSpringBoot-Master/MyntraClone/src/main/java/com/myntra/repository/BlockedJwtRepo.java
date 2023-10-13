package com.myntra.repository;

import org.springframework.data.repository.CrudRepository;
import com.myntra.entity.BlockedJwt;

public interface BlockedJwtRepo extends CrudRepository<BlockedJwt, String> {

}
