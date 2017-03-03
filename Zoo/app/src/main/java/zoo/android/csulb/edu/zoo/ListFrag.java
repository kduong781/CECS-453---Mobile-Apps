package zoo.android.csulb.edu.zoo;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 3/2/2017.
 */

public class ListFrag extends ListFragment implements AdapterView.OnItemClickListener {
    private List<Animal> animals = new ArrayList();
    private ListView listView;
    private int proceed = 0;
    private Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle s) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.custom_list_view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle s) {
        super.onActivityCreated(s);

        animals.add(new Animal("Zebra","A striped white horse."));
        animals.add(new Animal("Tiger", "A striped orange cat."));
        animals.add(new Animal("Lion", "A big cat usually found in the safari."));
        animals.add(new Animal("Penguin", "A bird usually found by the North Pole."));
        animals.add(new Animal("Giraffe", "A long headed animal with spots."));
        CustomAdapter adapter = new CustomAdapter(getActivity(),R.id.custom_list_view, animals);
        getListView().setAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent = new Intent(getActivity(), Detail.class);
        intent.putExtra("name", animals.get(position).getName());
        intent.putExtra("desc", animals.get(position).getDesc());
        intent.putExtra("pos", position);
        if(position == animals.size()- 1) {
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setMessage("This animal is very scary. Do you wish to proceed?")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            // Do stuff if user accepts

                            startActivity(intent);
                        }
                    }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                            proceed = 0;
                        }
                    }).setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface dialog) {
                            dialog.dismiss();
                            // Do stuff when cancelled
                        }
                    }).create();
            dialog.show();
        } else {
            startActivity(intent);
        }


/*//        detailFrag.setText(animals.get(position).getName(),animals.get(position).getDesc());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment list = getFragmentManager().findFragmentById(R.id.animalList);
        transaction.remove(list);
        transaction.replace(R.id.container, detailFrag);
        transaction.addToBackStack(null);
    transaction.commit();*/
}

}
