package spms.controls;

import spms.annotation.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * IDE : IntelliJ IDEA
 * Created by minho on 2018. 9. 2..
 */

@Component("/auth/logout.do")
public class LogoutController implements Controller {

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        HttpSession httpSession = (HttpSession) model.get("session");
        httpSession.invalidate();
        return "redirect:../member/list.do";
    }

}
