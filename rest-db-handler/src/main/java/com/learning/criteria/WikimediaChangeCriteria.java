package com.learning.criteria;

import com.learning.filters.BooleanFilter;
import com.learning.filters.StringFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WikimediaChangeCriteria {
    private StringFilter title;
    private StringFilter user;
    private StringFilter domain;
    private BooleanFilter bot;
    private StringFilter comment;
    private StringFilter serverUrl;
    private StringFilter fullUrl;
}
