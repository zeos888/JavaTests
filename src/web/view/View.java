package web.view;

import web.Controller;
import web.vo.News;

import java.util.List;

/**
 * Created by alxev on 28.11.2015.
 */
public interface View
{
    void update(List<News> vacancies);
    void setController(Controller controller);
}
