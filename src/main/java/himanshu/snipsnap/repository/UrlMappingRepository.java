package himanshu.snipsnap.repository;


import himanshu.snipsnap.models.UrlMapping;
import himanshu.snipsnap.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping , Long> {

    public UrlMapping findByShortURL(String shortUrl);

    public List<UrlMapping> findByUser(Users user);

}
