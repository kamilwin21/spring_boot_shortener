package com.kamil.shortener.link;

import com.kamil.shortener.link.exceptions.ExistingArgument;
import com.kamil.shortener.link.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;
    private final LinkGenerator linkGenerator;

    public Link save(String longLink) {
        return linkRepository.save(new Link(linkGenerator.generate(), longLink));
    }

    public Link saveShortAndLongLink(String shortLink, String longLink) {
        if (!linkRepository.existsById(shortLink)) {
            return linkRepository.save(new Link(shortLink, longLink));
        }
        throw new ExistingArgument("Short link is already in database");
    }

    public Link getLink(String shortLink) {
        Optional<Link> link = linkRepository.findById(shortLink);
        if (link.isPresent()) {
            return link.get();
        }
        throw new NotFoundException("Short link not exists in database");

    }

}
