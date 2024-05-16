package com.spcu.dims.spcu_dims.controllers;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.Search;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.springframework.util.StringUtils.hasText;

public class CreatedDateSpecification<T> implements Specification<T> {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String dateFieldName;

    public CreatedDateSpecification(DataTablesInput input, String dateFieldName) {
        this.dateFieldName = dateFieldName;
        Search columnSearch = input.getColumn(dateFieldName).getSearch();
        String dateFilter = columnSearch.getValue();
        columnSearch.setValue("");
        if (!hasText(dateFilter)) {
            startDate = endDate = null;
            return;
        }
        String[] texts = dateFilter.split(";");
        startDate = getValue(texts, 0);
        endDate = getValue(texts, 1);
    }

    private LocalDate getValue(String[] bounds, int index) {
        if (bounds.length > index && hasText(bounds[index])) {
            try {
                return LocalDate.parse(bounds[index]);
            } catch (DateTimeParseException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Expression<LocalDate> createdDate = root.get(dateFieldName).as(LocalDate.class);
        if (startDate != null && endDate != null) {
            return criteriaBuilder.between(createdDate, startDate, endDate);
        } else if (startDate != null) {
            return criteriaBuilder.greaterThanOrEqualTo(createdDate, startDate);
        } else if (endDate != null) {
            return criteriaBuilder.lessThanOrEqualTo(createdDate, endDate);
        } else {
            return criteriaBuilder.conjunction();
        }
    }
}
