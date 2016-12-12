package itp341.wang.cherrie.contact.tab_fragments;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;

import itp341.wang.cherrie.contact.R;
import itp341.wang.cherrie.contact.SavedActivity;
import itp341.wang.cherrie.contact.model.Senator;


public class ThreeFragment extends Fragment {

    private Senator fragSenator3;
    private TextView address;
    private Button goToMapsButton;
    private LatLng addressForMaps;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_three, container, false);
        Bundle bundle = getArguments();
        fragSenator3 = bundle.getParcelable("sen");
        address = (TextView) rootView.findViewById(R.id.officeLoc);
        goToMapsButton = (Button) rootView.findViewById(R.id.buttonMaps);

        address.setText(fragSenator3.getOffice().toString());

        listeners();
        // Inflate the layout for this fragment
        return rootView;
    }


    private void listeners(){
        // Listener to login
        goToMapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start intent
                openInMaps(fragSenator3.getOffice().toString());
            }
        });
    }

    public void openInMaps(String address){
        //.replace(".", "%20");
//        addressForMaps = getLocationFromAddress(getActivity(), fragSenator3.getOffice().toString());
//        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", addressForMaps.latitude, addressForMaps.latitude);
//        Log.e("address: ", "long: "+addressForMaps.latitude+" lat: "+addressForMaps.latitude);
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//        startActivity(intent);

        String uri = String.format(Locale.ENGLISH,
                "http://maps.google.com/maps?&daddr=%f,%f (%s)", 12f, 2f, fragSenator3.getOffice().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }
}
