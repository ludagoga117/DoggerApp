package com.luisgoyes.doggerapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditFranchise extends Fragment {

    private static int ID = 2;
    private View rootView;

    public EditFranchise() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add, container, false);

        EditText EditFNome = (EditText) rootView.findViewById(R.id.AddNome);
        EditText EditFLatitude = (EditText) rootView.findViewById(R.id.AddLatitude);
        EditText EditFLongitude = (EditText) rootView.findViewById(R.id.AddLongitude);
        Button bEditFranchise = (Button) rootView.findViewById(R.id.ButtonAddAcept);
        Button bEditFranchiseCancel = (Button) rootView.findViewById(R.id.ButtonAddCancel);
        EditFNome.setText(MainActivity.getMasterDataBase().get(ID).getNome());
        EditFLatitude.setText(((Double)MainActivity.getMasterDataBase().get(ID).getLatitude()).toString());
        EditFLongitude.setText(((Double)MainActivity.getMasterDataBase().get(ID).getLogitude()).toString());


        bEditFranchise.setOnClickListener(onClickEditFButton(rootView));
        bEditFranchise.setText(getResources().getString(R.string.sEdit));
        bEditFranchiseCancel.setOnClickListener(onClickCancelFButton());
        return rootView;
    }

    private View.OnClickListener onClickEditFButton(final View rootView){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etAddNome = (EditText) rootView.findViewById(R.id.AddNome);
                EditText etAddLat = (EditText) rootView.findViewById(R.id.AddLatitude);
                EditText etAddLong = (EditText) rootView.findViewById(R.id.AddLongitude);

                if( etAddNome.getText().toString().isEmpty() || etAddLat.getText().toString().isEmpty() || etAddLong.getText().toString().isEmpty()){
                    if( etAddNome.getText().toString().isEmpty() ) {
                        Toast.makeText(rootView.getContext(), getResources().getString(R.string.tetAddNome), Toast.LENGTH_SHORT).show();
                    }
                    if( etAddLat.getText().toString().isEmpty() ) {
                        Toast.makeText(rootView.getContext(),getResources().getString(R.string.tetAddLat),Toast.LENGTH_SHORT).show();
                    }
                    if( etAddLong.getText().toString().isEmpty() ) {
                        Toast.makeText(rootView.getContext(),getResources().getString(R.string.tetAddLong),Toast.LENGTH_SHORT).show();
                    }
                }else{

                    if(MainActivity.getMasterDataBase().searchByName(etAddNome.getText().toString(),ID)||MainActivity.getMasterDataBase().searchByLocation(Double.parseDouble(etAddLat.getText().toString()), Double.parseDouble(etAddLong.getText().toString()),ID)){
                        if(MainActivity.getMasterDataBase().searchByName(etAddNome.getText().toString(),ID)){
                            Toast.makeText(rootView.getContext(),getResources().getString(R.string.tsearchAddName),Toast.LENGTH_SHORT).show();
                        }
                        if(MainActivity.getMasterDataBase().searchByLocation(Double.parseDouble(etAddLat.getText().toString()), Double.parseDouble(etAddLong.getText().toString()),ID)){
                            Toast.makeText(rootView.getContext(),getResources().getString(R.string.tsearchAddLocation),Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        MainActivity.getMasterDataBase().replace(ID, Double.parseDouble(etAddLat.getText().toString()), Double.parseDouble(etAddLong.getText().toString()), etAddNome.getText().toString(), null, MainActivity.getDogger_marker_tag());
                        Toast.makeText(rootView.getContext(), getResources().getString(R.string.tEditSuccess), Toast.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(android.R.id.content, new Edit()).commit();
                    }
                }
            }
        };
    }

    private View.OnClickListener onClickCancelFButton(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(android.R.id.content,new Edit()).commit();
            }
        };
    }

    public void setID(int ID){
        this.ID = ID;
    }
}
