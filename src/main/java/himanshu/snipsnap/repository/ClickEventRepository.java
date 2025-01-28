package himanshu.snipsnap.repository;

import himanshu.snipsnap.models.ClickEvents;
import himanshu.snipsnap.models.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvents,Long > {
    List<ClickEvents> findUrlMappingAndClickDateBetween(UrlMapping urlMapping, LocalDateTime start, LocalDateTime end);
    List<ClickEvents> findUrlMappingInAndClickDateBetween (List<UrlMapping> urlMappingList, LocalDateTime start, LocalDateTime end);
}
