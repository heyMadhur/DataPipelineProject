package com.learning.service;


import com.learning.filters.BooleanFilter;
import com.learning.filters.StringFilter;
import org.springframework.data.jpa.domain.Specification;

public abstract class QueryService<T> {

    protected Specification<T> buildStringSpecification(StringFilter filter, String fieldName) {
        return (root, query, cb) -> {
            if (filter.getContains() != null) {
                return cb.like(cb.lower(root.get(fieldName)), "%" + filter.getContains().toLowerCase() + "%");
            } else if (filter.getEquals() != null) {
                return cb.equal(cb.lower(root.get(fieldName)), filter.getEquals().toLowerCase());
            }
            return null;
        };
    }

    protected Specification<T> buildBooleanSpecification(BooleanFilter filter, String fieldName) {
        return (root, query, cb) -> {
            if (filter.getEquals() != null) {
                return cb.equal(root.get(fieldName), filter.getEquals());
            }
            return null;
        };
    }
}
