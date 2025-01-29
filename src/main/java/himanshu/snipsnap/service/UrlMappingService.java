package himanshu.snipsnap.service;


import himanshu.snipsnap.DTO.ClickEventDTO;
import himanshu.snipsnap.DTO.UrlMappingDTO;
import himanshu.snipsnap.models.ClickEvents;
import himanshu.snipsnap.models.UrlMapping;
import himanshu.snipsnap.models.Users;
import himanshu.snipsnap.repository.ClickEventRepository;
import himanshu.snipsnap.repository.UrlMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;
    private final ClickEventRepository clickEventRepository;


    public UrlMappingDTO createShortURL(String originalURL, Users user) {

        String shortURL = generateShortURL();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setShortURL(shortURL);
        urlMapping.setOriginalURL(originalURL);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());

        UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping);

        return UrlMappingDtoWrapper(savedUrlMapping);

    }

    private UrlMappingDTO UrlMappingDtoWrapper(UrlMapping savedUrlMapping) {

        UrlMappingDTO urlMappingDTO = new UrlMappingDTO();
        urlMappingDTO.setId(savedUrlMapping.getId());
        urlMappingDTO.setOriginalURL(savedUrlMapping.getOriginalURL());
        urlMappingDTO.setShortURL(savedUrlMapping.getShortURL());
        urlMappingDTO.setClickCount(savedUrlMapping.getClickCount());
        urlMappingDTO.setCreatedDate(savedUrlMapping.getCreatedDate());
        urlMappingDTO.setName(savedUrlMapping.getUser().getName());
        return urlMappingDTO;
    }

    private String generateShortURL() {
        final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int SHORT_URL_LENGTH = 8;

        Random random = new SecureRandom();
        StringBuilder shortUrl = new StringBuilder(SHORT_URL_LENGTH);

        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortUrl.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return shortUrl.toString();
    }

    public List<UrlMappingDTO> getAllUrl(Users user) {
        return urlMappingRepository.findByUser(user).stream()
                .map(this::UrlMappingDtoWrapper)
                .toList();
    }


    public List<ClickEventDTO> getClickEventsByDate(String shortURL, LocalDateTime start, LocalDateTime end) {
        UrlMapping urlMapping = urlMappingRepository.findByShortURL(shortURL);
        if (urlMapping != null) {
            return clickEventRepository.findByUrlMappingAndClickDateBetween(urlMapping, start, end)
                    .stream()
                    .collect(Collectors.groupingBy(click -> click.getClickDate().toLocalDate(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .map(entry -> {
                        ClickEventDTO clickEventDTO = new ClickEventDTO();
                        clickEventDTO.setClickDate(entry.getKey());
                        clickEventDTO.setClickCount(entry.getValue().intValue());
                        return clickEventDTO;
                    })
                    .toList();
        }
        return null;
    }


    public Map<LocalDate, Long> totalClicksByUser(Users user, LocalDate start, LocalDate end) {
        List<UrlMapping> urlMappings = urlMappingRepository.findByUser(user);
        List<ClickEvents> clickEvents = clickEventRepository.findByUrlMappingInAndClickDateBetween(urlMappings, start.atStartOfDay(), end.plusDays(1).atStartOfDay());
        return clickEvents.stream()
                .collect(Collectors.groupingBy(click -> click
                        .getClickDate()
                        .toLocalDate(), Collectors.counting()));
    }

    public UrlMapping getOriginalURL(String shortURL) {
        UrlMapping urlMapping= urlMappingRepository.findByShortURL(shortURL) ;
        if (urlMapping != null ) {
            // click count
            urlMapping.setClickCount(urlMapping.getClickCount() +1 );
            urlMappingRepository.save(urlMapping) ;

            // click event
            ClickEvents clickEvents = new ClickEvents() ;
            clickEvents.setClickDate(LocalDateTime.now());
            clickEvents.setUrlMapping(urlMapping);
            clickEventRepository.save(clickEvents) ;
         }

        return urlMapping ;
    }
}