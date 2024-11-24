package com.example.testassignment.controller;

import com.example.testassignment.service.puzzle.PuzzleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/puzzle")
@RequiredArgsConstructor
public class PuzzleController {
    private final PuzzleService puzzleService;

    @GetMapping
    public String getAlgorithmPage() {
        return "puzzle";
    }

    @PostMapping
    public String calculate(@RequestParam("numbersFile") MultipartFile numbersFile, Model model) {
        String longestSequence =  puzzleService.findLongestSequence(numbersFile);
        model.addAttribute("longestSequence", longestSequence);
        model.addAttribute("longestSequenceLength", longestSequence.length());
        return "puzzle";
    }
}
