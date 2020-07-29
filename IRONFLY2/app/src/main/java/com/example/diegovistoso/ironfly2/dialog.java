package com.example.diegovistoso.ironfly2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class dialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        //alert dialog con las instrucciones del juego
     builder.setTitle("COMO JUGAR").setMessage("1)Cada toque es 1 punto" +
             "\n" +
             "2) Las moscas rojas quitan 1 vida").setPositiveButton("OK", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialogInterface, int i) {

         }
     });
    return builder.create();
    }
}
