package parkingos.com.bolink.service;

import javax.servlet.http.HttpServletRequest;

public interface YsProductClassifyService {
    String quickquery(HttpServletRequest req);

    String create(HttpServletRequest req);

    String delete(HttpServletRequest request);

    String getDepartments(HttpServletRequest request);
}
