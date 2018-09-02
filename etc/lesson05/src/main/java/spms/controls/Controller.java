package spms.controls;

import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 1..
 */
public interface Controller {
    String execute(Map<String, Object> model) throws Exception;
}
