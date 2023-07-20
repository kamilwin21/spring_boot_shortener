package com.kamil.shortener.link;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles(profiles = {"test"})
@SpringBootTest
@Testcontainers
class LinkServiceTestContainersTest {

    private static final String SHORTLINK = "shf";
    private static final String SHORTLINK1 = "google";
    private static final String LONGLINK = "https://www.google.com";

    @Autowired
    private LinkService linkService;

    @Test
    void addLongLink() {
        Link link = new Link(SHORTLINK, LONGLINK);
        Link savedLink = linkService.saveShortAndLongLink(link.getShortLink(), link.getLongLink());
        Link linksList = linkService.getLink(link.getShortLink());

        assertEquals(savedLink.getShortLink(), linksList.getShortLink());
        assertEquals(savedLink.getLongLink(), linksList.getLongLink());
    }

    @Test
    void save_link_using_long_link_and_generate_short_link() {
        Link savedLink = linkService.save(LONGLINK);
        Link getLink = linkService.getLink(savedLink.getShortLink());

        assertEquals(savedLink.getShortLink(), getLink.getShortLink());
        assertEquals(savedLink.getLongLink(), getLink.getLongLink());
    }

    @Test
    void should_return_redirect_to_web_page() {
        Link link = new Link(SHORTLINK1, LONGLINK);
        Link savedLink = linkService.saveShortAndLongLink(link.getShortLink(), link.getLongLink());
        Link getLink = linkService.getLink(savedLink.getShortLink());

        assertEquals(savedLink.getShortLink(), getLink.getShortLink());
        assertEquals(savedLink.getLongLink(), getLink.getLongLink());
    }
}