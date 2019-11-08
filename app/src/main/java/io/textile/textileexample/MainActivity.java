package io.textile.textileexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

import io.textile.ipfslite.Peer;

public class MainActivity extends AppCompatActivity {

    Peer litePeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initIPFS();
    }

    public void onButtonClick(View v) {
        try {
            String cid = litePeer.addFile("Hello World".getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            byte[] file = litePeer.getFile("bafybeic35nent64fowmiohupnwnkfm2uxh6vpnyjlt3selcodjipfrokgi");
            System.out.println("Success: " + new String(file, "UTF-8"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void initIPFS() {
        try {
            Context ctx = getApplicationContext();
            final File filesDir = ctx.getFilesDir();
            final String path = new File(filesDir, "ipfslite").getAbsolutePath();
            litePeer = new Peer(path);
            litePeer.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    private void destroyTextile() {
//        Textile.instance().destroy();
//    }
}
