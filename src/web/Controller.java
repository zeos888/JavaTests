package web;

import web.model.Model;
import web.model.Provider;
import web.vo.News;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alxev on 27.11.2015.
 */
public class Controller
{
    public Controller(Model model)
    {
        if (model == null){
            throw new IllegalArgumentException();
        }
        this.model = model;
    }

    private Model model;

    public void onViewUpdate(){
        model.updateView();
    }
}
