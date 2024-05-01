package com.spcu.dims.spcu_dims.services;

import com.spcu.dims.spcu_dims.entities.Activity;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface ActivityServices {

    Object createActivity(String name);

    List<String> showAll();

    Activity findById(int id);

    Activity delete(int id);

    Object update(int id, String name);

    DataTablesOutput<Activity> activityTable(DataTablesInput input);

    int activityCount();
}
