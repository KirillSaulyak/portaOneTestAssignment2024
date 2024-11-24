package com.example.testassignment.service.puzzle;

import org.springframework.web.multipart.MultipartFile;

public interface PuzzleService {
    String findLongestSequence(MultipartFile numbersFile);
}
