package himanshu.snipsnap.controller;


import himanshu.snipsnap.DTO.ClickEventDTO;
import himanshu.snipsnap.DTO.UrlMappingDTO;
import himanshu.snipsnap.DTO.UrlRequestDTO;
import himanshu.snipsnap.models.Users;
import himanshu.snipsnap.service.UrlMappingService;
import himanshu.snipsnap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/url")
@PreAuthorize("isAuthenticated()")
public class UrlMappingController {

    private final UserService userService;
    private final UrlMappingService urlMappingService;


    @PostMapping("/shorten")
    public ResponseEntity<UrlMappingDTO> shortenURL(@RequestBody UrlRequestDTO requestDTO,
                                                    Principal principal){

        String originalURL = requestDTO.getOriginalURL();
        Users user = userService.findByName(principal.getName());
        UrlMappingDTO urlMappingDTO = urlMappingService.createShortURL(originalURL, user);
        return ResponseEntity.ok(urlMappingDTO) ;
    }

    @GetMapping("/getAllUrl")
    public ResponseEntity<List<UrlMappingDTO>> getAllUrl(Principal principal) {
        Users user = userService.findByName(principal.getName()) ;
        List<UrlMappingDTO> urls = urlMappingService.getAllUrl(user) ;
        return ResponseEntity.ok(urls) ;
    }

    // shortened url analytics
    @GetMapping("/analytics/{shortURL}")
    public ResponseEntity<List<ClickEventDTO>> getAnalytics(@PathVariable String shortURL ,
                                                      @RequestParam String startDate,
                                                      @RequestParam String endDate){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME ;
        LocalDateTime start = LocalDateTime.parse(startDate,dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse(endDate,dateTimeFormatter);
        List<ClickEventDTO> clickEventDTOS = urlMappingService.getClickEventsByDate(shortURL , start , end);

        return ResponseEntity.ok(clickEventDTOS) ;
    }



}
