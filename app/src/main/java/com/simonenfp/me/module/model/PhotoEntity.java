package com.simonenfp.me.module.model;

import java.util.List;

/**
 * Created by simonenfp on 2016-08-14.
 */
public class PhotoEntity {
    public String id;
    public String title;
    public String long_title;
    public String source;
    public String link;
    public String pic;
    public String kpic;
    public String bpic;
    public String intro;
    public int pubDate;
    public int articlePubDate;
    public String comments;
    public String feedShowStyle;
    public String category;
    public int comment;

    public PicsEntity pics;

    public CommentInfo comment_count_info;

    public class PicsEntity{
        public int total;
        public int picTemplate;

        public List<PicsEntityList> list;

        public class PicsEntityList{
            public String pic;
            public String alt;
            public String kpic;
        }
    }

    public class CommentInfo{
        public int qreply;
        public int total;
        public int show;
        public int comment_status;
        public int praise;
        public int dispraise;
    }
}
