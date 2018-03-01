package parkingos.com.bolink.service;

import javax.servlet.http.HttpServletRequest;

public interface YsProductService {
    String quickquery(HttpServletRequest req);

    String create(HttpServletRequest req);

    String delete(HttpServletRequest request);

    String getProducts(HttpServletRequest request);

    String getProductMsg(HttpServletRequest request);
}
