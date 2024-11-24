package com.example.testassignment.service.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class PuzzleServiceImpl implements PuzzleService {
    @Override
    public String findLongestSequence(MultipartFile numbersFile) {
        List<String> strNumbers = parseFileToStringList(numbersFile);
        String longestSequence = "";
        for (int i = 0; i < strNumbers.size(); i++) {
            String currentSequence = buildSequence(strNumbers, i);
            if (currentSequence.length() > longestSequence.length()) {
                longestSequence = currentSequence;
            }
        }
        return longestSequence;
    }

    private String buildSequence(List<String> strNumbers, int startIndex) {
        StringBuilder sequence = new StringBuilder();
        List<String> remaining = new LinkedList<>(strNumbers);

        String current = remaining.remove(startIndex);
        sequence.append(current);

        while (!remaining.isEmpty()) {
            boolean matched = false;

            for (int i = 0; i < remaining.size(); i++) {
                String next = remaining.get(i);

                if (current.endsWith(next.substring(0, 2))) {
                    sequence.append(next.substring(2));
                    current = next;
                    remaining.remove(i);
                    matched = true;
                    break;
                }
            }
            if (!matched) break;
        }

        return sequence.toString();
    }

    private List<String> parseFileToStringList(MultipartFile numbersFile) {
        List<String> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(numbersFile.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(line);
            }
        } catch (IOException e) {
            log.error("Read file error", e);
        }
        return numbers;
    }
}
