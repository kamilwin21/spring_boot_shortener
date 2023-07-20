package com.kamil.shortener.link;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LinkController.class)
class LinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    LinkService linkService;

    private static final String SHORTLINK = "shf";
    private static final String SHORTLINK1 = "google";
    private static final String LONGLINK = "https://www.sfgame.pl";
    private static final String LONGLINK1 = "https://www.google.pl";

    @Test
    void shouldSaveLink() throws Exception {
        Link link = new Link(SHORTLINK, LONGLINK);

        given(linkService.saveShortAndLongLink(SHORTLINK, LONGLINK)).willReturn(link);

        MultiValueMap<String, String> params = setParams(SHORTLINK, LONGLINK);
        mockMvc.perform(post("/links/custom").params(params))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("shortLink").exists())
                .andExpect(jsonPath("$.shortLink", SHORTLINK).value(SHORTLINK))
                .andExpect(jsonPath("longLink").exists())
                .andExpect(jsonPath("$.longLink").value(LONGLINK));
    }

    @Test
    void shouldSaveAnotherLink() throws Exception {
        Link link = new Link(SHORTLINK1, LONGLINK1);

        given(linkService.saveShortAndLongLink(SHORTLINK1, LONGLINK1)).willReturn(link);
        MultiValueMap<String, String> params = setParams(SHORTLINK1, LONGLINK1);

        mockMvc.perform(post("/links/custom").params(params))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("shortLink").exists())
                .andExpect(jsonPath("$.shortLink", SHORTLINK1).value(SHORTLINK1))
                .andExpect(jsonPath("longLink").exists())
                .andExpect(jsonPath("$.longLink").value(LONGLINK1));
    }

    @Test
    void should_redirect_to_web_page() throws Exception {
        Link link = new Link(SHORTLINK, LONGLINK);
        given(linkService.getLink(SHORTLINK)).willReturn(link);
        mockMvc.perform(get("/links/" + SHORTLINK)).andExpect(redirectedUrl(LONGLINK));
    }

    @Test
    void should_redirect_to_google() throws Exception {
        Link link = new Link(SHORTLINK, LONGLINK);
        given(linkService.getLink(SHORTLINK)).willReturn(link);
        mockMvc.perform(get("/links/" + SHORTLINK)).andExpect(redirectedUrl(LONGLINK));
    }

    private MultiValueMap<String, String> setParams(String shortLink, String longLink) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("short_link", shortLink);
        params.add("long_link", longLink);
        return params;
    }
}