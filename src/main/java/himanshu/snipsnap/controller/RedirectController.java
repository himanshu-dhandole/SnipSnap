package himanshu.snipsnap.controller;

import himanshu.snipsnap.models.UrlMapping;
import himanshu.snipsnap.service.UrlMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedirectController {
    private final UrlMappingService urlMappingService;


    @GetMapping("/{shortURL}")
    public ResponseEntity<Void> redierct(@PathVariable String shortURL){
        UrlMapping urlMapping = urlMappingService.getOriginalURL(shortURL);
        if (urlMapping != null) {
            HttpHeaders header = new HttpHeaders();
            header.add("Location" , urlMapping.getOriginalURL());
            return ResponseEntity.status(302).headers(header).build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
