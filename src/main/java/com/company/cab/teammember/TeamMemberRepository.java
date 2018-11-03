package com.company.cab.teammember;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember,Integer> {
	
	@Query(value="SELECT t.* FROM  teammember t WHERE t.cab_id =  :id",nativeQuery=true)
    public List<TeamMember> findByCab(@Param("id") int id);
	
	
}
