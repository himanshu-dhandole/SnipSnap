package himanshu.snipsnap.service;


import himanshu.snipsnap.DTO.UrlMappingDTO;
import himanshu.snipsnap.models.UrlMapping;
import himanshu.snipsnap.models.Users;
import himanshu.snipsnap.repository.UrlMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlMappingService {

    private final UrlMappingRepository urlMappingRepository ;


    public UrlMappingDTO createShortURL(String originalURL, Users user) {

        String shortURL = generateShortURL() ;
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setShortURL(shortURL);
        urlMapping.setOriginalURL(originalURL);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());

        UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping) ;

        return UrlMappingDtoWrapper(savedUrlMapping) ;

    }

    private UrlMappingDTO UrlMappingDtoWrapper(UrlMapping savedUrlMapping) {

        UrlMappingDTO urlMappingDTO = new UrlMappingDTO();
        urlMappingDTO.setId(savedUrlMapping.getId());
        urlMappingDTO.setOriginalURL(savedUrlMapping.getOriginalURL());
        urlMappingDTO.setShortURL(savedUrlMapping.getShortURL());
        urlMappingDTO.setClickCount(savedUrlMapping.getClickCount());
        urlMappingDTO.setCreatedDate(savedUrlMapping.getCreatedDate());
        urlMappingDTO.setName(savedUrlMapping.getUser().getName());
        return urlMappingDTO ;
    }

    private String generateShortURL() {

        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random rand = new Random() ;
        StringBuilder shortUrl = new StringBuilder(8) ;

        for (int i = 0; i < 8; i++) {
            shortUrl.append(characters.charAt(rand.nextInt(characters.length()))) ;
        }

        return shortUrl.toString() ;
    }

    public List<UrlMappingDTO> getAllUrl(Users user) {
       return urlMappingRepository.findByUser(user).stream()
               .map(this::UrlMappingDtoWrapper)
               .toList();
    }
}