package com.example.homework_222;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import com.example.homework_222.tasks.TaskListContent;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskAddingFragment extends Fragment{

    public static final int REQUEST_IMAGE_CAPTURE = 1; // request code for image capture
    private TaskListContent.Task mDsiplayedTask;
    final Random rnd = new Random();

    public TaskAddingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_adding_task, container, false);
        Button addAttraction = (Button) view.findViewById(R.id.add_attraction);
        final FragmentActivity activity = getActivity();
        super.onActivityCreated(savedInstanceState);
        //(activity.findViewById(R.id.displayFragment)).setVisibility(View.VISIBLE);

        final Intent data = getActivity().getIntent();
        final String Uri = data.getStringExtra("photo");

        addAttraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText attractionTitle = (EditText) view.findViewById(R.id.taskTitle1);
                final EditText attractionDescription = (EditText) view.findViewById(R.id.taskDescription1);
                final EditText attractionLocalization = (EditText) view.findViewById(R.id.taskLocalization1);
                final String str;

                if(Uri.contains("random")){
                    str = "drawable " + rnd.nextInt(4);
                }else{
                    str = Uri;
                }
                   // str = "drawable " + rnd.nextInt(4);


                String taskTitle = attractionTitle.getText().toString();
                String taskDescription = attractionDescription.getText().toString();
                String taskLocalization = attractionLocalization.getText().toString();


                if (taskTitle.isEmpty() && taskDescription.isEmpty() && taskLocalization.isEmpty()) {
                    TaskListContent.addItem(new TaskListContent.Task("Task." + TaskListContent.ITEMS.size() + 1,
                            getString(R.string.default_title),
                            getString(R.string.default_description),
                            getString(R.string.default_localization),
                            str));
                } else {
                    if (taskTitle.isEmpty())
                        taskTitle = getString(R.string.default_title);
                    if (taskDescription.isEmpty())
                        taskDescription = getString(R.string.default_description);
                    if(taskLocalization.isEmpty())
                        taskLocalization = getString(R.string.default_localization);
                    TaskListContent.addItem(new TaskListContent.Task("Task." + TaskListContent.ITEMS.size() + 1,
                            taskTitle,
                            taskDescription,
                            taskLocalization,
                            str));
                }

                //((TaskFragment) TaskFragment.getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();

                attractionTitle.setText("");
                attractionDescription.setText("");
                attractionLocalization.setText("");

                String helper =" ";
                Intent data = new Intent();
                data.putExtra(MainActivity.helper,helper);
                //Set the result code for the MainActivity and attach the data Intent
                getActivity().setResult(RESULT_OK, data);

                getActivity().finish();
                //InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                //imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                //Intent intent = new Intent(getActivity(), MainActivity.class);
                //startActivity(intent);
            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            FragmentActivity holdingActivity = getActivity();
            if (holdingActivity instanceof MainActivity) {
                ((TaskFragment) holdingActivity.getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
            } else if (holdingActivity instanceof TaskInfoActivity) {
                ((TaskInfoActivity) holdingActivity).setImgChanged(true);
            }
        }
    }
}
