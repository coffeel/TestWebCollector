package com.coffeel;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Created by xue on 16-8-10.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        String crawlStorageFolder = "/home/xue/IdeaProjects/TestWebCollector/data";
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(4);
        config.setMaxPagesToFetch(1000);
        config.setUserAgentString("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed("https://www.amazon.cn/%E5%9B%BE%E4%B9%A6/b/ref=topnav_storetab_b?ie=UTF8&node=658390051");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }
}
