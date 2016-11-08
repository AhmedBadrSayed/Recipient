package com.projects.ahmedbadr.recipient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.util.Arrays;

public class Tracking extends AppCompatActivity {

    PubNub pubNub;
    TextView locationTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        locationTv = (TextView) findViewById(R.id.location);
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-4a62d6a8-9515-11e6-82f8-02ee2ddab7fe");
        pnConfiguration.setPublishKey("pub-c-4194e9ae-c331-46a8-8188-dfb7b1498cea");

        pubNub = new PubNub(pnConfiguration);

        pubNub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {

            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                // Handle new message stored in message.message
                if (message.getChannel() != null) {
                    Log.d("Channel ",message.getChannel());
                }
                else {
                    Log.d("Subscrip ",message.getSubscription());
                }

                Log.d("Message ", String.valueOf(message.getMessage()));
                Log.d("Time ", String.valueOf(message.getTimetoken()));
                locationTv.setText(String.valueOf(message.getMessage()));
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });

        pubNub.subscribe().channels(Arrays.asList("Track")).execute();
    }
}
