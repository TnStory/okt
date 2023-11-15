
package com.example.demo;

import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import scala.collection.JavaConverters;
import scala.collection.Seq;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KoreanTextAnalyzerService {
    public List<String> analyzeText(String text) {
        CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);

        // 해시태그로 적합한 단어만 추출 (예: 명사)
        return JavaConverters.seqAsJavaList(tokens).stream()
                .filter(token -> token.pos().toString().equals("Noun") || token.pos().toString().equals("ProperNoun")) // 명사와 고유명사 선택
                .map(KoreanTokenizer.KoreanToken::text)
                .filter(word -> word.length() > 1) // 한 글자 단어 제외
                .distinct() // 중복 제거
                .collect(Collectors.toList());
    }
}
