package shivam.com.custom_marker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import de.hdodenhof.circleimageview.CircleImageView;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    boolean isExpand = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);

        final LatLng customMarkerLocationOne = new LatLng(28.5429519, 77.2374969);

        mMap.addMarker(new MarkerOptions().position(customMarkerLocationOne).icon(BitmapDescriptorFactory.fromBitmap(
                createCustomMarker(this,R.drawable.my_dp, R.layout.layout_custom_marker1, "Shivam Sharma"))));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(customMarkerLocationOne));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker arg0)
            {
                if(!isExpand)
                {
                    arg0.setIcon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MapsActivity.this,R.drawable.my_dp,R.layout.layout_custom_marker2, "Shivam Sharma")));
                    isExpand = true;
                }
                else
                {
                    arg0.setIcon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MapsActivity.this,R.drawable.my_dp,R.layout.layout_custom_marker1, "Shivam Sharma")));
                    isExpand = false;
                }

                return true;
            }
        });
    }

    public Bitmap createCustomMarker(final Context context, @DrawableRes int resource, @LayoutRes int layout, String name)
    {
        View markerView = LayoutInflater.from(context).inflate(layout, null);
        CircleImageView mUserIcon = markerView.findViewById(R.id.civ_user);
        mUserIcon.setImageResource(resource);

        if(layout == R.layout.layout_custom_marker2)
        {
            TextView mUsername = markerView.findViewById(R.id.tv_user_name);
            mUsername.setText(name);
        }


        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((MapsActivity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        markerView.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        markerView.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        markerView.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        markerView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(markerView.getMeasuredWidth(), markerView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        markerView.draw(canvas);
        return bitmap;
    }
}
