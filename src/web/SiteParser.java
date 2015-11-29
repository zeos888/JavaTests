package web;

import web.model.IXBTStrategy;
import web.model.Model;
import web.model.Provider;
import web.view.HtmlView;
import web.view.View;

/**
 * Created by alxev on 27.11.2015.
 */
public class SiteParser
{
    public static void main(String[] args){
        HtmlView htmlView = new HtmlView();
        Provider provider = new Provider(new IXBTStrategy());
        Model model = new Model(htmlView, new Provider[]{provider});
        Controller controller = new Controller(model);
        htmlView.setController(controller);
        htmlView.userRunModel();
    }
}
