package zoo.android.csulb.edu.zoo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Kevin on 3/2/2017.
 */

public class CustomAdapter extends ArrayAdapter<Animal> {
    private final List<Animal> animals;


    public CustomAdapter(Context context, int resource, List<Animal> animals) {
        super(context, resource, animals);
        this.animals = animals;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Animal animal = animals.get(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.detail_fragment,null);
        }
        View row = inflater.inflate(R.layout.custom_row,null);


        //Set text
        TextView textView = (TextView) row.findViewById(R.id.rowText);
        textView.setText(animal.getName());
        int img[] = {R.drawable.zebra,R.drawable.tiger,R.drawable.lion, R.drawable.penguin, R.drawable.giraffe};
        //Set img
        ImageView imageView = (ImageView) row.findViewById(R.id.rowImage);
        imageView.setImageResource(img[position]);

        return row;
    }
}
