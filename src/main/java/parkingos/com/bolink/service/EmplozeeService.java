package parkingos.com.bolink.service;

import javax.servlet.http.HttpServletRequest;

public interface EmplozeeService {
    String create(HttpServletRequest req);

    String quickquery(HttpServletRequest req);

    String delete(HttpServletRequest request);
}
