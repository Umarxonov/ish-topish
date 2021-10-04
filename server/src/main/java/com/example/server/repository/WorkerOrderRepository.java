package com.example.server.repository;

import com.example.server.entity.Work;
import com.example.server.entity.enums.WorkEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WorkerOrderRepository extends JpaRepository<Work, UUID> {

    List<Work> findAllByCreatedBy(UUID createdBy);

    List<Work> findAllByCreatedByAndBossIdAndActive(UUID createdBy, UUID boss_id, boolean active);

    List<Work> findAllByCreatedByAndWorkerIdAndActive(UUID createdBy, UUID worker_id,boolean active);

    Page<Work> findAllByWorkEnuAndCategoryWorkIdAndActive(WorkEnum workEnu, UUID categoryWork_id, Pageable pageable, boolean active);

    Page<Work> findAllByWorkEnuAndCategoryWorkIdAndLanBetweenAndLatBetweenAndActive(WorkEnum workEnu, UUID categoryWork_id, Double lan, Double lan2, Double lat, Double lat2, Pageable pageable,boolean active);
//    Page<Work> findAllByWorkEnuAndCategoryWorkIdIn(WorkEnum workEnu, Collection<UUID> categoryWork_id, Pageable pageable);


    List<Work> findAllByLanBetweenAndLatBetweenAndCategoryWorkIdAndBossId(Double lan, Double lan2, Double lat, Double lat2, UUID categoryWork_id, UUID boss_id);

    List<Work> findAllByLanBetweenAndLatBetweenAndCategoryWorkIdAndWorkerId(Double lan, Double lan2, Double lat, Double lat2, UUID categoryWork_id, UUID worker_id);


}
