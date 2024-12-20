package com.ernestogonzalez.tanititourism.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ernestogonzalez.tanititourism.dto.ThingToDoDto;
import com.ernestogonzalez.tanititourism.entity.DoType;
import com.ernestogonzalez.tanititourism.entity.Region;
import com.ernestogonzalez.tanititourism.entity.ThingToDo;
import com.ernestogonzalez.tanititourism.service.AdminThingToDoService;
import com.ernestogonzalez.tanititourism.service.DoTypeService;
import com.ernestogonzalez.tanititourism.service.RegionService;
import com.ernestogonzalez.tanititourism.service.ResponseHelperService;

import java.util.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api/things-to-do")
public class AdminThingToDoController {

    private final AdminThingToDoService adminThingToDoService;
    private final ResponseHelperService responseHelperService;
    private final RegionService regionService;
    private final DoTypeService doTypeService;


    @GetMapping("/list")
    public Page<ThingToDo> getAllThingsToDo(@RequestParam(required = false, defaultValue = "1") int page,
                                            @RequestParam(required = false, defaultValue = "10") int size) {
        return adminThingToDoService.getAllThingsToDo(page, size);
    }

    @GetMapping("/listing-detail/{id}")
    public Optional<ThingToDo> getThingToDo(@PathVariable Long id) {
        return adminThingToDoService.getThingToDo(id);
    }


    @DeleteMapping("/delete-listing/{id}")
    public ResponseEntity<Void> deleteThingToDo(@PathVariable Long id) {
        adminThingToDoService.deleteThingToDo(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/new-listing")
    public ResponseEntity<?> addThingToDo(@Valid @RequestBody ThingToDoDto thingToDoDto,
                                          @RequestHeader("X-Username") String username,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        ThingToDo thingToDo = convertDtoToThingToDo(thingToDoDto);
        Region region = regionService.getRegionById(thingToDoDto.getRegionId());
        Set<DoType> doTypes = doTypeService.getDoTypesByIds(thingToDoDto.getDoTypesIds());
        ThingToDo savedThingToDo = adminThingToDoService.addThingToDo(thingToDo, region, doTypes, username);
        return ResponseEntity.ok(savedThingToDo);
    }

    @PutMapping("/update-listing/{id}")
    public ResponseEntity<?> updateThingToDo(@PathVariable Long id, @Valid @RequestBody ThingToDoDto thingToDoDto,
                                             @RequestHeader("X-Username") String username,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseHelperService.getBindingErrors(bindingResult);
        }
        ThingToDo thingToDo = convertDtoToThingToDo(thingToDoDto);
        Region region = regionService.getRegionById(thingToDoDto.getRegionId());
        Set<DoType> doTypes = doTypeService.getDoTypesByIds(thingToDoDto.getDoTypesIds());
        ThingToDo savedThingToDo = adminThingToDoService.updateThingToDo(id, thingToDo, region, doTypes, username);
        return ResponseEntity.ok(savedThingToDo);
    }

    private ThingToDo convertDtoToThingToDo(ThingToDoDto thingToDoDto) {
        ThingToDo thingToDo = new ThingToDo();
        thingToDo.setName(thingToDoDto.getName());
        thingToDo.setDescription(thingToDoDto.getDescription());
        thingToDo.setCost(thingToDoDto.getCost());
        thingToDo.setImageUrl(thingToDoDto.getImageUrl());
        thingToDo.setImageAltText(thingToDoDto.getImageAltText());
        thingToDo.setPhone(thingToDoDto.getPhone());
        return thingToDo;
    }

}