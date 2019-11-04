package com.xie.com.imoocmusic.http;

import io.vov.vitamio.utils.Log;

/**
 * @author xiejinbo
 * @date 2019/10/28 0028 14:32
 */
public class GetTranslation {

    public int status;
    public content content;
    public static class content {
        public String from;
        public String to;
        public String vendor;
        public String out;
        public int errNo;
    }
}
