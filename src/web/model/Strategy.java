package web.model;

import web.vo.News;

import java.util.List;

/**
 * Created by alxev on 27.11.2015.
 */
public interface Strategy
{
    List<News> getNews();
}
