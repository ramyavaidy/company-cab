package com.company.cab.droppoint;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroppointServiceImpl implements DroppointService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DropPointRepository repository;

	@Autowired
	private DroppointDistanceRepository dpdrepo;

	@Autowired
	EntityManagerFactory emf;

	public void saveDropPoint(LinkedHashMap<String, String> dropPoints) {

		List<String> dps = new ArrayList<String>();
		List<DropPoint> droppointList = new ArrayList<DropPoint>();
		List<DropPoint> savedDroppoints = null;
		List<DroppointDistance> dpdList = null;

		for (Map.Entry<String, String> entry : dropPoints.entrySet()) {

			dps.add(entry.getKey());
			droppointList.add(new DropPoint(entry.getKey()));

		}
		savedDroppoints = repository.saveAll(droppointList);
		dpdrepo.deleteAllInBatch();
		
		dpdList = saveDroppointDistance(dps, dropPoints);
		dpdrepo.saveAll(dpdList);
		

	}

	public void deletecabDropPoint(List<String> dropPoints) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query q = em.createNativeQuery("delete from cab_droppoint where droppoint_id in (:ids)");
		q.setParameter("ids", dropPoints);
		q.executeUpdate();

		em.getTransaction().commit();
		em.close();

	}

	public List<DropPoint> retrieveAllDropPoints() {
		List<DropPoint> dropPoints = repository.findAll();

		return dropPoints;
	}

	public List<DroppointResponse> retrieveAllDropPointsDistance() {

		List<DroppointDistance> dropPoints = dpdrepo.findAll();
		List<DroppointResponse> dpRespList = new ArrayList<DroppointResponse>();

		for (DroppointDistance dpd : dropPoints) {
			dpRespList.add(this.convertDroppointDistanceToResponse(dpd));
		}
		return dpRespList;
	}

	public DropPoint retrieveOneDropPoint(String name) {
		Optional<DropPoint> droppoint = repository.findById(name);

		if (!droppoint.isPresent()) {
			throw new DropPointNotFoundException("Name-" + name);
		}

		return droppoint.get();

	}

	public List<DroppointDistance> saveDroppointDistance(List<String> dps, LinkedHashMap<String, String> dropPoints) {
		List<DroppointDistance> dpdList = new ArrayList<DroppointDistance>();
		int count = 0;
		for (String dp : dps) {
			count = 0;
			for (String distance : dropPoints.get(dp).split(",")) {
				dpdList.add(createDroppointDistanceFromDroppoints(new DropPoint(dp), new DropPoint(dps.get(count)),
						Double.valueOf(distance)));
				count++;

			}
			

		}
		
		return dpdList;
		
		
	}

	public static DroppointResponse convertDroppointDistanceToResponse(DroppointDistance dpd) {

		DroppointResponse dpResp = new DroppointResponse();
		dpResp.setDroppoint_from(dpd.getDroppointRel().getDroppoint_from().getDropPointName());
		dpResp.setDroppoint_to(dpd.getDroppointRel().getDroppoint_to().getDropPointName());
		dpResp.setDistance(dpd.getDistance());

		return dpResp;

	}

	public static DroppointDistance createDroppointDistanceFromDroppoints(DropPoint dp1, DropPoint dp2, double distance) {

		DroppointDistance dpd = new DroppointDistance();
		DroppointRel droppointRel = new DroppointRel();
		droppointRel.setDroppoint_from(dp1);
		droppointRel.setDroppoint_to(dp2);
		dpd.setDroppointRel(droppointRel);
		dpd.setDistance(distance);

		return dpd;

	}

	public void deleteDropPoint(LinkedHashMap<String, String> dropPoints) {

		List<String> dps = new ArrayList<String>();
		List<DropPoint> droppointList = new ArrayList<DropPoint>();
		List<String> dpCabListToDelete = new ArrayList<String>();
		List<DropPoint> droppointListToDelete = new ArrayList<DropPoint>();
		boolean isPresent = false;
		int count = 0;

		List<DropPoint> dpListFromRepo = repository.findAll();

		for (DropPoint dpoint : dpListFromRepo) {
			isPresent = false;

			for (Map.Entry<String, String> entry : dropPoints.entrySet()) {

				if (count == 0) {
					dps.add(entry.getKey());
					droppointList.add(new DropPoint(entry.getKey()));
				}

				if (dpoint.getDropPointName().equals(entry.getKey())) {
					isPresent = true;
					continue;
				}

			}

			if (!isPresent) {
				dpCabListToDelete.add(dpoint.getDropPointName());
				droppointListToDelete.add(dpoint);
			}
			count++;

		}
		deletecabDropPoint(dpCabListToDelete);
		dpdrepo.deleteAllInBatch();
		repository.deleteAll(droppointListToDelete);
		repository.saveAll(droppointList);
		List<DroppointDistance> dpdList = saveDroppointDistance(dps, dropPoints);
		dpdrepo.saveAll(dpdList);

	}

}
