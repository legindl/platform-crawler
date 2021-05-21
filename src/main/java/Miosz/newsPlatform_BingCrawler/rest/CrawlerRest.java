package Miosz.newsPlatform_BingCrawler.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Miosz.newsPlatform_BingCrawler.BLL.Crawler;
import Miosz.newsPlatform_BingCrawler.BLL.SearchResults;
@Path("/api")
public class CrawlerRest {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public void get() throws Exception {
        System.out.println("Crawler up");
    }

    @GET
    @Path("{market}/search/{phrase}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSearch(@PathParam("phrase") String searchPhrase,
                            @PathParam("market") String marketCode){
        System.out.println("Searching for: " + searchPhrase + " for market: " + marketCode);
        SearchResults result = null;
        try {
            result = new Crawler().SearchNews(searchPhrase, marketCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result != null) {
            return Response.ok(new Crawler().prettify(result.jsonResponse), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("{market}/category/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("category") String searchCategory,
                            @PathParam("market") String marketCode){
        System.out.println("Searching for category: " + searchCategory + " for market: " + marketCode);
        SearchResults result = null;
        try {
            result = new Crawler().SearchByCategory(searchCategory, marketCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result != null) {
            return Response.ok(new Crawler().prettify(result.jsonResponse), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }
}