package com.example.inmyhands;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Map<String, Object>> students;

    private Context context;
    private String batch;

    public StudentAdapter(Context context, List<Map<String, Object>> students, String batch) {
        this.context = context;
        this.students = students;
        this.batch = batch;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Map<String, Object> student = students.get(position);
        holder.nameTextView.setText((String) student.get("Name"));
        holder.branchTextView.setText((String) student.get("Branch"));
        holder.registernoTextView.setText((String) student.get("Registerno"));
        holder.vuidTextView.setText(String.valueOf(student.get("Student mobile")));
        holder.itemView.setTag(R.id.registernoTextView, student.get("Registerno"));
        holder.itemView.setTag(R.id.vuidTextView, String.valueOf(student.get("Student mobile")));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void updateData(List<Map<String, Object>> newStudents) {
        this.students = newStudents;
        notifyDataSetChanged();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        public TextView nameTextView;
        public TextView branchTextView;
        public TextView registernoTextView;
        public TextView vuidTextView;

        public StudentViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            branchTextView = itemView.findViewById(R.id.branchTextView);
            registernoTextView = itemView.findViewById(R.id.registernoTextView);
            vuidTextView = itemView.findViewById(R.id.vuidTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String registerno = (String) view.getTag(R.id.registernoTextView);
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("reg", registerno);
            intent.putExtra("batch", batch);
            context.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            String studentMobile = (String) view.getTag(R.id.vuidTextView);
            String url = "https://api.whatsapp.com/send?phone=91" + studentMobile;
            try {
                PackageManager pm = context.getPackageManager();
                pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            } catch (PackageManager.NameNotFoundException e) {
                Toast.makeText(context, "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return true;
        }
    }
}
