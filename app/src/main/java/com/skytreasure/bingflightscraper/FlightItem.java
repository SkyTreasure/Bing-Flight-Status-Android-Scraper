package com.skytreasure.bingflightscraper;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.skytreasure.bingflightscraper.model.FlightModel;

/**
 * Created by akash on 21/12/17.
 */
@Layout(R.layout.item_flight_card)
public class FlightItem {

    @View(R.id.label)
    TextView tvLabel;
    @View(R.id.tv_source)
    TextView tvSource;
    @View(R.id.tv_destination)
    TextView tvDestination;
    @View(R.id.tv_date)
    TextView tvDate;
    @View(R.id.tv_day)
    TextView tvDay;
    @View(R.id.tv_flightname)
    TextView tvFlightName;
    @View(R.id.tv_flightnumber)
    TextView tvFlightNumber;
    @View(R.id.tv_pnr)
    TextView tvPnr;
    @View(R.id.tv_journey_stime)
    TextView tvJourneyStarttime;
    @View(R.id.tv_journey_etime)
    TextView tvJourneyEndtime;
    @View(R.id.tv_status)
    TextView tvStatus;

    private Context mContext;
    private PlaceHolderView mPlaceHolderView;
    private FlightModel flightModel;

    public FlightItem(Context mContext, PlaceHolderView mPlaceHolderView, FlightModel fm) {
        this.flightModel = fm;
        this.mContext = mContext;
        this.mPlaceHolderView = mPlaceHolderView;
    }

    @Resolve
    private void onResolved() {
        tvLabel.setText(flightModel.departureArrivalDates.substring(0, flightModel.departureArrivalDates.length() / 2));
        tvSource.setText(flightModel.departureArrivalCities.substring(0, flightModel.departureArrivalCities.length() / 2 + 1));
        tvDestination.setText(flightModel.departureArrivalCities.substring(flightModel.departureArrivalCities.length() / 2 + 1, flightModel.departureArrivalCities.length()));
        //   tvDate.setText(flightModel.departureArrivalDates);
        tvJourneyStarttime.setText(flightModel.departureArrivalTimings.substring(0,flightModel.departureArrivalTimings.length()/2+1));
        tvJourneyEndtime.setText(flightModel.departureArrivalTimings.substring(flightModel.departureArrivalTimings.length()/2+1,flightModel.departureArrivalTimings.length()));
        tvFlightNumber.setText(flightModel.sourceDestinationCode);
    }
}
