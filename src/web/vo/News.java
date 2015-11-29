package web.vo;

/**
 * Created by alxev on 27.11.2015.
 */
public class News
{
    private String date;
    private int comments;
    private String header;

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public int getComments()
    {
        return comments;
    }

    public void setComments(int comments)
    {
        this.comments = comments;
    }

    public String getHeader()
    {
        return header;
    }

    public void setHeader(String header)
    {
        this.header = header;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    private String url;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (comments != news.comments) return false;
        if (!date.equals(news.date)) return false;
        if (!header.equals(news.header)) return false;
        if (!url.equals(news.url)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = date.hashCode();
        result = 31 * result + comments;
        result = 31 * result + header.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
