package com.spcu.dims.spcu_dims.controllers.api;

import com.spcu.dims.spcu_dims.dto.IndexDto;
import com.spcu.dims.spcu_dims.services.ActivityService;
import com.spcu.dims.spcu_dims.services.ParishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spcu-dims/api/v1/index")
@RequiredArgsConstructor
public class IndexAPI {

    private final ActivityService activityService;
    private final ParishService parishService;

    @GetMapping("/activity&parish")
    public ResponseEntity<IndexDto> activityAndParish() {

        int activityCount = activityService.activityCount();
        int parishCount = parishService.parishCount();
        IndexDto indexDto = IndexDto.builder().parish(parishCount).activity(activityCount).build();
        return new ResponseEntity<>(indexDto, HttpStatus.OK);
    }
}
