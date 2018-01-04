package com.skytreasure.bingflightscraper.Parser;

import com.skytreasure.bingflightscraper.model.FlightModel;

import java.util.List;


public interface ParserResponseInterface {

    void onParsingDone(List<FlightModel> flightModel);
}
