package gmaps.android.csulb.edu.mejk;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static gmaps.android.csulb.edu.mejk.R.id.activity_main;


public class MainActivity extends AppCompatActivity implements LoaderCallbacks<Cursor>, OnMapReadyCallback {
    private final LatLng Location_UNIV = new LatLng(33.783768,-118.114336);
    private final LatLng Location_ECS = new LatLng(33.782777,-118.111868);

    private static int DATABASE_VERSION = 1;
    private static final String DATBASE_NAME = "db";
    private static final String TABLE_NAME = "markers";

    private static final String KEY_ID = "id";
    private static final String KEY_LAT = "latitude";
    private static final String KEY_LNG = "longitude";
    private static final String KEY_ZOOM = "zoom";

    private GoogleMap map;
    private LocationsContentProvider content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportLoaderManager().initLoader(0, null, this);
        MapFragment mapFrag = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        // Invoke LoaderCallbacks to retrieve and draw already saved locations in map
    }


    public void onClick_ECS(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(Location_ECS,16);
        map.animateCamera(update);
    }

    public void onClick_LongBeachUniv(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(Location_UNIV,14);
        map.animateCamera(update);
    }

    public void onClick_City(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(Location_UNIV,9);
        map.animateCamera(update);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setTrafficEnabled(true);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Find me here!"));
                LocationInsertTask insertTask = new LocationInsertTask();
               // insertTask.doInBackground();

                ContentValues values = new ContentValues();
                values.put(KEY_LAT,latLng.latitude);
                values.put(KEY_LNG, latLng.longitude);
                values.put(KEY_ZOOM, map.getCameraPosition().zoom);
                insertTask.execute(values);
                Toast.makeText(getBaseContext(),"A marker has been added!",Toast.LENGTH_SHORT).show();

            }
        });

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                map.clear();
                LocationDeleteTask deleteTask = new LocationDeleteTask();
                deleteTask.execute();
                Toast.makeText(getBaseContext(),"All markers have been removed!",Toast.LENGTH_LONG).show();
            }
        });

        map.addMarker(new MarkerOptions()
            .position(Location_ECS)
            .title("Find me here!"));
    }




   private class LocationInsertTask extends AsyncTask<ContentValues, Void, Void> {
        protected Void doInBackground(ContentValues... contentValues) {
            getContentResolver().insert(LocationsContentProvider.CONTENT_URI, contentValues[0]);
            return null;
        }
    }

    private class LocationDeleteTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            getContentResolver().delete(LocationsContentProvider.CONTENT_URI, null, null);
            return null;
        }
    }




    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = LocationsContentProvider.CONTENT_URI;
        return new CursorLoader(this,uri,null,null,null,null);

    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> arg0, Cursor arg1) {
        int locationCount = 0;
        double lat = 0;
        double lng = 0;
        float zoom = 0;
        if(arg1 != null) {
            locationCount = arg1.getCount();
            arg1.moveToFirst();
        }
        else {
            locationCount = 0;
        }
        for(int i = 0; i < locationCount; i++) {
            // Get the latitude
            lat = arg1.getDouble(arg1.getColumnIndex(KEY_LAT));

            // Get the longitude
            lng = arg1.getDouble(arg1.getColumnIndex(KEY_LNG));

            // Get the zoom level
            zoom = arg1.getFloat(arg1.getColumnIndex(KEY_ZOOM));

            // Creating an instance of LatLng to plot the location in Google Maps
            LatLng location = new LatLng(lat, lng);

            map.addMarker(new MarkerOptions()
                    .position(location)
                    .title("Find me here!"));

            // Traverse the pointer to the next row
            arg1.moveToNext();
        }
        if(locationCount > 0) {
            map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat,lng)));
            map.animateCamera(CameraUpdateFactory.zoomTo(zoom));

        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {

    }





}

