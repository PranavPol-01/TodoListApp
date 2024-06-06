package com.example.todolistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks;
    private OnTaskClickListener listener;

    public interface OnTaskClickListener {
        void onTaskClick(int position);
        void onDeleteClick(int position);
    }

    public TaskAdapter(List<Task> tasks, OnTaskClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.textViewTask.setText(task.getName());
        holder.checkBoxCompleted.setChecked(task.isCompleted());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTask;
        CheckBox checkBoxCompleted;
        ImageButton buttonDelete;

        TaskViewHolder(View itemView) {
            super(itemView);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            checkBoxCompleted = itemView.findViewById(R.id.checkBoxCompleted);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);

            checkBoxCompleted.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Task task = tasks.get(position);
                task.setCompleted(!task.isCompleted());
                listener.onTaskClick(position);
            });

            buttonDelete.setOnClickListener(v -> listener.onDeleteClick(getAdapterPosition()));
        }
    }
}
