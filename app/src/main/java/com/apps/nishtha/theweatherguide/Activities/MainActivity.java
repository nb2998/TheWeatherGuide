package com.apps.nishtha.theweatherguide.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.nishtha.theweatherguide.Pojo.Details;
import com.apps.nishtha.theweatherguide.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final int RCODEFORPERMISSION =1;
    public static final int RCODEFORSETTINGS =2;
    public static final String TAG="TAG";
    OkHttpClient okHttpClient;
    TextView textTemp,textLocation,textCondition,textDate;
    ImageView imgCondition;
    String sunrise="";
    GetDetails getDetails;
    BottomNavigationView bottomNavigationView;
    String baseUrl = "https://api.apixu.com/v1/forecast.json?key=1d0d6eccacac4f45a3e133614171308&";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTemp = (TextView) findViewById(R.id.textTemp);
        textLocation= (TextView) findViewById(R.id.textLocation);
        textCondition= (TextView) findViewById(R.id.textCondition);
        textDate= (TextView) findViewById(R.id.textDate);
        imgCondition= (ImageView) findViewById(R.id.imgCondition);
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottomNavigation);

        if((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_DENIED)&&ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, RCODEFORPERMISSION);
        }
        if(isLocationEnabled()){
            Toast.makeText(MainActivity.this,"Enabled",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this,"Disabled",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent,RCODEFORSETTINGS);
        }

        getDetails=new GetDetails();
        getDetails.execute(baseUrl+"q=Delhi&&days=5");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menuAstro){
                    Intent intent=new Intent(MainActivity.this,AstroActivity.class);
                    intent.putExtra("sunrise",sunrise);
                    startActivity(intent);
                }else if(item.getItemId()==R.id.menuLocation){
                    Intent intent=new Intent(MainActivity.this,AddLocationActivity.class);
                    startActivityForResult(intent,123);

                }else if(item.getItemId()==R.id.menuTemp){

                }
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==123){
            if(resultCode==RESULT_OK){
                String newLocation=data.getStringExtra("newLocation");
                Toast.makeText(MainActivity.this,newLocation,Toast.LENGTH_SHORT).show();
                getDetails=new GetDetails();
                getDetails.execute(baseUrl+"q="+newLocation+"&days=5");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    boolean isLocationEnabled(){
        boolean isEnabled=false;
        LocationManager locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            LocationProvider gpsProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
            isEnabled=true;
        } catch (Exception e){
            isEnabled=false;
        }
        try{
            LocationProvider networkProvider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
            isEnabled=true;
        } catch (Exception e){
            isEnabled=false;
        };
        return isEnabled;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode==RCODEFORSETTINGS){
//            if(resultCode!=RESULT_OK){
//                Toast.makeText(MainActivity.this,"Please enable the location settings",Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==RCODEFORPERMISSION){
            if(permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)&&grantResults[0]==PackageManager.PERMISSION_DENIED){
                Toast.makeText(MainActivity.this,"Permission denied!",Toast.LENGTH_SHORT).show();
            }
            if(permissions[1].equals(Manifest.permission.ACCESS_COARSE_LOCATION)&&grantResults[1]==PackageManager.PERMISSION_DENIED){
                Toast.makeText(MainActivity.this,"Permission denied!",Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    class GetDetails extends AsyncTask<String,Void,String> {

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
                    final Details details=gson.fromJson(response.body().string(),Details.class);
                    sunrise=details.getForecast().getForecastdayArrayList().get(0).getAstro().getSunrise();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textTemp.setText(details.getCurrent().getTemp_c()+ "C");
                            textLocation.setText(details.getLocation().getName());
                            Picasso.with(MainActivity.this)
                                    .load("https:"+details.getCurrent().getCondition().getIcon())
                                    .placeholder(R.mipmap.ic_launcher)
                                    .error(R.mipmap.ic_launcher_round)
                                    .into(imgCondition);
                            textCondition.setText(details.getCurrent().getCondition().getText());
                            textDate.setText(details.getForecast().getForecastdayArrayList().get(0).getDate());
                        }
                    });
                }
            });
            Log.d(TAG, "onResponse: "+sunrise);
            return sunrise;
        }
        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        @Override
        protected void onProgressUpdate(Void... values) {
            progressDialog.show();
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String sunrise) {
//            Log.d(TAG, "onPostExecute: "+sunrise);
            progressDialog.hide();
            super.onPostExecute(sunrise);
        }
    }
}
