package web.model;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.BrowserVersionFeatures;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import web.vo.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by alxev on 27.11.2015.
 */
public class IXBTStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://www.ixbt.com/news/%d/%d/%d/";

    @Override
    public List<News> getNews()
    {
        List<News> allNews = new ArrayList<News>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.DATE, -1);
        Calendar cutoffCalendar = Calendar.getInstance();
        cutoffCalendar.add(calendar.DATE, -3);
        {
        }
        try{
            while (true){
                int year = calendar.get(calendar.YEAR);
                int month = calendar.get(calendar.MONTH) + 1;
                int day = calendar.get(calendar.DAY_OF_MONTH);
                //Document newsDocument = getDocument(year, month, day);
                //Document newsDocument = getDocumentJS(year, month, day);
                Document newsDocument = getDocumentComments(year, month, day);
                List<Element> newsItems = newsDocument.getElementsByAttributeValue("class", "news-block _clr");
                if (!newsItems.isEmpty()){
                    for (Element newsItem : newsItems){
                        News news = new News();
                        news.setDate(dateFormat.format(calendar.getTime()) + " " + newsItem.getElementsByAttributeValue("class", "date").first().text());
                        //news.setDate(dateFormat.format(calendar.getTime()) + newsItem.getElementsByAttribute("p").first().getElementsByClass("date").first().toString());
                        news.setComments(0);
                        news.setComments(Integer.parseInt(newsItem.getElementsByClass("comments_num activated").first().toString()));
                        news.setHeader(newsItem.getElementsByClass("title").first().text());
                        news.setUrl("http://www.ixbt.com" + newsItem.getElementsByClass("title").first().getElementsByTag("a").first().attr("href"));
                        allNews.add(news);
                    }
                }
                calendar.add(calendar.DATE, -1);
                if (calendar.before(cutoffCalendar)){
                    break;
                }
            }
        } catch (IOException ignored){

        }
        return allNews;
    }

    protected Document getDocument(int year, int month, int day) throws IOException{
        String url = String.format(URL_FORMAT, year, month, day);
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36";
        String referrer = "http://www.ixbt.com/news/2015/";
        Document document = Jsoup.connect(url).userAgent(userAgent).referrer(referrer).cookie("news_comments", "show_all%3A1%3B").get();
        return document;
    }

    protected Document getDocumentJS(int year, int month, int day) throws IOException{
        String url = String.format(URL_FORMAT, year, month, day);
        url = "http://www.ixbt.com/news/2015/11/27/elephone-m3-soc-mediatek-helio-p10-99.html#comments_block";
        //url = "http://www.ixbt.com/cgi/news_comments/comments.pl?action=ajax&sub_option=full_comments_list&option=news&category=news3&id=193224";
        WebClient webClient = new WebClient();
        webClient.setJavaScriptEnabled(true);
        HtmlPage htmlPage = webClient.getPage(url);
        //htmlPage.executeJavaScript("http://www.ixbt.com/js/news2/comments/comments.js");
        String pageAsText = htmlPage.getWebResponse().getContentAsString();
        Document document = Jsoup.parse(pageAsText);
        return document;
    }

    protected Document getDocumentComments(int year, int month, int day) throws IOException{
        String url = String.format(URL_FORMAT, year, month, day);
        url = "http://www.ixbt.com/cgi/news_comments/comments.pl?action=ajax&sub_option=full_comments_list&option=news&category=news3&id=193224";
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36";
        String referrer = "http://www.ixbt.com/news/2015/";
        Document document = Jsoup.connect(url).userAgent(userAgent).referrer(referrer).cookie("news_comments", "show_all%3A1%3B").post();
        return document;
    }
}
