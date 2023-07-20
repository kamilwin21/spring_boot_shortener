package com.kamil.shortener.link;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkGeneratorTest {

    LinkGenerator linkGenerator = new LinkGenerator();

    @Test
    void shouldReturnEightCharacters() {
        String shortLink = linkGenerator.generate();
        assertEquals(8, shortLink.length());
    }

}