package com.example.zerlin.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

/**
 * Created by Zerlin on 2018/3/18.
 */

class MyDialog extends Dialog {

    MyDialog(@NonNull final Context context) {
        super(context);
        setContentView(R.layout.dialog_myself);
        Button button1 = findViewById(R.id.Yes);
        Button button2 = findViewById(R.id.No);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
