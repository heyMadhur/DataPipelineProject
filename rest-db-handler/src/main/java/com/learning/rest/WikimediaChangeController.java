package com.learning.rest;

import com.learning.criteria.WikimediaChangeCriteria;
import com.learning.entity.WikimediaChange;
import com.learning.service.WikimediaChangeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WikimediaChangeController {

    private final WikimediaChangeQueryService queryService;

    @GetMapping("/")
    public ResponseEntity<String> defaultApi(){
        return ResponseEntity.ok("Default Endpoint Working");
    }

    @GetMapping("/changes")
    public ResponseEntity<Page<WikimediaChange>> getChanges(WikimediaChangeCriteria criteria, Pageable pageable) {
        Page<WikimediaChange> result = queryService.findByCriteria(criteria, pageable);
        return ResponseEntity.ok(result);
    }
}
