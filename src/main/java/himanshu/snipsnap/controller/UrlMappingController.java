package himanshu.snipsnap.controller;


import himanshu.snipsnap.DTO.UrlMappingDTO;
import himanshu.snipsnap.DTO.UrlRequestDTO;
import himanshu.snipsnap.models.Users;
import himanshu.snipsnap.service.UrlMappingService;
import himanshu.snipsnap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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



}
