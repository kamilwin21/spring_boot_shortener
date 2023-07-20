package com.kamil.shortener.link;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@NoArgsConstructor
public class LinkGenerator {

    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnouprstuvwxyz" + "0123456789";

    public String generate() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 8;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHABET.length());
            char randomChar = ALPHABET.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

}
