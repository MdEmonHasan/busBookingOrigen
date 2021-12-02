package com.createapp.busbooking.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.createapp.busbooking.R;

import java.text.BreakIterator;

public class LandingCustomHolder extends RecyclerView.ViewHolder {

    public TextView busName,busFrom,busDestination;
    public ImageView busImage;
    public CardView mainCard;
    public LandingCustomHolder(@NonNull View itemView) {
        super(itemView);

        busName = itemView.findViewById(R.id.busName);
        busFrom = itemView.findViewById(R.id.busFrom);
        busDestination = itemView.findViewById(R.id.busDestination);
        busImage = itemView.findViewById(R.id.busImage);
        mainCard = itemView.findViewById(R.id.mainCard);
    }
}
