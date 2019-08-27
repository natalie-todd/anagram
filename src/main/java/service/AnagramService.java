package service;

import com.ibotta.anagram.model.AddWordsResponse;
import com.ibotta.anagram.model.AddWordsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AnagramService {

    @Autowired
    public AnagramService() {
    }

    public ResponseEntity<AddWordsResponse> addWords(AddWordsRequest request) {
    return null;
    }
}
