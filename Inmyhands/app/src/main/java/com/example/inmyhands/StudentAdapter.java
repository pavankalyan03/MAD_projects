package com.example.inmyhands;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Map<String, Object>> students;

    public StudentAdapter(List<Map<String, Object>> students) {
        this.students = students;
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
        holder.vuidTextView.setText((String) student.get("Vuid"));
        // Bind other fields similarly
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void updateData(List<Map<String, Object>> newStudents) {
        this.students = newStudents;
        notifyDataSetChanged();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
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
        }
    }
}
