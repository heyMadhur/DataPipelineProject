package com.learning.service;

import com.learning.criteria.WikimediaChangeCriteria;
import com.learning.entity.WikimediaChange;
import com.learning.repo.WikimediaChangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class WikimediaChangeQueryService extends QueryService<WikimediaChange>{

    @Autowired
    private WikimediaChangeRepo repository;

    public Page<WikimediaChange> findByCriteria(WikimediaChangeCriteria criteria, Pageable pageable) {
        Specification<WikimediaChange> spec = createSpecification(criteria);
        return repository.findAll(spec, pageable);
//        return repository.findAll(spec);
    }

    private Specification<WikimediaChange> createSpecification(WikimediaChangeCriteria criteria) {
        // Where 1=1 / No Condition (What if No Criteria, thats why)
        Specification<WikimediaChange> spec= (root, query, cb) -> cb.conjunction();

        if(criteria.getTitle()!=null) {
            spec= spec.and(buildStringSpecification(criteria.getTitle(), "title"));
        }
        if (criteria.getUser() != null) {
            spec = spec.and(buildStringSpecification(criteria.getUser(), "user"));
        }
        if (criteria.getDomain() != null) {
            spec = spec.and(buildStringSpecification(criteria.getDomain(), "domain"));
        }
        if (criteria.getBot() != null) {
            spec = spec.and(buildBooleanSpecification(criteria.getBot(), "bot"));
        }
        if (criteria.getComment() != null) {
            spec = spec.and(buildStringSpecification(criteria.getComment(), "comment"));
        }

        return spec;


    }



}
