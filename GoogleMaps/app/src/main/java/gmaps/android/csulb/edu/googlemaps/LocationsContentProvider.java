package gmaps.android.csulb.edu.googlemaps;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Kevin on 4/17/2017.
 */

public class LocationsContentProvider extends ContentProvider {
    static final String PROVIDER_NAME = "gmaps.android.csulb.edu.googlemaps";
    static final String URL = "content://" + PROVIDER_NAME + "/locations";
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final int LOCATIONS = 1;
    LocationsDB ldb;
    private static final UriMatcher uriMatcher ;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "locations", LOCATIONS);
    }


    private static int DATABASE_VERSION = 1;
    private static final String DATBASE_NAME = "db";
    private static final String TABLE_NAME = "markers";

    private static final String KEY_ID = "id";
    private static final String KEY_LAT = "latitude";
    private static final String KEY_LNG = "longitude";
    private static final String KEY_ZOOM = "zoom";

    public class LocationsDB extends SQLiteOpenHelper {
        SQLiteDatabase db;

        public LocationsDB(Context context) {
            super(context,DATBASE_NAME, null, DATABASE_VERSION);
            this.db = getWritableDatabase();
        }

        public void onCreate(SQLiteDatabase db) {
            String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + KEY_LAT +
                    " DOUBLE, " + KEY_LNG + " DOUBLE, " + KEY_ZOOM + " INTEGER)";
            db.execSQL(CREATE_TABLE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
            onCreate(db);
        }

        public long insert(ContentValues contentValues){
            long rowID = db.insert(TABLE_NAME, null, contentValues);
            return rowID;

        }

        public int del(){
            int cnt = db.delete(TABLE_NAME, null , null);
            return cnt;
        }

        public Cursor getAllLocations(){
            return db.query(TABLE_NAME, new String[] { KEY_ID,  KEY_LAT , KEY_LNG, KEY_ZOOM } , null, null, null, null, null);
        }




    }


    @Override
    public boolean onCreate() {
        ldb = new LocationsDB(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if(uriMatcher.match(uri)==LOCATIONS){
            return ldb.getAllLocations();
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId = ldb.insert(values);

        if (rowId > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        count = ldb.del();
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;

        return count;
    }
}
