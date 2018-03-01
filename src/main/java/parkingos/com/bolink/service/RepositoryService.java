package parkingos.com.bolink.service;

import javax.servlet.http.HttpServletRequest;

public interface RepositoryService {
    String create(HttpServletRequest req);

    String quickquery(HttpServletRequest req);

    String delete(HttpServletRequest request);

    String queryByProduct(HttpServletRequest request);
}
