package ca.vivyd.vivydcalculator.menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ca.vivyd.vivydcalculator.MainActivity;
import ca.vivyd.vivydcalculator.R;
import ca.vivyd.vivydcalculator.themes.Themer;


/**
 * Created by Amair on 6/15/2016.
 */
public class adfFragment extends Fragment implements View.OnClickListener {

    public adfFragment(){

    }

    public static adfFragment getInstance(int position){
        adfFragment abtFragment = new adfFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        abtFragment.setArguments(args);
        return abtFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View adfView = inflater.inflate(R.layout.fragment_adfree, container, false);
        adfView.setBackgroundColor(Themer.colorArray.get(Themer.COLOR_BACKGROUND));


        Button adfreeButton = (Button) adfView.findViewById(R.id.adfButton);
        adfreeButton.setOnClickListener(this);

        return adfView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adfButton:
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", MainActivity.CONTACT_EMAIL, null));
                //i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_SUBJECT, "User Feedback");
                try {
                    startActivity(Intent.createChooser(i, "Send feedback..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    // Toast.makeText(OptFragment.this, "You don't have an email client!", Toast.LENGTH_LONG).show();
                }
        }
    }
}
