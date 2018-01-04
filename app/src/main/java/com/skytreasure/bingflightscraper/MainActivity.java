package com.skytreasure.bingflightscraper;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.mindorks.placeholderview.PlaceHolderView;
import com.skytreasure.bingflightscraper.Parser.HtmlParser;
import com.skytreasure.bingflightscraper.Parser.ParserResponseInterface;
import com.skytreasure.bingflightscraper.databinding.ActivityMainBinding;
import com.skytreasure.bingflightscraper.model.FlightModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ParserResponseInterface {

    String url = "https://www.bing.com/search?q=";
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        //Execute AsyncTask for Parsing HTML

        mBinding.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new HtmlParser(MainActivity.this).execute(url + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onParsingDone(List<FlightModel> flightModels) {
        Log.e("", "");
        mBinding.phv.removeAllViews();
        for (FlightModel fm : flightModels) {
            mBinding.phv.addView(new FlightItem(MainActivity.this, mBinding.phv, fm));
        }
    }
}
