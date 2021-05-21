package Miosz.newsPlatform_BingCrawler.BLL;

import java.util.HashMap;

public class SearchResults {
    public HashMap<String, String> relevantHeaders;
    public String jsonResponse;

    SearchResults(HashMap<String, String> headers, String json) {
        relevantHeaders = headers;
        jsonResponse = json;
    }
}
