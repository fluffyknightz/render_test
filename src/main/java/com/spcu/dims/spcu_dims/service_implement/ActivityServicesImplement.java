package com.spcu.dims.spcu_dims.service_implement;

import com.spcu.dims.spcu_dims.entities.Activity;
import com.spcu.dims.spcu_dims.repositories.ActivityRepository;
import com.spcu.dims.spcu_dims.services.ActivityServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityServicesImplement implements ActivityServices {

    private final ActivityRepository activityRepository;

    String existsMessage = "Activity with the name '%s' already exists.";
    String doesNotExistMessage = "Activity with the name '%s' does not exist.";

    @Override
    public Object createActivity(String name) {

        Optional<Activity> optionalActivity = activityRepository.findByName(name);

        if (optionalActivity.isPresent()) {

            if (optionalActivity.get().isDelete()) {
                optionalActivity.get().setDelete(false);
                return activityRepository.save(optionalActivity.get());
            }
            return String.format(existsMessage, name);

        }

        Activity newActivity = Activity.builder().name(name).build();

        return activityRepository.save(newActivity);
    }

    @Override
    public List<String> showAll() {
        return activityRepository.findAllNotDeleted();
    }

    @Override
    public Activity findById(int id) {
        return activityRepository.findById(id).orElse(null);
    }

    @Override
    public Activity delete(int id) {
        Optional<Activity> activity = activityRepository.findById(id);
        if (activity.isPresent()) {
            activity.get().setDelete(true);
            return activityRepository.save(activity.get());
        }
        return null;
    }

    @Override
    public Object update(int id, String name) {
        Optional<Activity> activity = activityRepository.findById(id);
        Optional<Activity> existActivity = activityRepository.findByNameAndIdNot(name, id);
        if (existActivity.isPresent()) {
            return String.format(existsMessage, name);
        }
        if (activity.isPresent()) {

            if (activity.get().getName().equals(name)) {
                return activity;
            }

            activity.get().setName(name);
            return activityRepository.save(activity.get());

        }
        return String.format(doesNotExistMessage, name);
    }

    @Override
    public DataTablesOutput<Activity> activityTable(DataTablesInput input) {
        return activityRepository.findAll(input);
    }

    @Override
    public int activityCount() {
        return activityRepository.activityCount();
    }
}
    

