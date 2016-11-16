package com.mycompany.daummapapptest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;
import net.daum.mf.map.common.MapConnectionSettingManager;


public class MapsActivity extends AppCompatActivity implements MapView.MapViewEventListener,MapView.CurrentLocationEventListener, MapView.POIItemEventListener, MapView.OpenAPIKeyAuthenticationResultListener {
    private static final String DAUM_API_KEY = "295d7ff2170cfd95de1181ca721e3d9c";
    public MapView mapView;
    public Location cloc;
    public String TAG ="jjang";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapView.setMapTilePersistentCacheEnabled(true);
        mapView = new MapView(MapsActivity.this); // MapView 객체 생성
        mapView.setDaumMapApiKey(DAUM_API_KEY); // API 키 설정
        mapView.setOpenAPIKeyAuthenticationResultListener(this);
        mapView.setDefaultCurrentLocationMarker();
        mapView.setShowCurrentLocationMarker(true);
        mapView.setMapViewEventListener(this);
        mapView.setPOIItemEventListener(this);
        mapView.setCurrentLocationEventListener(this);

        ViewGroup viewGroup = (ViewGroup)findViewById(R.id.fl_maps); //인수로 참조할 아이디R.id.fl_maps 전달
        viewGroup.addView(mapView);


//        //권한 체크
//        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
//        if(permissionCheck== PackageManager.PERMISSION_DENIED){
//            Log.d("log","deny permission");
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1); //권한 팝업
//        } else {
//            Log.d("log","access permission");
//        }


        //플로팅 버튼
        final boolean[] chk = {true};
        FloatingActionButton fab1 = (FloatingActionButton)findViewById(R.id.mylocation);
        fab1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Log.d("log","11111");
                if(chk[0]){
                    Toast.makeText(MapsActivity.this, "나침반모드 온", Toast.LENGTH_SHORT).show();
                    Log.d("log","kbc true");
                    chk[0] = false;
                    mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);//현재위치이동 나침반모드
                }else {
                    Toast.makeText(MapsActivity.this, "나침반모드 오프", Toast.LENGTH_SHORT).show();
                    Log.d("log","kbc false");
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
                mapView.setZoomLevel(5,true);
            Log.d("jjang", "onCreate: " + mapView.getZoomLevel());
//                Log.d("log","22222");
                if(chk2[0]){
                    Toast.makeText(MapsActivity.this, "현재위치 트래킹 온", Toast.LENGTH_SHORT).show();
                    Log.d("log","kbc true");
                    chk2[0] = false;
                    mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);//현재위치이동
                }else {
                    Toast.makeText(MapsActivity.this, "현재위치 트래킹 오프", Toast.LENGTH_SHORT).show();
                    Log.d("log","kbc false");
                    chk2[0] = true;
                    mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
                }
            }
        });

        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Default Marker");//아이템 네임설정
        marker.setTag(0); //태그설정
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(37.551094, 127.019470)); //마커 위치 설정
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);



        MapPolyline polyline = new MapPolyline();
        polyline.setTag(1000);
        polyline.setLineColor(Color.argb(255, 255, 51, 0)); // Polyline 컬러 지정.

        // Polyline 좌표 지정.
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.5548768201904, 126.96966524449994));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.55308718044556, 126.97642899633566));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.55522076659584, 126.97654602427454));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.55320655210504, 126.97874667968763));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.55368689494708, 126.98541456064552));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.54722934282707, 126.995229135048));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.549694559809545, 126.99832516302801));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.550159406110104, 127.00436818301327));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.54820235864802, 127.0061334023129));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.546169758665414, 127.00499711608721));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.54385947805103, 127.00727818360471));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.54413326436179, 127.00898460651953));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.539639030116945, 127.00959054834321));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.537681185520256, 127.01726163044557));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.53378887274516, 127.01719284893274));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.52290225898471, 127.00614038053493));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.51309192794448, 126.99070240960813));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.50654651085339, 126.98553683648308));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.50702053393398, 126.97524914998174));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.51751820477105, 126.94988506562748));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.52702918583156, 126.94987870367682));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.534519656862926, 126.94481851935942));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.537500243531994, 126.95335659960566));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.54231338779177, 126.95817394011969));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.54546318600178, 126.95790512689311));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.548791603525764, 126.96371984820232));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.55155543391863, 126.96233786542686));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.5541513366375, 126.9657135934734));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.55566236579088, 126.9691850696746));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.5548768201904, 126.96966524449994));

        // Polyline 지도에 올리기.
        mapView.addPolyline(polyline);

        MapPolyline polyline2 = new MapPolyline();
        polyline2.setTag(1000);
        polyline2.setLineColor(Color.argb(255, 255, 51, 0)); // Polyline 컬러 지정.
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.57275246810175, 127.04241813085706));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.57038253579033, 127.04794980454399));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.56231553903832, 127.05876047165025));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.5594131360664, 127.07373408220053));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.52832388381049, 127.05621773388143));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.52832388381049, 127.05621773388143));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.53423885672233, 127.04604398310076));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.53582328355087, 127.03979942567628));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.53581367627865, 127.0211714455564));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.53378887274516, 127.01719284893274));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.537681185520256, 127.01726163044557));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.53938672166098, 127.00993448922989));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.54157804358092, 127.00879872996808));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.54502351209897, 127.00815187343248));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.547466935106435, 127.00931996404753));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.55264513061776, 127.01620129137214));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.556850715898484, 127.01807638779917));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.55779412537163, 127.0228934248264));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.5607276739534, 127.02339232029838));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.563390358462826, 127.02652159646888));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.56505173515675, 127.02678930885806));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.565200182350495, 127.02358387477513));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.57190723475508, 127.02337770475695));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.56978273516453, 127.03099733100001));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.57302061077518, 127.0381755492195));
        polyline2.addPoint(MapPoint.mapPointWithGeoCoord(37.57275246810175, 127.04241813085706));

        // Polyline 지도에 올리기.
        mapView.addPolyline(polyline2);


        MapPolyline polyline3 = new MapPolyline();
        polyline3.setTag(1000);
        polyline3.setLineColor(Color.argb(255, 255, 51, 0)); // Polyline 컬러 지정.
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.607062869017085, 127.07111288773496));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.60107201319839, 127.07287376670605));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.59724304056685, 127.06949105186925));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.58953367466315, 127.07030363208528));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.58651213184981, 127.07264218709383));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.5849555116177, 127.07216063016078));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.58026781100598, 127.07619547037923));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.571869232268774, 127.0782018408153));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.559961773835425, 127.07239004251258));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.56231553903832, 127.05876047165025));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.57038253579033, 127.04794980454399));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.572878529071055, 127.04263554582458));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.57302061077518, 127.0381755492195));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.56978273516453, 127.03099733100001));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.57190723475508, 127.02337770475695));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.57838361223621, 127.0232348231103));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.58268174514337, 127.02953994610249));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.58894739851823, 127.03553876830637));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.5911852565689, 127.03621919708065));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.59126734230753, 127.03875553445558));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.5956815721534, 127.04062845365279));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.5969637344377, 127.04302522879048));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.59617641777492, 127.04734129391157));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.60117358544485, 127.05101351973708));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.600149587503246, 127.05209540476308));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.60132672748398, 127.05508130598699));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.6010580545608, 127.05917142337097));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.605121767227374, 127.06219611364686));
        polyline3.addPoint(MapPoint.mapPointWithGeoCoord(37.607062869017085, 127.07111288773496));
        // Polyline 지도에 올리기.
        mapView.addPolyline(polyline3);

    }


    @Override
    public void onMapViewInitialized(MapView mapView) {
        //Map 초기화 실행
        Toast.makeText(MapsActivity.this, "Initialized...", Toast.LENGTH_SHORT).show();
        Log.d("log", "Initialized");
        Log.d(TAG, "onMapViewInitialized:========================= "+mapView.getZoomLevel());
        mapView.setZoomLevel(7,true);
        Log.d(TAG, "onMapViewInitialized:========================= "+mapView.getZoomLevel());
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.566826, 126.9786567),true);
        //권한 체크
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck== PackageManager.PERMISSION_DENIED){
            Log.d("log","deny permission");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1); //권한 팝업
        } else {
            Log.d("log","access permission");
        }
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        //언제쓰는거지?
//        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.551094, 127.019470),true);
        Log.i("log", "MapView onMapViewCenterPointMoved");

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
        //줌 레벨이 바뀌었을 때 호출
//        Toast.makeText(MapsActivity.this, "ZoomLevel Changed...", Toast.LENGTH_SHORT).show();
        Log.i("log", "MapView onMapViewZoomLevelChanged");

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        Log.i("log", "MapView onMapViewSingleTapped");

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
        Log.i("log", "MapView onMapViewDoubleTapped");

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
        Log.i("log", "MapView onMapViewLongPressed");

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        Log.i("log", "MapView onMapViewDragStarted");

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
        Log.i("log", "MapView onMapViewDragEnded");

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        Log.i("log", "MapView onMapViewMoveFinished");

    }


    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
        Log.i("log", "MapView onCurrentLocationUpdate");
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {
        Log.i("log", "MapView onCurrentLocationDeviceHeadingUpdate");
    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {
        Log.i("log", "MapView onCurrentLocationUpdateFailed");
    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {
        Log.i("log", "MapView onCurrentLocationUpdateCancelled");
    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        Log.i("log", "MapView onPOIItemSelected");
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
        Log.i("log", "MapView onCalloutBalloonOfPOIItemTouched");
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
        Log.i("log", "MapView onCalloutBalloonOfPOIItemTouched");

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
        Log.i("log", "MapView onDraggablePOIItemMoved");

    }

    @Override
    public void onDaumMapOpenAPIKeyAuthenticationResult(net.daum.mf.map.api.MapView mapView, int i, java.lang.String s){
        Log.i("log", "MapView onDaumMapOpenAPIKeyAuthenticationResult");

    }



}