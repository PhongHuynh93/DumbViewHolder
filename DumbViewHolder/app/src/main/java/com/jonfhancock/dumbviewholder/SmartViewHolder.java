package com.jonfhancock.dumbviewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//info this is the smartviewholder
public class SmartViewHolder extends RecyclerView.ViewHolder {
    TextView textTitle;
    TextView textLocation;
    TextView textDate;
    ImageView mapIcon;
    View textContainer;

    Item item;
    ExcellentAdventureListener listener;

    // info - We can improve things even more by having our ViewHolder define an Interface that the Activity or Fragment can implement, and the Adapter can pass to the ViewHolder.
    public interface ExcellentAdventureListener{
        void onMapClicked(Item item);
        void onTitleClicked(Item item);
    }

    public SmartViewHolder(View itemView, final ExcellentAdventureListener listener) {
        super(itemView);
        this.listener = listener;
        // info - make all the findviewbyid here
        textTitle = (TextView) itemView.findViewById(R.id.text_title);
        textLocation = (TextView) itemView.findViewById(R.id.text_location);
        textDate = (TextView) itemView.findViewById(R.id.text_date);
        mapIcon = (ImageView) itemView.findViewById(R.id.map_icon);
        textContainer = itemView.findViewById(R.id.text_container);

//        info make the seton click on the viewholder too
        // We can set the onClickListeners once in the constructor rather than over and over again at bind time.
        textContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTitleClicked(item);
            }
        });
        mapIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMapClicked(item);
            }
        });
    }

    // info - make the adapter a liiter smarter, by put the set item here
    public void setItem(Item item) {
        this.item = item;
        textTitle.setText(item.getTitle());
        textLocation.setText(item.getLocationName());
        textDate.setText(getFormattedDate(item));
    }
    private String getFormattedDate(Item item) {
        String date = item.getYear() + " " + item.getEra();
        return date;
    }

}
