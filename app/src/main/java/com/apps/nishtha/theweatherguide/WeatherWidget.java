package com.apps.nishtha.theweatherguide;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;

import com.apps.nishtha.theweatherguide.Pojo.Details;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Implementation of App Widget functionality.
 */
public class WeatherWidget extends AppWidgetProvider {

    String baseUrl = "https://api.apixu.com/v1/forecast.json?key=1d0d6eccacac4f45a3e133614171308&q=delhi&days=3";
    OkHttpClient okHttpClient;
    String sunrise="";
    GetDetails getDetails;
    RemoteViews remoteViews;
    public static final String TAG="TAG";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_weather);
//            remoteViews.setTextViewText(R.id.appwidget_text, String.valueOf(new Random().nextInt(100)));
            Log.d(TAG, "onUpdate: "+sunrise);
            remoteViews.setTextViewText(R.id.appwidget_text,sunrise);
            getDetails=new GetDetails(remoteViews,appWidgetManager,appWidgetId);
            getDetails.views=remoteViews;
            getDetails.execute(baseUrl+"q=Delhi"+"days=5");
            Intent intent=new Intent();
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra("appWidgetIds",appWidgetIds);
            remoteViews.setOnClickPendingIntent(R.id.btnWidgetRefresh,
                    PendingIntent.getBroadcast(context,123,intent,PendingIntent.FLAG_UPDATE_CURRENT));
            appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    boolean isLocationEnabled(){
        boolean isEnabled=false;

        return isEnabled;
    }

    class GetDetails extends AsyncTask<String,Void,String>{
        RemoteViews views;
        AppWidgetManager appWidgetManager;
        int appWidgetId;
        public GetDetails() {
        }
        public GetDetails(RemoteViews views,AppWidgetManager appWidgetManager,int appWidgetId) {
            this.views=views;
            this.appWidgetManager=appWidgetManager;
            this.appWidgetId=appWidgetId;
        }

        @Override
        protected String doInBackground(String... params) {
            String url=params[0];
            okHttpClient=new OkHttpClient();
            final Gson gson=new Gson();
            Request request=new Request.Builder()
                    .url(url)
                    .build();
            Log.d(TAG, "doInBackground: "+ url);
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Details details=gson.fromJson(response.body().string(),Details.class);
                    sunrise=details.getForecast().getForecastdayArrayList().get(0).getAstro().getSunrise();
                    remoteViews.setTextViewText(R.id.appwidget_text,"wdnjenda");
                }
            });
            Log.d(TAG, "onResponse: "+sunrise);
            return sunrise;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
//            ProgressDialog progressDialog;
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String sunrise) {
            Log.d(TAG, "onPostExecute: "+sunrise);
            views.setTextViewText(R.id.appwidget_text,"Hi");
            views.setTextViewText(R.id.appwidget_text,sunrise);
            remoteViews.setTextViewText(R.id.appwidget_text,sunrise);
            appWidgetManager.updateAppWidget(appWidgetId,views);
            super.onPostExecute(sunrise);
        }
    }
}

