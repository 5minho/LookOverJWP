package spms.controllers;

import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 8. 2..
 */
public interface Controller {
    String execute(Map<String, Object> model) throws Exception;
}
