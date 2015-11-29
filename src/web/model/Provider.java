package web.model;

import web.vo.News;

import java.util.List;

/**
 * Created by alxev on 27.11.2015.
 */
public class Provider
{
    private Strategy strategy;

    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public Provider(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public List<News> getNews(){
        return strategy.getNews();
    }
}
