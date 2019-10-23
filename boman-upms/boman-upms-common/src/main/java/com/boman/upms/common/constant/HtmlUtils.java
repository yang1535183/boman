package com.boman.upms.common.constant;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * html 网页处理工具类
 */
public class HtmlUtils {


    /**
     * 处理表单，删除不必要的元素
     * @param html
     */
    public static String parseHTML(String html){
        // 追加form在首尾处
        html = "<form action='#formAction#' method='post' onsubmit='return fd.run.submit(this)'><input type='hidden' name='$formId' value='#tid#'/>" + html;
        html = html + "</form>";
        html = html.replaceAll("field=", "name=");
        Document root = Jsoup.parse(html);

        Elements editable = root.getElementsByAttribute("contenteditable");
        editable.removeAttr("contenteditable");
        //#3 处理fd-move
        Elements fdmove = root.getElementsByAttribute("fd-move");
        fdmove.removeAttr("fd-move");
        //处理class
        Elements selecteds = root.getElementsByClass("fd-view-selected");
        selecteds.removeClass("fd-view-selected");
        Elements currents = root.getElementsByClass("fd-view-current");
        currents.removeClass("fd-view-current");
        return root.body().html();
    }

}
