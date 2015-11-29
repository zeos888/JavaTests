package web.model;

import web.view.View;

/**
 * Created by alxev on 28.11.2015.
 */
public class Model
{
    private View view;
    private Provider[] providers;

    public Model(View view, Provider[] providers)
    {
        if (view == null || providers == null || providers.length == 0){
            throw new IllegalArgumentException();
        }
        this.view = view;
        this.providers = providers;
    }

    public void updateView(){
        for (Provider provider : providers){
            view.update(provider.getNews());
        }
    }
}
