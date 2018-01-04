package com.skytreasure.bingflightscraper.Parser;

import android.os.AsyncTask;

import com.skytreasure.bingflightscraper.model.FlightModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HtmlParser extends AsyncTask<String, Void, List<FlightModel>> {

    private ParserResponseInterface parserResponseInterface;

    public HtmlParser(ParserResponseInterface parserResponseInterface) {
        this.parserResponseInterface = parserResponseInterface;
    }

    @Override
    protected List<FlightModel> doInBackground(String... params) {

        List<FlightModel> flightModelList = new ArrayList<>();
        String url = params[0];
        Document pageDocument;

        try {
            pageDocument = Jsoup.connect(url).get();


            List<String> otherDetailsList = new ArrayList<>();
            Elements otherDetailsElements = pageDocument.select(".fsmd-status__details");
            for (int i = 0; i < otherDetailsElements.size(); i++) {
                otherDetailsList.add(otherDetailsElements.get(i).text());
                flightModelList.add(new FlightModel());
                flightModelList.get(i).statusDetails = otherDetailsElements.get(i).text();

            }

            List<String> statues = new ArrayList<>();
            Elements statusElements = pageDocument.select(".fsmd-status__badge");
            for (int i = 0; i < statusElements.size(); i++) {
                statues.add(statusElements.get(i).text());
                flightModelList.get(i).statusBadge = statusElements.get(i).text();
            }

            List<String> departureArivalTimings = new ArrayList<>();
            Elements departureAndArrivalTimeingElements = pageDocument.select(".fsmd-leg__updated-time");
            for (int i = 0; i < departureAndArrivalTimeingElements.size(); i++) {
                departureArivalTimings.add(departureAndArrivalTimeingElements.get(i).text());
                flightModelList.get(i).departureArrivalTimings = departureAndArrivalTimeingElements.get(i).text();
            }

            List<String> departureArivalDates = new ArrayList<>();
            Elements departureArivalDatesElements = pageDocument.select(".fsmd-leg__updated-date");
            for (int i = 0; i < departureArivalDatesElements.size(); i++) {
                departureArivalDates.add(departureArivalDatesElements.get(i).text());
                flightModelList.get(i).departureArrivalDates = departureArivalDatesElements.get(i).text();
            }

            List<String> departureArivalCities = new ArrayList<>();
            Elements departureArivalCitiesElements = pageDocument.select(".fsmd-leg__city");
            for (int i = 0; i < departureArivalCitiesElements.size(); i++) {
                departureArivalCities.add(departureArivalCitiesElements.get(i).text());
                flightModelList.get(i).departureArrivalCities = departureArivalCitiesElements.get(i).text();
            }

            List<String> airportInfoList = new ArrayList<>();
            Elements airportInfoElements = pageDocument.select(".fsmd-leg__airport-info");
            for (int i = 0; i < airportInfoElements.size(); i++) {
                airportInfoList.add(airportInfoElements.get(i).text());
                flightModelList.get(i).airportInfo = airportInfoElements.get(i).text();
            }

            List<String> sourceDestinationCodeList = new ArrayList<>();
            Elements sourceDestinationCode = pageDocument.select(".fsmd-progress-bar");
            for (int i = 0; i < sourceDestinationCode.size(); i++) {
                sourceDestinationCodeList.add(sourceDestinationCode.get(i).text());
                flightModelList.get(i).sourceDestinationCode = airportInfoElements.get(i).text();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return flightModelList;
    }

    @Override
    protected void onPostExecute(List<FlightModel> fmlist) {
        super.onPostExecute(fmlist);

        parserResponseInterface.onParsingDone(fmlist);
    }
}
