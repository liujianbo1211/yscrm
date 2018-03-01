package parkingos.com.bolink.service;

import javax.servlet.http.HttpServletRequest;

public interface CustomerService {
    String add(HttpServletRequest req);

    String quickquery(HttpServletRequest req);

    String delete(HttpServletRequest request);
}
