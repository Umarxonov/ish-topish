package com.example.server.service;


import com.example.server.entity.Category;
import com.example.server.entity.User;
import com.example.server.entity.Work;
import com.example.server.entity.enums.WorkEnum;
import com.example.server.payload.ApiResponse;
import com.example.server.payload.WorkDto;
import com.example.server.repository.CategoryRepository;
import com.example.server.repository.WorkerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class WorkService {
    @Autowired
    WorkerOrderRepository workerOrderRepository;
    @Autowired
    CategoryRepository categoryWorkRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ApiResponse saveWorkByWorker(WorkDto workDto, User user) {
        Work work = new Work();

        if (workDto.getId() != null) {
            Optional<Work> byId = workerOrderRepository.findById(workDto.getId());
            if (byId.isPresent()) {
                work = byId.get();
                if (!work.getWorker().getCreatedBy().equals(user.getId())) {
                    return new ApiResponse("bu elon sizniki emas", false);
                }
            } else {
                return new ApiResponse("not found this id= " + workDto.getId(), false);
            }
        }

        Optional<Category> byId = categoryWorkRepository.findById(workDto.getCategoryWorkId());
        if (byId.isPresent()) {
            work.setWorker(user);
            work.setName(workDto.getName());
            work.setPhoneNumber(workDto.getPhoneNumber());
            work.setLan(workDto.getLan());
            work.setLat(workDto.getLat());
            work.setDate(workDto.getDate());
            work.setActive(workDto.isActive());
            work.setDescription(workDto.getDescription());
            work.setCategoryWork(byId.get());
            workerOrderRepository.save(work);
            return new ApiResponse("Saved", true);
        }
        return new ApiResponse("error", false);

    }


    public ApiResponse saveWorkByBoss(WorkDto workDto, User user) {

        Work work = new Work();
        if (workDto.getId() != null) {
            Optional<Work> byId = workerOrderRepository.findById(workDto.getId());
            if (byId.isPresent()) {
                work = byId.get();
                if (!work.getWorker().equals(user.getId())) {
                    return new ApiResponse("bu elon sizniki emas", false);

                } else {
                    return new ApiResponse("not found this id= " + workDto.getId(), false);
                }

            }

        }
        Optional<Category> byId = categoryWorkRepository.findById(workDto.getCategoryWorkId());

        if (byId.isPresent()) {
            work.setBoss(user);
            work.setName(workDto.getName());
            work.setPhoneNumber(workDto.getPhoneNumber());
            work.setLan(workDto.getLan());
            work.setLat(workDto.getLat());
            work.setActive(workDto.isActive());
            work.setDate(workDto.getDate());
            work.setDescription(workDto.getDescription());
            work.setCategoryWork(categoryWorkRepository.getById(workDto.getCategoryWorkId()));
            workerOrderRepository.save(work);
            return new ApiResponse("Saved", true);
        }
        return new ApiResponse("error", false);
    }


    public ApiResponse getMyOrder(User user, WorkEnum workEnum) {
        UUID id = user.getId();
        if (workEnum.equals(WorkEnum.WORKER)) {
            return new ApiResponse("WORKER", true, workerOrderRepository.findAllByCreatedByAndWorkerIdAndActive(id, id,true));
        }
        if (workEnum.equals(WorkEnum.BOSS)) {
            return new ApiResponse("BOSS", true, workerOrderRepository.findAllByCreatedByAndBossIdAndActive(id, id,true));
        }
        return new ApiResponse("All", true, workerOrderRepository.findAllByCreatedBy(id));


    }


    public ApiResponse search(WorkEnum workEnum, Double lan, Double lat, UUID categoryId, User user, int page, int size) {
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from work where active=true");
//        if (maps.size()!=0) {
//
//            if (lan == null && lat == null) {
//                if (workEnum.equals(WorkEnum.WORKER)) {
//
//                    return workerOrderRepository.findAllByCreatedByAndWorkerId(userId, userId);
//                }
//
//                if (workEnum.equals(WorkEnum.BOSS)) {
//
//                    return workerOrderRepository.findAllByCreatedByAndBossId(userId, userId);
//                }
//
//            } else {
//
//                if (workEnum.equals(WorkEnum.WORKER)) {
//
//
//                    return workerOrderRepository.
//                            findAllByLanBetweenAndLatBetweenAndCategoryWorkIdAndWorkerId(lan - 0.09, lan + 0.09, lat - 0.09, lat + 0.09,
//                                    categoryId, userId);
//
//                }
//
//                if (workEnum.equals(WorkEnum.BOSS)) {
//                    return workerOrderRepository.
//                            findAllByLanBetweenAndLatBetweenAndCategoryWorkIdAndBossId(lan - 0.09, lan + 0.09, lat - 0.09, lat + 0.09,
//                                    categoryId, userId);
//
//                }
//
//
//            }
//
//
//        }
//
//return (List<Work>) ResponseEntity.ok();
        if (size < 1000)
            return null;
        Double r = 0.009 * 500;
        if (lan == null || lat == null) {
            return new ApiResponse("All", true, workerOrderRepository.findAllByWorkEnuAndCategoryWorkIdAndLanBetweenAndLatBetweenAndActive(workEnum, categoryId, lan - r, lan + r,
                    lat - r, lat + r, PageRequest.of(page, size, Sort.by("zIndex").descending()),true));
        } else {
            return new ApiResponse("All", true, workerOrderRepository.findAllByWorkEnuAndCategoryWorkIdAndActive(workEnum, categoryId, PageRequest.of(page, size, Sort.by("zIndex").descending()),true));
        }

    }
}