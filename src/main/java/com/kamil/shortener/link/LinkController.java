package com.kamil.shortener.link;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping
    public Link save(@RequestParam("long_link") String longLink) {
        return linkService.save(longLink);
    }

    @PostMapping(value = "/custom")
    public Link saveShortAndLongLink(@RequestParam("short_link") String shortLink, @RequestParam("long_link") String longLink) {
        return linkService.saveShortAndLongLink(shortLink, longLink);
    }

    @GetMapping(value = "/{redirect}")
    public ResponseEntity<Void> redirect(@PathVariable("redirect") String shortLink) {
        Link longLink = linkService.getLink(shortLink);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(longLink.getLongLink()))
                .build();
    }

}
