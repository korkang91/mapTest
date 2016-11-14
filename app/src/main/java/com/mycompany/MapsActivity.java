package com.mycompany;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mycompany.daummapapptest.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import net.daum.mf.map.api.MapCircle;
import android.graphics.Color;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

public class MapsActivity extends AppCompatActivity implements MapView.MapViewEventListener,MapView.CurrentLocationEventListener, MapView.POIItemEventListener {
    private static final String DAUM_API_KEY = "295d7ff2170cfd95de1181ca721e3d9c";
    TextView textview;
    Document doc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        final MapView mapView = new MapView(this); // MapView 객체 생성
        mapView.setDaumMapApiKey(DAUM_API_KEY); // API 키 설정

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.fl_maps); //인수로 참조할 아이디R.id.fl_maps 전달
        viewGroup.addView(mapView);

        mapView.setDefaultCurrentLocationMarker();
        mapView.setShowCurrentLocationMarker(true);

        mapView.setMapViewEventListener(this);
        mapView.setPOIItemEventListener(this);
        mapView.setCurrentLocationEventListener(this);
// test
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeadingWithoutMapMoving);
//        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.570705,126.958008),true); //초기 지도 위치 설정

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck== PackageManager.PERMISSION_DENIED){
            Log.d("log","deny permission");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1); //권한 팝업
        } else {
            Log.d("log","access permission");
        }

//        MapView.CurrentLocationEventListener;
        final boolean[] chk = {true};
        FloatingActionButton fab1 = (FloatingActionButton)findViewById(R.id.mylocation);
        fab1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d("log","11111");
                if(chk[0]){
                    Toast.makeText(MapsActivity.this, "나침반모드 온", Toast.LENGTH_SHORT).show();
                    Log.d("log","true");
                    chk[0] = false;
                    mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);//현재위치이동 나침반모드
                }else {
                    Toast.makeText(MapsActivity.this, "나침반모드 오프", Toast.LENGTH_SHORT).show();
                    Log.d("log","false");
                    chk[0] = true;
                    mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
                }

            }
        });

        final boolean[] chk2 = {true}; //현제위치이동 기능 체크 변수
        FloatingActionButton fab2 = (FloatingActionButton)findViewById(R.id.check); //현제위치체크 플로팅 버튼
        fab2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("log","22222");
                if(chk2[0]){
                    Toast.makeText(MapsActivity.this, "현재위치 트래킹 온", Toast.LENGTH_SHORT).show();
                    Log.d("log","true");
                    chk2[0] = false;
                    mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);//현재위치이동
                }else {
                    Toast.makeText(MapsActivity.this, "현재위치 트래킹 오프", Toast.LENGTH_SHORT).show();
                    Log.d("log","false");
                    chk2[0] = true;
                    mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
                }
            }
        });
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {
        Toast.makeText(MapsActivity.this, "Initialized...", Toast.LENGTH_SHORT).show();
        Log.d("log","Initialized");
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }


    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {

    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }



}
