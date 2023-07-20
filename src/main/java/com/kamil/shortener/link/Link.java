package com.kamil.shortener.link;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "links")
@AllArgsConstructor
@Getter
public class Link implements Persistable<String> {

    @Id
    private String shortLink;
    private String longLink;

    @Override
    @JsonIgnore
    public String getId() {
        return shortLink;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return true;
    }

}
