
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TextAnalysisController {

    private final KoreanTextAnalyzerService koreanTextAnalyzerService;

    @Autowired
    public TextAnalysisController(KoreanTextAnalyzerService koreanTextAnalyzerService) {
        this.koreanTextAnalyzerService = koreanTextAnalyzerService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<List<String>> analyzeText(@RequestBody TextInput input) {
        List<String> result = koreanTextAnalyzerService.analyzeText(input.getText());
        return ResponseEntity.ok(result);
    }
}
