package com.company.cab.cabs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CabRepository extends JpaRepository<Cab,Integer> {
	
	@Query(value="SELECT C.* FROM CAB C,CAB_DROPPOINT CD WHERE CD.DROPPOINT_ID = :name AND CD.CAB_ID = C.CAB_ID",nativeQuery=true)
    public List<Cab> findByDropPoint(@Param("name") String name);

	
}
